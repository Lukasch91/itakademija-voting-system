package vs.representative_.multielection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class MultiElectionController {

	@Autowired
	private MultiElectionRepository multiElectionRepository;
	@Autowired
	private MultiElectionCreateService multiElectionCreateService;

	@RequestMapping(value = {"/api/REPRES/reg-votes-multi", "/api/ADMIN/reg-votes-multi"}, method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[REPRES] - Get all  Multi Election results")
	public List<MultiElection> findAllElection() {
		return multiElectionRepository.findAllElection();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/api/REPRES/reg-votes-multi", method = RequestMethod.POST)
	@ApiOperation(value = "[REPRES] - Create multi election result.", notes = "Data: [{\"id\": null," + " \"party\": {\"id\": 1},"
			+ "\"district\": { \"id\": 3}," + " \"votes\": 99}]")
	public ResponseEntity createOrUpdateMulti(@RequestBody List<MultiVotesPackage> multiVotesPackage) {
		return multiElectionCreateService.validatePackage(multiVotesPackage);
	}

	@RequestMapping(value = "/api/ADMIN/reg-votes-multi/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Get Multi Election results by id")
	public MultiElection getMultiElectionById(@PathVariable("id") Integer id) {
		return multiElectionRepository.findMultiElectionById(id);
	}

	@RequestMapping(value = "/api/ADMIN/reg-votes-multi/dis/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Get Multi Election results by District id")
	public List<MultiElection> getMultiElectionByDistrictId(@PathVariable("id") Integer id) {
		return multiElectionRepository.findMultiElectionByDistrictId(id);
	}

	@RequestMapping(value = "/api/ADMIN/reg-votes-multi/{id}", method = RequestMethod.PUT)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[UNUSED - ADMIN] - Delete Multi Election results by id")
	public void deteleMultiElectionById(@PathVariable("id") Integer id) {
		multiElectionRepository.deleteMultiElection(id);
	}

	@RequestMapping(value = "/api/ADMIN/multielectiondistrict/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[ADMIN] - Delete multi election results by district id")
	public void deleteMultiElectionResultsByDistrictId(@PathVariable("id") Integer id) {
		multiElectionRepository.deleteMultiElectionResultsByDistrictId(id);
	}

	@RequestMapping(value = "/api/ADMIN/multielectiondistrict/{id}", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "[ADMIN] - Publish multi election results by district id")
	public void publishMultiElectionResultsByDistrictId(@PathVariable("id") Integer id) {
		multiElectionRepository.publishMultiElectionResultsByDistrictId(id);
	}
}
