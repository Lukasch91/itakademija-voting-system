package vs.representative.features.election.single.results;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import vs.admin.features.admin.constituency.Constituency;
import vs.admin.features.admin.constituency.ConstituencyRepository;
import vs.admin.features.admin.district.District;
import vs.admin.features.admin.district.DistrictRepository;
import vs.representative.features.single.election.SingleElectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleElectionResultsService {

	@Autowired
	ConstituencyRepository constituencyRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	SingleElectionRepository singleElectionRepository;

	public List<SingleElectionConstituency> singleElectionConstituencyResults() {

		List<Constituency> constituencyList = constituencyRepository.findAllConstituencies();
		List<SingleElectionConstituency> resultList = new ArrayList<>();

		for (Constituency constituency : constituencyList) {

			Integer id = constituency.getId();

			Long numberOfDistricts = changeNullToLong(districtRepository.getNumberOfExistentDistricts(id));

			Long districtsPublishedResults = changeNullToLong(singleElectionRepository.getNumberOfPublishedResults(id));

			Long sumOfVoters = changeNullToLong(districtRepository.getSumOfVoters(id));

			Long voted = changeNullToLong(singleElectionRepository.getSumOfPublishedVotes(id));

			Long invalidVotes = changeNullToLong(singleElectionRepository.getSumOfInvalidVotes(id));

			Long validVotes = changeNullToLong(singleElectionRepository.getSumOfValidVotes(id));

			BigDecimal percentageVoted = checkForCorrectArithmetic(voted, sumOfVoters);

			BigDecimal percentageOfInvalidVotes = checkForCorrectArithmetic(invalidVotes, voted);

			BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(validVotes, voted);

			SingleElectionConstituency singleElectionConstituencyResults = new SingleElectionConstituency(id,
					constituency.getTitle(), numberOfDistricts, districtsPublishedResults, sumOfVoters, voted,
					percentageVoted, invalidVotes, percentageOfInvalidVotes, validVotes, percentageOfValidVotes);

			resultList.add(singleElectionConstituencyResults);

		}

		return resultList;

	}

	public List<SingleElectionDistrict> singleElectionDistrictResults(Integer id) {

		List<District> districtList = districtRepository.findAllDistrictsByConstituencyId(id);
		List<SingleElectionDistrict> resultList = new ArrayList<>();

		for (District district : districtList) {

			Long numberOfVoters = changeNullToLong(district.getNumberOfVoters());

			Long voted = changeNullToLong(singleElectionRepository.getNumberOfpublishedVotes(district.getId()));

			Long invalidVotes = changeNullToLong(
					singleElectionRepository.getNumberOfDistrictInvalidVotes(district.getId()));

			Long validVotes = changeNullToLong(voted - invalidVotes);

			BigDecimal percentageOfVoted = checkForCorrectArithmetic(voted, numberOfVoters);

			BigDecimal percentageOfInvalidVotes = checkForCorrectArithmetic(invalidVotes, voted);

			BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(validVotes, voted);

			SingleElectionDistrict singleElectionDistrict = new SingleElectionDistrict(district.getId(),
					district.getTitle(), numberOfVoters, voted, percentageOfVoted, invalidVotes,
					percentageOfInvalidVotes, validVotes, percentageOfValidVotes);

			resultList.add(singleElectionDistrict);
		}

		return resultList;

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
		return new BigDecimal((firstParameter / secondParamter) * 100);
	}

}
