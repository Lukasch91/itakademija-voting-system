package vs.representative_.multielection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONArray;
import vs.admin_.district.DistrictRepository;
import vs.representative_.corruptedvotes.CorruptedVotes;
import vs.representative_.corruptedvotes.CorruptedVotesRepository;
import vs.utils_.hibernate.validators.multiElection.MEValidationMessages;

@Service
public class MultiElectionCreateService {

	@Autowired
	private MultiElectionRepository multiElectionRepository;
	@Autowired
	private CorruptedVotesRepository corruptedVotesRepository;
	@Autowired
	private DistrictRepository districtRepository;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	private Long numberOfVotes;

	@SuppressWarnings("rawtypes")
	public ResponseEntity validatePackage(List<MultiVotesPackage> multiVotesPackage) {

		CorruptedVotes spoiltVote = new CorruptedVotes();
		List<MultiElection> multiVotes = new ArrayList<MultiElection>();

		JSONArray jsonArray = new JSONArray();

		for (MultiVotesPackage unidentifiedVote : multiVotesPackage) {
			if (unidentifiedVote.getTypeMulti() != null) {
				spoiltVote.setDistrict(unidentifiedVote.getDistrict());
				spoiltVote.setTypeMulti(unidentifiedVote.getTypeMulti());
				spoiltVote.setVotes(unidentifiedVote.getVotes());
			} else {
				MultiElection multiVote = new MultiElection();
				multiVote.setDistrict(unidentifiedVote.getDistrict());
				multiVote.setParty(unidentifiedVote.getParty());
				multiVote.setVotes(unidentifiedVote.getVotes());
				multiVotes.add(multiVote);
			}
		}
		// validate spoiltVotes with hibernate
		if (validator.validate(spoiltVote).isEmpty() == false) {
			Set<ConstraintViolation<CorruptedVotes>> constraintViolationsSpoilt = validator.validate(spoiltVote);
			MEValidationMessages vMessagesSpoilt = new MEValidationMessages();
			vMessagesSpoilt.setPartyId(null);
			vMessagesSpoilt.setSpoiltVote(true);

			for (ConstraintViolation<CorruptedVotes> constraintViolation : constraintViolationsSpoilt) {
				vMessagesSpoilt.setOneMessage(constraintViolation.getMessage());
			}
			jsonArray.add(vMessagesSpoilt);
			constraintViolationsSpoilt.clear();
		}
		// validate multiVotes with hibernate
		for (MultiElection multiElection : multiVotes) {
			if (validator.validate(multiElection).isEmpty() == false) {

				Set<ConstraintViolation<MultiElection>> constraintViolationsMulti = validator.validate(multiElection);
				MEValidationMessages vMessagesMulti = new MEValidationMessages();
				vMessagesMulti.setPartyId(multiElection.getParty().getId());
				vMessagesMulti.setSpoiltVote(false);

				for (ConstraintViolation<MultiElection> constraintViolation : constraintViolationsMulti) {
					vMessagesMulti.setOneMessage(constraintViolation.getMessage());
				}
				jsonArray.add(vMessagesMulti);
				constraintViolationsMulti.clear();
			}
		}
		// validate number of votes entered
		if (jsonArray.isEmpty()) {
			numberOfVotes = 0L;
			for (MultiElection multiElection : multiVotes) {
				Long multiVoted = Long.parseLong(multiElection.getVotes());
				numberOfVotes += multiVoted;
			}
			Long spoitVoted = Long.parseLong(spoiltVote.getVotes());
			numberOfVotes += spoitVoted;
			if (districtRepository.findDistrictById(spoiltVote.getDistrict().getId()).getNumberOfVoters() != null) {
				Long votesLimitInDistrict = districtRepository.findDistrictById(spoiltVote.getDistrict().getId())
						.getNumberOfVoters();
				if (numberOfVotes > votesLimitInDistrict) {
					MEValidationMessages vMessageVoteLimit = new MEValidationMessages();
					vMessageVoteLimit.setPartyId(null);
					vMessageVoteLimit.setSpoiltVote(true);
					vMessageVoteLimit.setOneMessage("Balsų kiekis viršija apylinkės rinkėjų skaičių");
					jsonArray.add(vMessageVoteLimit);
				}
			}
		}
		if (jsonArray.isEmpty()) {
			multiElectionRepository.saveOrUpdate(multiVotes);
			corruptedVotesRepository.saveOrUpdate(spoiltVote);
			return ResponseEntity.status(HttpStatus.OK).body(jsonArray);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonArray);
		}
	}
}
