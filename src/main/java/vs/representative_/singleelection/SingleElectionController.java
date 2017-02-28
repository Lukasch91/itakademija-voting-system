package vs.representative_.singleelection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api; //swagger
import io.swagger.annotations.ApiOperation; //swagger

@RestController
@Api // swagger
public class SingleElectionController {
	@Autowired
	private SingleElectionRepository singleElectionRepository;
	@Autowired
	private SingleElectionCreateService singleElectionCreateService;
	
	@RequestMapping(value = "/api/REPRES/singleelection", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[REPRES] - Get all  single Election results")
	public List<SingleElection> findAllsingleElectionResults() {
		return singleElectionRepository.findAllSingleElectionResults();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/api/REPRES/singleelection", method = RequestMethod.POST)
	@ApiOperation(value = "[REPRES] - Create single election result or results. Accepts array.", notes = "Data: ["
+ "{\"singleId\": null, \"singleCandidate\": {\"candidateID\": 1}, \"singleDistrict\": { \"id\": 3}, \"singleVotes\": 99}, "
+ "{\"singleId\": null, \"singleCandidate\": {\"candidateID\": 2}, \"singleDistrict\": { \"id\": 4}, \"singleVotes\": 33}"
+ "]")
	public ResponseEntity createSingleElectionResult(@RequestBody List<SingleVotesPackage> singleVotesPackage) {
		return singleElectionCreateService.validatePackage(singleVotesPackage);
	}

	@RequestMapping(value = "/api/ADMIN/singleelection/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Find single election result by id")
	public SingleElection getSingleElectionResultsById(@PathVariable("id") Integer id) {
		return singleElectionRepository.findSingleElectionById(id);
	}

	@RequestMapping(value = "/api/ADMIN/singleelection/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[UNUSED - ADMIN] - Delete single election result by id(adds deletion date)")
	public void deleteSingleElectionResultById(@PathVariable("id") Integer id) {
		singleElectionRepository.deleteSingleElectionById(id);
	}
	
	@RequestMapping(value = "/api/ADMIN/singleelectiondistrict/{id}", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "[ADMIN] - Publish single election results by district id")
	public void publishSingleElectionResultsByDistrictId(@PathVariable("id") Integer id) {
		singleElectionRepository.publishSingleElectionResultsByDistrictId(id);
	}

	@RequestMapping(value = "/api/ADMIN/singleelectiondistrict/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[ADMIN] - Delete single election results by district id(adds date)")
	public void deleteSingleElectionResultsByDistrictId(@PathVariable("id") Integer id) {
		singleElectionRepository.deleteSingleElectionResultsByDistrictId(id);
	}

	@RequestMapping(value = "/api/ADMIN/singleelectiondistrictREAL/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[UNUSED - ADMIN] - Delete single election results by district id (REAL DELETE!)")
	public void deleteSingleElectionResultsByDistrictIdREAL(@PathVariable("id") Integer id) {
		singleElectionRepository.deleteSingleElectionResultsByDistrictIdREAL(id);
	}

	@RequestMapping(value = "/api/ADMIN/singleelectioncandidate/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Find single election result by id")
	public Long getCandidateVotes(@PathVariable("id") Integer id) {
		return singleElectionRepository.getVotesByCandidateId(id);
	}

}
