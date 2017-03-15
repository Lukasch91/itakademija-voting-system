package vs.public_.multi.results;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin_.constituency.Constituency;
import vs.admin_.constituency.ConstituencyRepository;
import vs.admin_.district.District;
import vs.admin_.district.DistrictRepository;
import vs.admin_.party.Party;
import vs.admin_.party.PartyRepository;
import vs.public_.single.results.ElectionDetails;
import vs.public_.single.results.SingleElectionResultsComperator;
import vs.representative_.corruptedvotes.CorruptedVotesRepository;
import vs.representative_.multielection.MultiElectionRepository;

@Service
public class MultiElectionResultsService {
	
	private static final Logger log = Logger.getLogger(MultiElectionResultsService.class.getName());

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

		log.info("||--> Started..." );
		
		List<Party> partyList = partyRepository.findAllParties();

		List<MultiElectionResults> resultList = new ArrayList<>();

		for (Party party : partyList) {

			Integer partyId = party.getId();

			String partyTitle = party.getTitle();

			Long votes = changeNullToLong(multiElectionRepository.getVotesOfCandidate(partyId));

			Long allPublishedVotes = changeNullToLong(multiElectionRepository.getAllPublishedVotes());

			Long allinvalidVotes = changeNullToLong(corruptedVotesRepository.getAllMultiCorruptedVotes());

			Long allVotes = allPublishedVotes + allinvalidVotes;

			Long mandates = null;

			BigDecimal percentageOfAllVotes = checkForCorrectArithmetic(votes, allVotes);

			MultiElectionResults multiElectionConstituency = new MultiElectionResults(partyId, partyTitle, votes,
					percentageOfAllVotes, mandates, party.getParty_abbreviation());

			resultList.add(multiElectionConstituency);
		}

		List<MultiElectionResults> list = getPartiesListWithMandates(resultList);

