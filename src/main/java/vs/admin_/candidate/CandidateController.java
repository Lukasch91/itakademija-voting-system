package vs.admin_.candidate;

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
import vs.utils_.storage.StorageService;

@RestController
@Api
public class CandidateController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private CandidateRepository candidateRepository;

	@RequestMapping(value = "/api/PUBLIC/candidate", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[PUBLIC] - Get all undeleted candidates")
	public List<Candidate> findAllCandidates() {
		return candidateRepository.findAllUndeletedCandidates();
	}
	
	@RequestMapping(value = "/api/REPRES/candidate/{districtId}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[REPRES] -Get all undeleted candidates by districtId")
	public List<Candidate> findAllCandidatesByDistrictId(@PathVariable("districtId") Integer districtId) {
		return candidateService.findAllCandidatesByDistrictId(districtId);
	}

	@RequestMapping(value = "/api/ADMIN/candidate", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "[FORTEST - ADMIN] - Create or update candidate")
	public Candidate createOrUpdateCandidate(@RequestBody Candidate candidate) {
		return candidateRepository.createOrUpdateCandidate(candidate);
	}

	@RequestMapping(value = "/api/ADMIN/candidateConstituency/{constituencyId}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[ADMIN] - Get candidate by Constituency id")
	public List<Candidate> getCandidateByConstituencyId(@PathVariable("constituencyId") Integer id) {
		return candidateRepository.findCandidatesByConstituencyId(id);
	}
	
	@RequestMapping(value = "/api/ADMIN/candidateConstituency/{constituencyId}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[ADMIN] - Delete candidate by Constituency id (adds deletion date)")
	public void deleteCandidateByConstituencyId(@PathVariable("constituencyId") Integer id) {
		candidateRepository.deleteCandidatesByConstituencyId(id);
	}
	
	@RequestMapping(value = "/api/ADMIN/candidateParty/{partyId}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[ADMIN] - Get candidate by Party id")
	public List<Candidate> getCandidateByPartyId(@PathVariable("partyId") Integer id) {
		return candidateRepository.findCandidatesByPartyId(id);
	}

	@RequestMapping(value = "/api/ADMIN/candidateParty/{partyId}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[ADMIN] - Delete candidate by Party id (adds deletion date)")
	public void deleteCandidateByPartyId(@PathVariable("partyId") Integer id) {
		candidateRepository.deleteCandidatesByPartyId(id);
	}
	
	/* ===========================================================File=== */

	@RequestMapping(value = "/api/ADMIN/districtcandidatesFILE", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "[ADMIN] - Upload district candidates CSV")
	public String districtCandidatesCSV(@RequestParam("file") MultipartFile file,
			@RequestHeader Integer constituencyId) {

		storageService.store(file);

		candidateService.setCandidatesConstituency(constituencyId);
		candidateService.setCandidatesData(storageService.returnStoredFile(file));
		candidateService.saveDistrictCandidates();

		String aaa = storageService.returnStoredFile(file);

		storageService.deleteFile(file);

		return aaa;
	}

	@RequestMapping(value = "/api/ADMIN/partycandidatesFILE", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "[ADMIN] - Upload party candidates CSV")
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
