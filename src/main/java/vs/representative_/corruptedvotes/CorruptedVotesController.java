package vs.representative_.corruptedvotes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class CorruptedVotesController {

	@Autowired
	private CorruptedVotesRepository corruptedVotesRepository;

	@RequestMapping(value = "/api/ADMIN/invalid-votes", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Get all  invalid votes")
	public List<CorruptedVotes> findAllVotes() {
		return corruptedVotesRepository.findAllCorruptedVotes();
	}

	@RequestMapping(value = "/api/ADMIN/invalid-votes", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Create create invalid votes.", notes = "Data: {\"id\": null,"
			+ " \"typeMulti\": true,"
			+ "\"district\": { \"id\": 1},"
			+ " \"votes\": 123}")
	public void createOrUpdateCorrupted(@RequestBody CorruptedVotes corruptedVotes) {
		corruptedVotesRepository.saveOrUpdate(corruptedVotes);
	}
	
	@RequestMapping(value = "/api/ADMIN/invalid-votes/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Get invalid votes by id")
	public CorruptedVotes getCorruptedVotesById(@PathVariable("id") Integer id) {
		return corruptedVotesRepository.findCorruptedVotesById(id);
	}
	
	@RequestMapping(value = "/api/ADMIN/invalid/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Get invalid votes by id")
	public Long getCorruptVotesById(@PathVariable("id") Integer id) {
		return corruptedVotesRepository.getCorruptedVotesInConstituency(id).longValue();
	}
	
	@RequestMapping(value = "/api/REPRES/invalid-votes/type/{typeMulti}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[REPRES] - Get all  invalid votes( boolean)")
	public List<CorruptedVotes> findAllCorruptedVotes(@PathVariable("typeMulti") Boolean typeMulti) {
		return corruptedVotesRepository.GetCorruptedVotesByType(typeMulti);
	}

	@RequestMapping(value = "/api/ADMIN/invalid-votes/{id}", method = RequestMethod.PUT)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[UNUSED - ADMIN] - Delete invalid votes by id")
	public void deteleCorruptedVotesById(@PathVariable("id") Integer id) {
		corruptedVotesRepository.deleteCorruptedVotes(id);
	}

	@RequestMapping(value = "/api/ADMIN/inalidvotesistrict/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[UNUSED - ADMIN] - Delete delete invalid votes by district id")
	public void deleteCorruptedVotesByDistrictId(@PathVariable("id") Integer id, @PathVariable("typeMulti") Boolean typeMulti) {
		corruptedVotesRepository.deleteCorruptedVotesByDistrictId(id, typeMulti);
	}

	@RequestMapping(value = "/api/ADMIN/inalidvotesistrict/{id}/{typeMulti}", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "[UNUSED - ADMIN] - Publish invalid votes by district id")
	public void publishCorruptedVotesByDistrictId(@PathVariable("id") Integer id, @PathVariable("typeMulti") Boolean typeMulti) {
		corruptedVotesRepository.publishCorruptedVotesByDistrictId(id, typeMulti);
	}
}
