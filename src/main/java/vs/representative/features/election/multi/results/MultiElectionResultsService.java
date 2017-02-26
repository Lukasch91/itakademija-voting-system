package vs.representative.features.election.multi.results;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin.features.admin.constituency.Constituency;
import vs.admin.features.admin.constituency.ConstituencyRepository;
import vs.admin.features.admin.district.DistrictRepository;
import vs.admin.features.party.model.Party;
import vs.admin.features.party.model.PartyRepository;
import vs.representative.features.corrupted.votes.CorruptedVotesRepository;
import vs.representative.features.multi.election.MultiElectionRepository;

@Service
public class MultiElectionResultsService {

	@Autowired
	PartyRepository partyRepository;

	@Autowired
	ConstituencyRepository constituencyRepository;

	@Autowired
	MultiElectionRepository multiElectionRepository;

	@Autowired
	CorruptedVotesRepository corruptedVotesRepository;

	@Autowired
	DistrictRepository districtRepository;

	public List<MultiElectionConstituency> getMultiElectionResults() {

		List<Party> partyList = partyRepository.findAllParties();

		List<MultiElectionConstituency> resultList = new ArrayList<>();

		for (Party party : partyList) {

			Integer partyId = party.getId();

			String partyTitle = party.getTitle();

			Long votes = changeNullToLong(multiElectionRepository.getVotesOfCandidate(partyId));

			Long allPublishedVotes = changeNullToLong(multiElectionRepository.getAllPublishedVotes());

			Long allinvalidVotes = changeNullToLong(corruptedVotesRepository.getAllMultiElectionInvalidVotes());

			Long allVotes = allPublishedVotes + allinvalidVotes;

			Long mandates = null;

			BigDecimal percentageOfAllVotes = checkForCorrectArithmetic(votes, allVotes);

			MultiElectionConstituency multiElectionConstituency = new MultiElectionConstituency(partyId, partyTitle,
					votes, percentageOfAllVotes, mandates);

			resultList.add(multiElectionConstituency);
		}
		return (List<MultiElectionConstituency>) getPartiesListWithMandates(resultList);
	}

	public List<MultiElectionConstituencyList> getMultiElectionConstituencyList(){
		
		List<Constituency> constituencyList = constituencyRepository.findAllConstituencies();

		for (Constituency constituency : constituencyList) {
			
			Integer id = constituency.getId();
			
			String title = constituency.getTitle();
			
			Long numberOfDistricts = districtRepository.getNumberOfExistentDistricts(id);
			
			Long districtsPublishedResults = multiElectionRepository.getCountDistricts(id);
			
			Long numberOfVoters = districtRepository.getSumOfVoters(id);
			
				
//			NOT FINISHED
			
//			MultiElectionConstituencyList multiElectionConstituencyList = new MultiElectionConstituencyList(id,
//					title, 
//					numberOfDistricts, 
//					districtsPublishedResults, 
//					numberOfVoters, 
//					numberOfVotersWhoVoted, 
//					percentegeOfVoted, 
//					invalidVotes, 
//					percentageOfInvalidVotes, 
//					validVotes, 
//					percentageOfValidVotes)
		}
		
		return null;
		
	}

	public BigDecimal getPercentageSumOver(List<MultiElectionConstituency> list) {
		BigDecimal sum = BigDecimal.valueOf(0.00);
		for (MultiElectionConstituency multiElectionConstituency : list) {
			if (multiElectionConstituency.getPercentageOfAllVotes() != null) {
				BigDecimal percentage = multiElectionConstituency.getPercentageOfAllVotes();
				if (percentage.compareTo(BigDecimal.valueOf(7)) > 0) {
					sum = sum.add(percentage);
				}
			}

		}
		return sum;
	}

	public List<MultiElectionConstituency> getPartiesListWithMandates(List<MultiElectionConstituency> list) {
		BigDecimal sum = getPercentageSumOver(list);
		Long mandates = 0L;
		for (MultiElectionConstituency multiElectionConstituency : list) {
			BigDecimal percentage = multiElectionConstituency.getPercentageOfAllVotes();

			if (percentage.compareTo(BigDecimal.valueOf(7)) > 0) {
				mandates = ((sum.multiply(BigDecimal.valueOf(70)))
						.divideToIntegralValue(multiElectionConstituency.getPercentageOfAllVotes())).longValue();
				multiElectionConstituency.setNumberOfMandates(mandates);
			}
		}
		return list;
	}

	public Long changeNullToLong(Long parameter) {
		if (parameter == null) {
			return (long) 0;
		}
		return parameter;
	}

	public BigDecimal checkForCorrectArithmetic(Long firstParameter, Long secondParamter) {
		if (firstParameter == 0 || secondParamter == 0) {
			return new BigDecimal(0);
		}
		BigDecimal percentage = new BigDecimal(firstParameter * 100.0 / secondParamter);
		return percentage.setScale(2, RoundingMode.HALF_UP);
	}

}
