package vs.public_.consolidated.results;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin_.candidate.Candidate;
import vs.admin_.candidate.CandidateRepository;
import vs.admin_.constituency.Constituency;
import vs.admin_.constituency.ConstituencyRepository;
import vs.public_.multi.results.MultiElectionResults;
import vs.public_.multi.results.MultiElectionResultsService;
import vs.public_.single.results.SingleElectionResult;
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

	public List<ConsolidatedResults> getMultiResults() {
		log.info("||--> Started...");

		List<ConsolidatedResults> consolidatedResultsList = new ArrayList<>();
		List<MultiElectionResults> resultList = multiElectionResultsService.getMultiElectionResults();
		for (MultiElectionResults multiElectionResults : resultList) {

			if (multiElectionResults.getNumberOfMandates() != null) {
				ConsolidatedResults consolidatedResults = new ConsolidatedResults(multiElectionResults.getPartyTitle(),
						multiElectionResults.getNumberOfMandates());
				consolidatedResultsList.add(consolidatedResults);
			}
		}
		log.info("||--> Finished!");
		return consolidatedResultsList;
	}

	public List<ConsolidatedResults> getSingleResults() {

		log.info("||--> Started...");
		
		List<Constituency> conList = constituencyRepository.findAllConstituencies();
		List<ConsolidatedResults> consResultList = new ArrayList<>();
		for (Constituency constituency : conList) {
			List<SingleElectionResult> candidatesList = singleElectionResultsService
					.getSingleElectionResults(constituency.getId());

			if (!candidatesList.isEmpty() && candidatesList.get(0).getVoted() != 0) {

				SingleElectionResult singleElectionResult = candidatesList.get(0);
				ConsolidatedResults consolidatedResults = new ConsolidatedResults(singleElectionResult.getParty(),
						(long) 1);
				consResultList.add(consolidatedResults);

			}
		}
		log.info("||--> Finished!");
		return consResultList;
	}

	public List<ConsolidatedResults> getAllResults() {

		log.info("||--> Started...");
		
		List<ConsolidatedResults> singleElectionResults = getSingleResults();
		List<ConsolidatedResults> multiElectionResults = getMultiResults();
		multiElectionResults.addAll(singleElectionResults);
		Map<String, Long> counting = consolidateResults(multiElectionResults);
		List<ConsolidatedResults> consildatedResults = new ArrayList<>();

		for (Entry<String, Long> entry : counting.entrySet()) {

			ConsolidatedResults consolidatedResults = new ConsolidatedResults(entry.getKey(), entry.getValue());
			consildatedResults.add(consolidatedResults);
		}

		log.info("||--> Finished!");
		return consildatedResults;
	}

	private Map<String, Long> consolidateResults(List<ConsolidatedResults> multiElectionResults) {
		log.info("||--> was used...");
		Map<String, Long> counting = multiElectionResults.stream().collect(Collectors.groupingBy(
				ConsolidatedResults::getPartyTitle, Collectors.summingLong(ConsolidatedResults::getMandates)));

		return counting;
	}

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

				if (singleCandidate.getCandidatePersonalID() != candidate.getCandidatePersonalID()) {

					filteredSingleFromMultiList.add(candidate);

				}

			}
		}

		filteredSingleFromMultiList.stream().limit(getNumberOfMandates(partyId));
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

			MemberOfParliament member = new MemberOfParliament(candidate.getCandidateName(),
					candidate.getCandidateSurname(), candidate.getCandidateParty().getTitle());
			newList.add(member);
		}

		return newList;
	}

	public Long getNumberOfMandates(Integer partyId) {

		List<MultiElectionResults> allPartiesList = multiElectionResultsService.getMultiElectionResults();
		MultiElectionResults party = allPartiesList.stream().filter(x -> partyId == x.getId()).findAny().orElse(null);
		return party.getNumberOfMandates();

	}

	public List<ConsolidatedResults> getSortedPartyList() {

		List<ConsolidatedResults> partyList = getAllResults();
		Comparator<ConsolidatedResults> mandatesComparator = (o1, o2) -> o1.getMandates().compareTo(o2.getMandates());
		partyList.sort(mandatesComparator);
		Collections.reverse(partyList);
		return partyList;

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
