package vs.public_.multi.results;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin_.constituency.Constituency;
import vs.admin_.constituency.ConstituencyRepository;
import vs.admin_.district.District;
import vs.admin_.district.DistrictRepository;
import vs.admin_.party.Party;
import vs.admin_.party.PartyRepository;
import vs.representative_.corruptedvotes.CorruptedVotesRepository;
import vs.representative_.multielection.MultiElectionRepository;

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

	public List<MultiElectionResults> getMultiElectionResults() {

		List<Party> partyList = partyRepository.findAllParties();

		List<MultiElectionResults> resultList = new ArrayList<>();

		for (Party party : partyList) {

			Integer partyId = party.getId();

			String partyTitle = party.getTitle();

			Long votes = changeNullToLong(multiElectionRepository.getVotesOfCandidate(partyId));

			Long allPublishedVotes = changeNullToLong(multiElectionRepository.getAllPublishedVotes());

			Long allinvalidVotes = changeNullToLong(corruptedVotesRepository.getAllMultiElectionInvalidVotes());

			Long allVotes = allPublishedVotes + allinvalidVotes;

			Long mandates = null;

			BigDecimal percentageOfAllVotes = checkForCorrectArithmetic(votes, allVotes);

			MultiElectionResults multiElectionConstituency = new MultiElectionResults(partyId, partyTitle, votes,
					percentageOfAllVotes, mandates);

			resultList.add(multiElectionConstituency);
		}
		return (List<MultiElectionResults>) getPartiesListWithMandates(resultList);
	}

	public List<MultiElectionConstituencyList> getMultiElectionConstituencyList() {

		List<Constituency> constituencyList = constituencyRepository.findAllConstituencies();

		List<MultiElectionConstituencyList> resultList = new ArrayList<>();

		for (Constituency constituency : constituencyList) {

			Integer id = constituency.getId();

			String title = constituency.getTitle();

			Long numberOfDistricts = changeNullToLong(districtRepository.getNumberOfExistentDistricts(id));

			Long districtsPublishedResults = changeNullToLong(multiElectionRepository.getCountDistricts(id));

			Long numberOfVoters = changeNullToLong(districtRepository.getSumOfVoters(id));

			Long invalidVotes = changeNullToLong(corruptedVotesRepository.getMultiCorruptedVotesInConstituency(id));

			Long numberOfVotersWhoVoted = changeNullToLong(multiElectionRepository.getAllPublishedVotes())
					+ changeNullToLong(invalidVotes);

			BigDecimal percentegeOfVoted = checkForCorrectArithmetic(numberOfVotersWhoVoted, numberOfVoters);

			Long validVotes = changeNullToLong(multiElectionRepository.getSumOfValidVotes(id));

			BigDecimal percentageOfInvalidVotes = checkForCorrectArithmetic(invalidVotes, numberOfVotersWhoVoted);

			BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(validVotes, numberOfVotersWhoVoted);

			MultiElectionConstituencyList multiElectionConstituencyList = new MultiElectionConstituencyList(id, title,
					numberOfDistricts, districtsPublishedResults, numberOfVoters, numberOfVotersWhoVoted,
					percentegeOfVoted, invalidVotes, percentageOfInvalidVotes, validVotes, percentageOfValidVotes);

			resultList.add(multiElectionConstituencyList);

		}

		return resultList;

	}

	public List<MultiElectionDistrictList> getResultsOfDistricts(Integer consId) {
		List<District> districtList = districtRepository.findAllDistrictsByConstituencyId(consId);
		List<MultiElectionDistrictList> resultList = new ArrayList<>();

		for (District district : districtList) {

			Integer id = district.getId();

			String title = district.getTitle();

			Long numberOfVoters = changeNullToLong(district.getNumberOfVoters());

			Long invalidVotes = changeNullToLong(corruptedVotesRepository.getMultiCorruptedVotesInDistrict(id));

			Long validVotes = changeNullToLong(multiElectionRepository.getNumberOfVotesInDistrict(id));

			BigDecimal percentageOfVoted = checkForCorrectArithmetic(validVotes, numberOfVoters);

			Long AllVotes = changeNullToLong(invalidVotes + validVotes);

			BigDecimal percentageOfInvalidVotes = checkForCorrectArithmetic(invalidVotes, AllVotes);

			BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(validVotes, AllVotes);

			MultiElectionDistrictList multiElectionDistrictList = new MultiElectionDistrictList(id, title,
					numberOfVoters, AllVotes, percentageOfVoted, invalidVotes, percentageOfInvalidVotes, validVotes,
					percentageOfValidVotes);

			resultList.add(multiElectionDistrictList);

		}

		return resultList;

	}

	public List<MultiElectionResults> getConstituencyPartiesResults(Integer consId) {

		List<Party> partyList = partyRepository.findAllParties();

		List<MultiElectionResults> resultList = new ArrayList<>();

		for (Party party : partyList) {

			Integer partyId = party.getId();

			String partyTitle = party.getTitle();

			Long votes = changeNullToLong(multiElectionRepository.getVotesOfPartyByConsId(consId, partyId));

			Long allPublishedVotes = changeNullToLong(multiElectionRepository.getSumOfValidVotes(consId));

			Long allinvalidVotes = changeNullToLong(corruptedVotesRepository.getCorruptedVotesInConstituency(consId));

			Long allVotes = changeNullToLong(allPublishedVotes + allinvalidVotes);

			Long mandates = null;

			BigDecimal percentageOfAllVotes = checkForCorrectArithmetic(votes, allVotes);

			MultiElectionResults multiElectionResults = new MultiElectionResults(partyId, partyTitle, votes,
					percentageOfAllVotes, mandates);

			resultList.add(multiElectionResults);
		}
		return resultList;
	}

	public List<MultiElectionResults> getDistrictPartiesResults(Integer disId) {

		List<Party> partyList = partyRepository.findAllParties();

		List<MultiElectionResults> resultList = new ArrayList<>();

		for (Party party : partyList) {

			Integer partyId = party.getId();

			String partyTitle = party.getTitle();

			Long votes = changeNullToLong(multiElectionRepository.getVotesOfPartyByDistrictId(disId, partyId));

			Long allPublishedVotes = changeNullToLong(multiElectionRepository.getNumberOfVotesInDistrict(disId));

			Long allinvalidVotes = corruptedVotesRepository.getMultiCorruptedVotesInDistrict(disId);

			Long allVotes = allPublishedVotes + allinvalidVotes;

			Long mandates = null;

			BigDecimal percentageOfAllVotes = checkForCorrectArithmetic(votes, allVotes);

			MultiElectionResults multiElectionConstituency = new MultiElectionResults(partyId, partyTitle, votes,
					percentageOfAllVotes, mandates);

			resultList.add(multiElectionConstituency);
		}
		return resultList;
	}

	public BigDecimal getPercentageSumOver(List<MultiElectionResults> list) {
		BigDecimal sum = BigDecimal.valueOf(0.00);
		for (MultiElectionResults multiElectionConstituency : list) {
			if (multiElectionConstituency.getPercentageOfAllVotes() != null) {
				BigDecimal percentage = multiElectionConstituency.getPercentageOfAllVotes();
				if (percentage.compareTo(BigDecimal.valueOf(7)) > 0) {
					sum = sum.add(percentage);
				}
			}

		}
		return sum;
	}

	public List<MultiElectionResults> getPartiesListWithMandates(List<MultiElectionResults> list) {
		BigDecimal sum = getPercentageSumOver(list);
		Long mandates = 0L;
		for (MultiElectionResults multiElectionConstituency : list) {
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
			return 0L;
		}
		return parameter;
	}

	public BigDecimal checkForCorrectArithmetic(Long firstParameter, Long secondParamter) {
		if (firstParameter == 0 || secondParamter == 0) {
			return new BigDecimal(0.00);
		}
		BigDecimal percentage = new BigDecimal(firstParameter * 100.0 / secondParamter);
		return percentage.setScale(2, RoundingMode.HALF_UP);
	}

}
