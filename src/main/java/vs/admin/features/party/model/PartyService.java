package vs.admin.features.party.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin.features.candidate.model.Candidate;
import vs.admin.features.candidate.model.CandidateRepository;
import vs.admin.features.party.model.PartyRepository;
import vs.admin.features.party.model.PartyExtension;
import vs.admin.features.party.model.Party;

@Service
public class PartyService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private PartyRepository partyRepository;

	public List<PartyExtension> findAllPartiesExtended() {
		List<Party> parties = partyRepository.findAllParties();
		List<Candidate> candidates = candidateRepository.findAllUndeletedCandidates();
		List<PartyExtension> extendedParties = new ArrayList<>();

		for (Party party : parties) {
			PartyExtension partyExtend = new PartyExtension();
			partyExtend.setId(party.getId());
			partyExtend.setTitle(party.getTitle());
			partyExtend.setParty_abbreviation(party.getParty_abbreviation());
			partyExtend.setDeletedTime(null);

			int numberOfCandidates = 0;
			for (Candidate candidate : candidates) {
				if (candidate.getCandidateParty() == party) {
					numberOfCandidates++;
				}
			}
			partyExtend.setNumberOfCandidatesInParty(numberOfCandidates);
			extendedParties.add(partyExtend);
		}
		return extendedParties;
	}
}
