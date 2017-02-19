package vs.representative.features.corrupted.votes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class CorruptedVotesController {

	@Autowired
	private CorruptedVotesRepository corruptedVotesRepository;

	@RequestMapping(value = "/api/invalid-votes", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all  invalid votes")
	public List<CorruptedVotes> findAllVotes() {
		return corruptedVotesRepository.findAllCorruptedVotes();
	}

	@RequestMapping(value = "/api/invalid-votes", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Create create invalid votes.", notes = "Data: {\"id\": null,"
			+ " \"type\": true,"
			+ "\"district\": { \"id\": 1},"
			+ " \"votes\": 123}")
	public void createOrUpdateCorrupted(@Valid @RequestBody CorruptedVotes corruptedVotes) {
		corruptedVotesRepository.saveOrUpdate(corruptedVotes);
	}

	@RequestMapping(value = "/api/invalid-votes/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get invalid votes by id")
	public CorruptedVotes getCorruptedVotesById(@PathVariable("id") Integer id) {
		return corruptedVotesRepository.findCorruptedVotesById(id);
	}
	
	

	@RequestMapping(value = "/api/invalid-votes/type/{typeMulti}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all  invalid votes( boolean)")
	public List<CorruptedVotes> findAllCorruptedVotes(@PathVariable("typeMulti") Boolean typeMulti) {
		return corruptedVotesRepository.GetCorruptedVotesByType(typeMulti);
	}

	@RequestMapping(value = "/api/invalid-votes/{id}", method = RequestMethod.PUT)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete invalid votes by id")
	public void deteleCorruptedVotesById(@PathVariable("id") Integer id) {
		corruptedVotesRepository.deleteCorruptedVotes(id);
	}

	@RequestMapping(value = "/api/inalidvotesistrict/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete delete invalid votes by district id")
	public void deleteCorruptedVotesByDistrictId(@PathVariable("id") Integer id) {
		corruptedVotesRepository.deleteCorruptedVotesByDistrictId(id);
	}

	@RequestMapping(value = "/api/inalidvotesistrict/{id}", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Publish invalid votes by district id")
	public void publishCorruptedVotesByDistrictId(@PathVariable("id") Integer id) {
		corruptedVotesRepository.publishCorruptedVotesByDistrictId(id);
	}
}
