package vs.representative.features.election.single.results;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import vs.admin.features.admin.constituency.Constituency;
import vs.admin.features.admin.constituency.ConstituencyRepository;
import vs.admin.features.admin.district.District;
import vs.admin.features.admin.district.DistrictRepository;
import vs.admin.features.candidate.model.Candidate;
import vs.admin.features.candidate.model.CandidateRepository;
import vs.representative.features.corrupted.votes.CorruptedVotes;
import vs.representative.features.corrupted.votes.CorruptedVotesRepository;
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

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	CorruptedVotesRepository corruptedVotesRepository;

	public List<SingleElectionConstituency> singleElectionConstituencyResults() {

		List<Constituency> constituencyList = constituencyRepository.findAllConstituencies();
		List<SingleElectionConstituency> resultList = new ArrayList<>();

		for (Constituency constituency : constituencyList) {

			Integer id = constituency.getId();

			Long numberOfDistricts = changeNullToLong(districtRepository.getNumberOfExistentDistricts(id));

			Long districtsPublishedResults = changeNullToLong(singleElectionRepository.getNumberOfPublishedResults(id));

			Long sumOfVoters = changeNullToLong(districtRepository.getSumOfVoters(id));

			Long invalidVotes = changeNullToLong(
					corruptedVotesRepository.getCorruptedVotesInConstituency(constituency.getId()));

			Long voted = changeNullToLong(singleElectionRepository.getSumOfPublishedVotes(id)) + invalidVotes;

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
					corruptedVotesRepository.getCorruptedVotesByDistrict(district.getId()));

			Long validVotes = changeNullToLong(voted + invalidVotes);

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

	public List<SingleElectionResult> getSingleElectionResults(Integer id) {
		List<Candidate> candidatesList = candidateRepository.findCandidatesByConstituencyId(id);

		List<SingleElectionResult> resultList = new ArrayList<>();

		for (Candidate candidate : candidatesList) {

			String party = "Išsikelęs pats";

			Long invalidVotesInConstituency = changeNullToLong(corruptedVotesRepository
					.getCorruptedVotesInConstituency(candidate.getCandidateConstituency().getId()));

			Long validVotesInConstituency = changeNullToLong(
					singleElectionRepository.getSumOfValidVotes(candidate.getCandidateConstituency().getId()));

			Long voted = changeNullToLong(singleElectionRepository.getSumOfPublishedVotes(candidate.getCandidateID()));

			Long allVotes = validVotesInConstituency + invalidVotesInConstituency;

			BigDecimal percentageOfVoted = checkForCorrectArithmetic(voted, validVotesInConstituency);

			BigDecimal percentageOfAllVotes = checkForCorrectArithmetic(voted, allVotes);

			if (candidate.getCandidateParty() != null) {
				party = candidate.getCandidateParty().getTitle();
			}

			SingleElectionResult singleElectionResult = new SingleElectionResult(candidate.getCandidateID(),
					candidate.getCandidateName(), candidate.getCandidateSurname(), party, voted, percentageOfVoted,
					percentageOfAllVotes);

			resultList.add(singleElectionResult);

		}
		Collections.sort(resultList, new SingleElectionResultsComperator());
		Collections.reverse(resultList);
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
		BigDecimal percentage = new BigDecimal(firstParameter * 100.0 / secondParamter);
		return percentage.setScale(2, RoundingMode.HALF_UP);
	}

	public SingleElectionConstituency getConstiteuncyResult(Integer id) {
		List<SingleElectionConstituency> list = singleElectionConstituencyResults();
		return list.stream().filter(r -> r.getConstituencyId() == id).findFirst().get();
	}
}
