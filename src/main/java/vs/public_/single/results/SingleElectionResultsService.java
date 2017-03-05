package vs.public_.single.results;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vs.admin_.candidate.Candidate;
import vs.admin_.candidate.CandidateRepository;
import vs.admin_.constituency.Constituency;
import vs.admin_.constituency.ConstituencyRepository;
import vs.admin_.district.District;
import vs.admin_.district.DistrictRepository;
import vs.public_.multi.results.ElectionDistrictDetails;
import vs.representative_.corruptedvotes.CorruptedVotesRepository;
import vs.representative_.singleelection.SingleElectionRepository;

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

			Long validVotes = changeNullToLong(singleElectionRepository.getSumOfPublishedVotes(id));

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

			Long invalidVotes = changeNullToLong(
					corruptedVotesRepository.getCorruptedVotesByDistrict(district.getId()));

			Long validVotes = changeNullToLong(singleElectionRepository.getNumberOfpublishedVotes(district.getId()));

			Long voted = changeNullToLong(validVotes + invalidVotes);

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
					singleElectionRepository.getSumOfPublishedVotes(candidate.getCandidateConstituency().getId()));

			Long voted = changeNullToLong(singleElectionRepository.getVotesByCandidateId(candidate.getCandidateID()));

			Long allVotes = validVotesInConstituency + invalidVotesInConstituency;

			BigDecimal percentageOfVoted = checkForCorrectArithmetic(voted, validVotesInConstituency);

			BigDecimal percentageOfAllVotes = checkForCorrectArithmetic(voted, allVotes);

			if (candidate.getCandidateParty() != null) {
				party = candidate.getCandidateParty().getTitle();
			}

			SingleElectionResult singleElectionResult = new SingleElectionResult(candidate.getCandidateID(),
					candidate.getCandidateName(), candidate.getCandidateSurname(), party, voted, percentageOfVoted,
					percentageOfAllVotes, id);

			resultList.add(singleElectionResult);

		}
		Collections.sort(resultList, new SingleElectionResultsComperator());
		Collections.reverse(resultList);
		return resultList;
	}

	public List<SingleElectionResult> getSingleElectionResultInDistrict(Integer id) {

		Integer constituencyId = districtRepository.getConstituencyIdByDistrictId(id);

		List<Candidate> candidateList = candidateRepository.findCandidatesByConstituencyId(constituencyId);

		List<SingleElectionResult> resultList = new ArrayList<>();

		for (Candidate candidate : candidateList) {

			String candidateName = candidate.getCandidateName();
			String candidateSurname = candidate.getCandidateSurname();
			String party = "Išsikelęs pats";

			if (candidate.getCandidateParty() != null) {
				party = candidate.getCandidateParty().getTitle();
			}

			Long votesForCandidate = singleElectionRepository.getVotesOfCandidate(candidate.getCandidateID(), id);

			Long validVotes = changeNullToLong(singleElectionRepository.getNumberOfDistrictvalidVotes(id));

			Long invalidVotes = changeNullToLong(corruptedVotesRepository.getCorruptedVotesByDistrict(id));

			Long allVotes = validVotes + invalidVotes;

			BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(votesForCandidate, validVotes);

			BigDecimal percentageOfAllVotes = checkForCorrectArithmetic(votesForCandidate, allVotes);

			SingleElectionResult singleElectionResult = new SingleElectionResult(candidate.getCandidateID(),
					candidateName, candidateSurname, party, votesForCandidate, percentageOfValidVotes,
					percentageOfAllVotes, constituencyId);

			resultList.add(singleElectionResult);

		}

		return resultList;
	}

	public ElectionDetails getSingleElectionDetails() {

		Long numberOfDistricts = changeNullToLong(districtRepository.countAllDistricts());

		Long numberOfConstituencies = changeNullToLong(constituencyRepository.countAllConstituencies());

		Long numberOfVoters = changeNullToLong(districtRepository.countAllVoters());

		Long validVotes = changeNullToLong(singleElectionRepository.getAllVotes());

		Long invalidVotes = changeNullToLong(corruptedVotesRepository.getAllSingleCorruptedVotes());

		Long numberOfVotersWhoVoted = changeNullToLong(validVotes + invalidVotes);

		BigDecimal percentageOfVoters = checkForCorrectArithmetic(numberOfVotersWhoVoted, numberOfVoters);

		BigDecimal percentageOfInvalidVotes = checkForCorrectArithmetic(invalidVotes, numberOfVotersWhoVoted);

		BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(validVotes, numberOfVotersWhoVoted);

		ElectionDetails details = new ElectionDetails(numberOfDistricts, numberOfConstituencies, numberOfVoters,
				numberOfVotersWhoVoted, percentageOfVoters, invalidVotes, percentageOfInvalidVotes, validVotes,
				percentageOfValidVotes);

		return details;

	}

	public ElectionDistrictDetails getDistrictElectionDetails(Integer id) {

		District district = districtRepository.findDistrictById(id);

		Integer districtId = district.getId();

		String districtTitle = district.getTitle();

		Integer constituencyId = district.getConstituencyId();

		Constituency constituency = constituencyRepository.findConstituencyById(constituencyId);

		String constituencyTitle = constituency.getTitle();

		Long voters = changeNullToLong(district.getNumberOfVoters());

		Long validVotes = changeNullToLong(singleElectionRepository.getNumberOfpublishedVotes(districtId));

		Long invalidvotes = changeNullToLong(corruptedVotesRepository.getMultiCorruptedVotesInDistrict(districtId));

		Long allVotes = changeNullToLong(validVotes + invalidvotes);

		BigDecimal percentageOfVoted = checkForCorrectArithmetic(allVotes, voters);

		BigDecimal percentageOfInvalidVotes = checkForCorrectArithmetic(invalidvotes, allVotes);

		BigDecimal percentageOfValidVotes = checkForCorrectArithmetic(validVotes, allVotes);

		String updateDate = null;

		if (singleElectionRepository.getPublishedDate(districtId) != null) {
			updateDate = singleElectionRepository.getPublishedDate(districtId).toString();

		}

		ElectionDistrictDetails electionDistrictDetails = new ElectionDistrictDetails(districtId, districtTitle,
				constituencyTitle, constituencyId, voters, validVotes, invalidvotes, allVotes, percentageOfVoted,
				percentageOfInvalidVotes, percentageOfValidVotes, updateDate);

		return electionDistrictDetails;

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
