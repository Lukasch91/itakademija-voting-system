package vs.admin.features.candidate.model;

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
public class CandidateController {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@RequestMapping(value = "/api/candidate", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all undeleted candidates")
	public List<Candidate>findAllCandidates() {
			return candidateRepository.findAllUndeletedCandidates();
	}

	@RequestMapping(value = "/api/candidate", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Create or update candidate")
	public Candidate createOrUpdateCandidate(@RequestBody Candidate candidate) {
		return candidateRepository.createOrUpdateCandidate(candidate);
	}
	
	@RequestMapping(value = "/api/candidate/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get candidate by id")
	public Candidate getCandidateById(@PathVariable("id") Integer id) {
		return candidateRepository.findCandidateById(id);
	}
	
	@RequestMapping(value = "/api/candidate/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete candidate by id (adds deletion date)")
	public void deleteCandidateById(@PathVariable("id") Integer id) {
		candidateRepository.deleteCandidateById(id);
	}
	
	
}


