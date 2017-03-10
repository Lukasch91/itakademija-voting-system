package vs.public_.consolidated.results;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin_.constituency.Constituency;
import vs.admin_.constituency.ConstituencyRepository;
import vs.public_.multi.results.MultiElectionResults;
import vs.public_.multi.results.MultiElectionResultsService;
import vs.public_.single.results.SingleElectionResult;
import vs.public_.single.results.SingleElectionResultsService;

@Service
public class ConsolidatedResultsService {

	@Autowired
	ConstituencyRepository constituencyRepository;

	@Autowired
	SingleElectionResultsService singleElectionResultsService;

	@Autowired
	MultiElectionResultsService multiElectionResultsService;

	public List<ConsolidatedResults> getMultiResults() {

		List<ConsolidatedResults> consolidatedResultsList = new ArrayList<>();

		List<MultiElectionResults> resultList = multiElectionResultsService.getMultiElectionResults();

		for (MultiElectionResults multiElectionResults : resultList) {

			if (multiElectionResults.getNumberOfMandates() != null) {

				ConsolidatedResults consolidatedResults = new ConsolidatedResults(multiElectionResults.getPartyTitle(),
						multiElectionResults.getNumberOfMandates());

				consolidatedResultsList.add(consolidatedResults);
			}

		}
		return consolidatedResultsList;
	}

	public List<ConsolidatedResults> getSingleResults() {

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
		return consResultList;

	}

	public List<ConsolidatedResults> getAllResults() {

		List<ConsolidatedResults> singleElectionResults = getSingleResults();

		List<ConsolidatedResults> multiElectionResults = getMultiResults();

		multiElectionResults.addAll(singleElectionResults);

		Map<String, Long> counting = consolidateResults(multiElectionResults);

		List<ConsolidatedResults> consildatedResults = new ArrayList<>();
		
		for (Entry<String, Long> entry : counting.entrySet()) {
			
			ConsolidatedResults consolidatedResults = new ConsolidatedResults(entry.getKey(), entry.getValue());

			consildatedResults.add(consolidatedResults);
		}
		

		return consildatedResults;

	}

	private Map<String, Long> consolidateResults(List<ConsolidatedResults> multiElectionResults) {

		Map<String, Long> counting = multiElectionResults.stream().collect(Collectors.groupingBy(
				ConsolidatedResults::getPartyTitle, Collectors.summingLong(ConsolidatedResults::getMandates)));

		return counting;
	}

}