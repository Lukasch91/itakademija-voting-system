package vs.representative.features.multi.election;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONArray;
import vs.utils.hibernate.validators.multiElection.MEValidationMessages;

@RestController
public class MultiElectionController {

	@Autowired
	private MultiElectionRepository multiElectionRepository;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@RequestMapping(value = "/api/reg-votes-multi", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all  Multi Election results")
	public List<MultiElection> findAllElection() {
		return multiElectionRepository.findAllElection();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/api/reg-votes-multi", method = RequestMethod.POST)
	@ApiOperation(value = "Create multi election result.", notes = "Data: [{\"id\": null," + " \"party\": {\"id\": 1},"
			+ "\"district\": { \"id\": 3}," + " \"votes\": 99}]")
	public ResponseEntity createOrUpdateMulti(@RequestBody List<MultiElection> multiElections) {
		JSONArray jsonArray = new JSONArray();

		for (MultiElection multiElection : multiElections) {
			if (validator.validate(multiElection).isEmpty()) {
			} else {
				Set<ConstraintViolation<MultiElection>> constraintViolations = validator.validate(multiElection);
				MEValidationMessages mEVM = new MEValidationMessages();
				
				mEVM.setPartyId(multiElection.getParty().getId());

				for (ConstraintViolation<MultiElection> constraintViolation : constraintViolations) {
					mEVM.setOneMessage(constraintViolation.getMessage());
				}
				jsonArray.add(mEVM);
			}
		}

		if (jsonArray.isEmpty()) {
			multiElectionRepository.saveOrUpdate(multiElections);
			return ResponseEntity.status(HttpStatus.OK).body(jsonArray);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonArray);
		}
	}

	@RequestMapping(value = "/api/reg-votes-multi/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get Multi Election results by id")
	public MultiElection getMultiElectionById(@PathVariable("id") Integer id) {
		return multiElectionRepository.findMultiElectionById(id);
	}

	@RequestMapping(value = "/api/reg-votes-multi/dis/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get Multi Election results by District id")
	public List<MultiElection> getMultiElectionByDistrictId(@PathVariable("id") Integer id) {
		return multiElectionRepository.findMultiElectionByDistrictId(id);
	}

	@RequestMapping(value = "/api/reg-votes-multi/{id}", method = RequestMethod.PUT)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Multi Election results by id")
	public void deteleMultiElectionById(@PathVariable("id") Integer id) {
		multiElectionRepository.deleteMultiElection(id);
	}

	@RequestMapping(value = "/api/multielectiondistrict/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete multi election results by district id")
	public void deleteMultiElectionResultsByDistrictId(@PathVariable("id") Integer id) {
		multiElectionRepository.deleteMultiElectionResultsByDistrictId(id);
	}

	@RequestMapping(value = "/api/multielectiondistrict/{id}", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Publish multi election results by district id")
	public void publishMultiElectionResultsByDistrictId(@PathVariable("id") Integer id) {
		multiElectionRepository.publishMultiElectionResultsByDistrictId(id);
	}
}
