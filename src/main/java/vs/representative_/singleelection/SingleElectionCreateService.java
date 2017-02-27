package vs.representative_.singleelection;

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
import vs.utils_.hibernate.validators.singleElection.SEValidationMessages;

@Service
public class SingleElectionCreateService {

	@Autowired
	private SingleElectionRepository singleElectionRepository;
	@Autowired
	private CorruptedVotesRepository corruptedVotesRepository;
	@Autowired
	private DistrictRepository districtRepository;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	private Long numberOfVotes;

	@SuppressWarnings("rawtypes")
	public ResponseEntity validatePackage(List<SingleVotesPackage> singleVotesPackage) {

		CorruptedVotes spoiltVote = new CorruptedVotes();
		List<SingleElection> singleVotes = new ArrayList<SingleElection>();

		JSONArray jsonArray = new JSONArray();

		for (SingleVotesPackage unidentifiedVote : singleVotesPackage) {
			if (unidentifiedVote.getTypeMulti() != null) {
				spoiltVote.setDistrict(unidentifiedVote.getSingleDistrict());
				spoiltVote.setTypeMulti(unidentifiedVote.getTypeMulti()); // false
				spoiltVote.setVotes(unidentifiedVote.getSingleVotes());
			} else {
				SingleElection singleVote = new SingleElection();
				singleVote.setSingleDistrict(unidentifiedVote.getSingleDistrict());
				singleVote.setSingleCandidate(unidentifiedVote.getSingleCandidate());//       setParty(unidentifiedVote.getParty());
				singleVote.setSingleVotes(unidentifiedVote.getSingleVotes());
				singleVotes.add(singleVote);
			}
		}

		if (validator.validate(spoiltVote).isEmpty() == false) {
			Set<ConstraintViolation<CorruptedVotes>> constraintViolationsSpoilt = validator.validate(spoiltVote);
			SEValidationMessages vMessagesSpoilt = new SEValidationMessages();
			vMessagesSpoilt.setCandidateId(null);
			vMessagesSpoilt.setSpoiltVote(true);

			for (ConstraintViolation<CorruptedVotes> constraintViolation : constraintViolationsSpoilt) {
				vMessagesSpoilt.setOneMessage(constraintViolation.getMessage());
			}
			jsonArray.add(vMessagesSpoilt);
			constraintViolationsSpoilt.clear();
		}

		for (SingleElection singleElection : singleVotes) {
			if (validator.validate(singleElection).isEmpty() == false) {

				Set<ConstraintViolation<SingleElection>> constraintViolationsSingle = validator.validate(singleElection);
				SEValidationMessages vMessagesSingle = new SEValidationMessages();
				vMessagesSingle.setCandidateId(singleElection.getSingleCandidate().getCandidateID());
				vMessagesSingle.setSpoiltVote(false);

				for (ConstraintViolation<SingleElection> constraintViolation : constraintViolationsSingle) {
					vMessagesSingle.setOneMessage(constraintViolation.getMessage());
				}
				jsonArray.add(vMessagesSingle);
				constraintViolationsSingle.clear();
			}
		}

		numberOfVotes = 0L;
		for (SingleElection singleElection : singleVotes) {
			numberOfVotes += singleElection.getSingleVotes();
		}
		numberOfVotes += spoiltVote.getVotes();
		if (districtRepository.findDistrictById(spoiltVote.getDistrict().getId()).getNumberOfVoters() != null) {
			Long votesLimitInDistrict = districtRepository.findDistrictById(spoiltVote.getDistrict().getId())
					.getNumberOfVoters();
			if (numberOfVotes > votesLimitInDistrict) {
				SEValidationMessages vMessageVoteLimit = new SEValidationMessages();
				vMessageVoteLimit.setCandidateId(null);
				vMessageVoteLimit.setSpoiltVote(true);
				vMessageVoteLimit.setOneMessage("Balsų kiekis viršija apylinkės rinkėjų skaičių");
				jsonArray.add(vMessageVoteLimit);
			}
		}

		if (jsonArray.isEmpty()) {
			singleElectionRepository.saveSingleElection(singleVotes);
			corruptedVotesRepository.saveOrUpdate(spoiltVote);
			return ResponseEntity.status(HttpStatus.CREATED).body(jsonArray);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonArray);
		}
	}
}
