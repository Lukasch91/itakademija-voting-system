package vs.representative.features.single.election;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value = "/api/singleelection", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all  single Election results")
	public List<SingleElection>findAllsingleElectionResults() {
			return singleElectionRepository.findAllSingleElectionResults();
	}
	
	@RequestMapping(value = "/api/singleelection", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Create single election result")
	public SingleElection createSingleElectionResult(@RequestBody SingleElection singleElection) {
		return singleElectionRepository.saveSingleElection(singleElection);
	}
	
	@RequestMapping(value = "/api/singleelection/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Find single election result by id")
	public SingleElection getSingleElectionResultsById(@PathVariable("id") Integer id) {
		return singleElectionRepository.findSingleElectionById(id);
	}
	
	@RequestMapping(value = "/api/singleelection/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "delete single election result by id(adds deletion date)")
	public void deleteSingleElectionResultById(@PathVariable("id") Integer id) {
		singleElectionRepository.deleteSingleElectionById(id);
	}	
	
	@RequestMapping(value = "/api/singleelectiondistrict/{id}", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Publish single election results by district id")
	public void publishSingleElectionResultsByDistrictId(@PathVariable("id") Integer id) {
		singleElectionRepository.publishSingleElectionResultsByDistrictId(id);
	}

	@RequestMapping(value = "/api/singleelectiondistrict/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete single election results by district id(adds date)")
	public void deleteSingleElectionResultsByDistrictId(@PathVariable("id") Integer id) {
		singleElectionRepository.deleteSingleElectionResultsByDistrictId(id);
	}

	@RequestMapping(value = "/api/singleelectiondistrictREAL/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete single election results by district id (REAL DELETE!)")
	public void deleteSingleElectionResultsByDistrictIdREAL(@PathVariable("id") Integer id) {
		singleElectionRepository.deleteSingleElectionResultsByDistrictIdREAL(id);
	}

}
	
	
	