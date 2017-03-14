package vs.public_.consolidated.results;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin_.candidate.Candidate;
import vs.admin_.candidate.CandidateRepository;
import vs.admin_.constituency.Constituency;
import vs.admin_.constituency.ConstituencyRepository;
import vs.public_.multi.results.MultiElectionResults;
import vs.public_.multi.results.MultiElectionResultsService;
import vs.public_.single.results.SingleElectionResultsService;
import vs.representative_.singleelection.SingleElectionRepository;

@Service
public class ConsolidatedResultsService {

	private static final Logger log = Logger.getLogger(ConsolidatedResultsService.class.getName());

	@Autowired
	ConstituencyRepository constituencyRepository;

	@Autowired
	SingleElectionResultsService singleElectionResultsService;

	@Autowired
	MultiElectionResultsService multiElectionResultsService;

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	SingleElectionRepository singleElectionRepository;

	public List<MultiElectionResults> getPartyListWithMandates() {

		List<MultiElectionResults> allPartiesList = multiElectionResultsService.getMultiElectionResults();
		List<MultiElectionResults> listOfPartiesWithMandates = new ArrayList<>();

		for (MultiElectionResults multiElectionResults : allPartiesList) {

			if (multiElectionResults.getNumberOfMandates() != null) {
				listOfPartiesWithMandates.add(multiElectionResults);
			}
		}
		return listOfPartiesWithMandates;
	}

	public List<Candidate> getMultiMembersList(Integer partyId) {

		List<Candidate> multiCandidates = candidateRepository.findCandidatesByPartyId(partyId);
		List<Candidate> singleList = getSingleMembersList();
		List<Candidate> filteredSingleFromMultiList = new ArrayList<>();

		for (Candidate singleCandidate : singleList) {

			for (Candidate candidate : multiCandidates) {

				if (singleCandidate.getCandidatePersonalID() != candidate.getCandidatePersonalID() && filteredSingleFromMultiList.size() < getNumberOfMandates(partyId)) {

					filteredSingleFromMultiList.add(candidate);

				}

			}
		}
		return filteredSingleFromMultiList;
	}

	public List<Candidate> getSingleMembersList() {

		List<Constituency> conList = constituencyRepository.findAllConstituencies();
		List<Candidate> singleElectionWinners = new ArrayList<>();

		for (Constituency constituency : conList) {

			if (singleElectionRepository.getWinnerOfDistrict(constituency.getId()) != null) {
				singleElectionWinners.add(singleElectionRepository.getWinnerOfDistrict(constituency.getId()));
			}
		}
		return singleElectionWinners;
	}

	public List<Candidate> consolidateMembersList() {

		List<MultiElectionResults> partyWithMandateslist = getPartyListWithMandates();
		List<Candidate> memberOfParliamentList = new ArrayList<>();

		for (MultiElectionResults multiElectionResults : partyWithMandateslist) {
			memberOfParliamentList.addAll(getMultiMembersList(multiElectionResults.getId()));
		}
		memberOfParliamentList.addAll(getSingleMembersList());
		return memberOfParliamentList;
	}

	public List<MemberOfParliament> changeObjectType() {

		List<Candidate> consolidatedMemberList = consolidateMembersList();
		List<MemberOfParliament> newList = new ArrayList<>();

		for (Candidate candidate : consolidatedMemberList) {
			if (candidate.getCandidateSurname() != null && candidate.getCandidateName() != null && candidate.getCandidateParty() != null) {
				MemberOfParliament member = new MemberOfParliament(candidate.getCandidateName(),
						candidate.getCandidateSurname(), candidate.getCandidateParty().getTitle());
				newList.add(member);
			}else if (candidate.getCandidateParty() == null) {
				MemberOfParliament member = new MemberOfParliament(candidate.getCandidateName(),
						candidate.getCandidateSurname(), "Išsikėlęs pats");
				newList.add(member);
			}
		}

		return newList;
	}

	public Long getNumberOfMandates(Integer partyId) {

		List<MultiElectionResults> allPartiesList = multiElectionResultsService.getMultiElectionResults();
		MultiElectionResults party = allPartiesList.stream().filter(x -> partyId == x.getId()).findAny().orElse(null);
		return party.getNumberOfMandates();

	}

	public List<MemberOfParliament> getSortedMemberOfParlList() {

		List<MemberOfParliament> membersOfParliamant = changeObjectType();
		Comparator<MemberOfParliament> compretorfirstname = (o1, o2) -> o1.getFirstname().compareTo(o2.getFirstname());
		Comparator<MemberOfParliament> compretorSurname = (o1, o2) -> o1.getSurname().compareTo(o2.getSurname());
		membersOfParliamant.sort(compretorfirstname);
		membersOfParliamant.sort(compretorSurname);
		return membersOfParliamant;

	}

}
