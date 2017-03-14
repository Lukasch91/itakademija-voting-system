package vs.public_.consolidated.results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin_.constituency.Constituency;
import vs.admin_.constituency.ConstituencyRepository;
import vs.public_.multi.results.MultiElectionResults;
import vs.public_.multi.results.MultiElectionResultsService;
import vs.public_.single.results.SingleElectionResult;
import vs.public_.single.results.SingleElectionResultsService;


@Service
public class ConsolidatetPartyListService {

	private static final Logger log = Logger.getLogger(ConsolidatedResultsService.class.getName());

	@Autowired
	MultiElectionResultsService multiElectionResultsService;

	@Autowired
	SingleElectionResultsService singleElectionResultsService;

	@Autowired
	ConstituencyRepository constituencyRepository;

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

	public List<ConsolidatedResults> getSortedPartyList() {

		List<ConsolidatedResults> partyList = getAllResults();
		Comparator<ConsolidatedResults> mandatesComparator = (o1, o2) -> o1.getMandates().compareTo(o2.getMandates());
		partyList.sort(mandatesComparator);
		Collections.reverse(partyList);
		return partyList;

	}
}