		Collections.sort(list, new MultiElectionResultsComperator());
		Collections.reverse(list);
		log.info("||--> Finished!" );
		return list;

	}

	public List<MultiElectionConstituencyList> getMultiElectionConstituencyList() {
		log.info("||--> Started..." );
		List<Constituency> constituencyList = constituencyRepository.findAllConstituencies();

		List<MultiElectionConstituencyList> resultList = new ArrayList<>();

		for (Constituency constituency : constituencyList) {

			Integer id = constituency.getId();

			String title = constituency.getTitle();

			Long numberOfDistricts = changeNullToLong(districtRepository.getNumberOfExistentDistricts(id));

			Long districtsPublishedResults = changeNullToLong(multiElectionRepository.getCountDistricts(id));

			Long numberOfVoters = changeNullToLong(districtRepository.getSumOfVoters(id));

			Long invalidVotes = changeNullToLong(corruptedVotesRepository.getMultiCorruptedVotesInConstituency(id));

			Long numberOfVotersWhoVoted = changeNullToLong(multiElectionRepository.getAllPublishedVotes(id))
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
		log.info("||--> Finished!" );
		return resultList;

	}

	public List<MultiElectionDistrictList> getResultsOfDistricts(Integer consId) {
		
		log.info("||--> Started...Constituency id : " + consId );
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

		log.info("||--> Finished!" );
		return resultList;

	}

	public List<MultiElectionResults> getConstituencyPartiesResults(Integer consId) {

		log.info("||--> Started...Constituency id : " + consId );
		
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
					percentageOfAllVotes, mandates, party.getParty_abbreviation());

			resultList.add(multiElectionResults);
		}
		Collections.sort(resultList, new MultiElectionResultsComperator());
		Collections.reverse(resultList);
		log.info("||--> Finished!" );
		return resultList;
	}

	public List<MultiElectionResults> getDistrictPartiesResults(Integer disId) {

		log.info("||--> Started...District id : " + disId );
		
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
					percentageOfAllVotes, mandates, party.getParty_abbreviation());

			resultList.add(multiElectionConstituency);
		}
		
		log.info("||--> Finished!" );
		
		return resultList;
	}

	public ElectionDetails getMultiElectionDetails() {
		
		log.info("||--> Started...");

		Long numberOfDistricts = changeNullToLong(districtRepository.countAllDistricts());

		Long numberOfConstituencies = changeNullToLong(constituencyRepository.countAllConstituencies());

		Long numberOfVoters = changeNullToLong(districtRepository.countAllVoters());

		Long validVotes = changeNullToLong(multiElectionRepository.getSumOfAllVotes());

		Long invalidVotes = changeNullToLong(corruptedVotesRepository.getAllSingleCorruptedVotes());

		Long numberOfVotersWhoVoted = changeNullToLong(validVotes + invalidVotes);

		BigDecimal percentageOfVoters = checkForCorrectArithmetic(numberOfVotersWhoVoted, numberOfVoters);

		BigDecimal percentageOfInvalidVotes = checkForCorrectArithmetic(invalidVotes, numberOfVotersWhoVoted);

		BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(validVotes, numberOfVotersWhoVoted);

		ElectionDetails details = new ElectionDetails(numberOfDistricts, numberOfConstituencies, numberOfVoters,
				numberOfVotersWhoVoted, percentageOfVoters, invalidVotes, percentageOfInvalidVotes, validVotes,
				percentageOfValidVotes);

		log.info("||--> Finished!" );
		return details;

	}

	public ElectionDistrictDetails getDistrictElectionDetails(Integer id) {

		log.info("||--> Started...District id: " + id);
		
		District district = districtRepository.findDistrictById(id);

		Integer districtId = district.getId();

		String districtTitle = district.getTitle();

		Integer constituencyId = district.getConstituencyId();

		Constituency constituency = constituencyRepository.findConstituencyById(constituencyId);

		String constituencyTitle = constituency.getTitle();

		Long voters = changeNullToLong(district.getNumberOfVoters());

		Long validVotes = changeNullToLong(multiElectionRepository.getNumberOfVotesInDistrict(districtId));

		Long invalidvotes = changeNullToLong(corruptedVotesRepository.getCorruptedVotesByDistrict(districtId));

		Long allVotes = changeNullToLong(validVotes + invalidvotes);

		BigDecimal percentageOfVoted = checkForCorrectArithmetic(allVotes, voters);

		BigDecimal percentageOfInvalidVotes = checkForCorrectArithmetic(invalidvotes, allVotes);

		BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(validVotes, allVotes);

		String updateDate = null;

		if (multiElectionRepository.getPublishedDate(districtId) != null) {
			updateDate = multiElectionRepository.getPublishedDate(districtId).toString();

		}

		ElectionDistrictDetails electionDistrictDetails = new ElectionDistrictDetails(districtId, districtTitle,
				constituencyTitle, constituencyId, voters, validVotes, invalidvotes, allVotes, percentageOfVoted,
				percentageOfInvalidVotes, percentageOfValidVotes, updateDate);

		log.info("||--> Finished!" );
		return electionDistrictDetails;

	}

	public BigDecimal getPercentageSumOver(List<MultiElectionResults> list) {
		log.info("||--> Started...");
		BigDecimal sum = BigDecimal.valueOf(0.00);
		for (MultiElectionResults multiElectionConstituency : list) {
			if (multiElectionConstituency.getPercentageOfAllVotes() != null) {
				BigDecimal percentage = multiElectionConstituency.getPercentageOfAllVotes();
				if (percentage.compareTo(BigDecimal.valueOf(5)) > 0) {
					sum = sum.add(percentage);
				}
			}

		}
		log.info("||--> Finished! sum: " + sum);
		return sum;
	}

	public List<MultiElectionResults> getPartiesListWithMandates(List<MultiElectionResults> list) {
		log.info("||--> Started...");
		BigDecimal sum = getPercentageSumOver(list);
		Long mandates = 0L;
		for (MultiElectionResults multiElectionConstituency : list) {
			BigDecimal percentage = multiElectionConstituency.getPercentageOfAllVotes();

			if (percentage.compareTo(BigDecimal.valueOf(5)) > 0) {
				mandates = ((multiElectionConstituency.getPercentageOfAllVotes().multiply(BigDecimal.valueOf(70)))
						.divideToIntegralValue(sum)).setScale(0, RoundingMode.HALF_UP).longValue();
				multiElectionConstituency.setNumberOfMandates(mandates);
			}
		}
		log.info("||--> Finished!");
		return list;
	}

	public Long changeNullToLong(Long parameter) {
		log.info("||--> was used. Long parameter: " + parameter);
		if (parameter == null) {
			return 0L;
		}
		return parameter;
	}

	public BigDecimal checkForCorrectArithmetic(Long firstParameter, Long secondParamter) {
		
		log.info("||--> Started...FirstParameter|SecondParameter: " + firstParameter + " | " + secondParamter);
		
		if (firstParameter == 0 || secondParamter == 0) {
			return new BigDecimal(0.00);
		}
		
		BigDecimal percentage = new BigDecimal(firstParameter * 100.0 / secondParamter);
		
		log.info("||--> Finished! result: " + percentage.setScale(2, RoundingMode.HALF_UP));
		
		return percentage.setScale(2, RoundingMode.HALF_UP);
	}

}
