package vs.admin.features.candidate.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api; //swagger
import io.swagger.annotations.ApiOperation; //swagger
import vs.admin.features.candidate.model.storage.StorageService;

@RestController
@Api // swagger
public class CandidateController {

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private CandidateService candidateService;

	@Autowired
	private CandidateRepository candidateRepository;

	@RequestMapping(value = "/api/candidate", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all undeleted candidates")
	public List<Candidate> findAllCandidates() {
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
	
	@RequestMapping(value = "/api/candidateConstituency/{constituencyId}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get candidate by Constituency id")
	public List<Candidate> getCandidateByConstituencyId(@PathVariable("constituencyId") Integer id) {
		return candidateRepository.findCandidatesByConstituencyId(id);
	}

	@RequestMapping(value = "/api/candidate/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete candidate by id (adds deletion date)")
	public void deleteCandidateById(@PathVariable("id") Integer id) {
		candidateRepository.deleteCandidateById(id);
	}
	
	
	@RequestMapping(value = "/api/candidateConstituency/{constituencyId}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete candidate by Constituency id (adds deletion date)")
	public void deleteCandidateByConstituencyId(@PathVariable("constituencyId") Integer id) {
		candidateRepository.deleteCandidatesByConstituencyId(id);
	}
	/* ===========================================================File=== */

	@RequestMapping(value = "/api/districtcandidatesFILE", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Upload district candidates CSV")
	public String districtCandidatesCSV(@RequestParam("file") MultipartFile file, @RequestHeader Integer constituencyId) {

		storageService.store(file);
		
		candidateService.setCandidatesConstituency(constituencyId);
		candidateService.setCandidatesData(storageService.returnStoredFile(file));
		candidateService.saveDistrictCandidates();
		
		String aaa = storageService.returnStoredFile(file);
		
		storageService.deleteFile(file);

		return aaa;
	}
	
	@RequestMapping(value = "/api/partycandidatesFILE", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Upload party candidates CSV")
	public String partyCandidatesCSV(@RequestParam("file") MultipartFile file, @RequestHeader Integer partyId) {

		storageService.store(file);
		
		candidateService.setCandidatesParty(partyId);
		candidateService.setCandidatesData(storageService.returnStoredFile(file));
		candidateService.savePartyCandidates();
		
		String aaa = storageService.returnStoredFile(file);
		
		storageService.deleteFile(file);

		return aaa;
	}

	/* ===========================================================File=== */
}
