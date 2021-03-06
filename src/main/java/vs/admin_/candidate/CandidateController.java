package vs.admin_.candidate;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
public class CandidateController {

	private static final Logger log = Logger.getLogger(CandidateController.class.getName());

	@Autowired
	private CandidateCreateService candidateService;
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private CandidateValidationService candidateValidationService;

	@RequestMapping(value = "/api/PUBLIC/candidate", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[PUBLIC] - Get all undeleted candidates")
	public List<Candidate> findAllCandidates() {
		log.debug("was used.");
		return candidateRepository.findAllUndeletedCandidates();
	}

	@RequestMapping(value = "/api/REPRES/candidate/{districtId}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[REPRES] -Get all undeleted candidates by districtId")
	public List<Candidate> findAllCandidatesByDistrictId(@PathVariable("districtId") Integer districtId) {
		log.debug("was used. District id: " + districtId);
		return candidateService.findAllCandidatesByDistrictId(districtId);
	}

	@RequestMapping(value = "/api/ADMIN/candidate", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "[FORTEST - ADMIN] - Create or update candidate")
	public Candidate createOrUpdateCandidate(@RequestBody Candidate candidate) {
		log.debug("was used. Candidate: " + candidate.getCandidateSurname());
		return candidateRepository.createOrUpdateCandidate(candidate);
	}

	@RequestMapping(value = "/api/ADMIN/candidateConstituency/{constituencyId}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[ADMIN] - Get candidate by Constituency id")
	public List<Candidate> getCandidateByConstituencyId(@PathVariable("constituencyId") Integer id) {
		log.debug("was used. Constituency id: " + id);
		return candidateRepository.findCandidatesByConstituencyId(id);
	}

	@RequestMapping(value = "/api/ADMIN/candidateConstituency/{constituencyId}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[ADMIN] - Delete candidate by Constituency id (adds deletion date)")
	public void deleteCandidateByConstituencyId(@PathVariable("constituencyId") Integer id) {
		log.debug("was used. Constituency id: " + id);
		candidateRepository.deleteCandidatesByConstituencyId(id);
	}

	@RequestMapping(value = "/api/ADMIN/candidateParty/{partyId}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[ADMIN] - Get candidate by Party id")
	public List<Candidate> getCandidateByPartyId(@PathVariable("partyId") Integer id) {
		log.debug("was used. Party id: " + id);
		return candidateRepository.findCandidatesByPartyId(id);
	}

	@RequestMapping(value = "/api/ADMIN/candidateParty/{partyId}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[ADMIN] - Delete candidate by Party id (adds deletion date)")
	public void deleteCandidateByPartyId(@PathVariable("partyId") Integer id) {
		log.debug("was used. Party id: " + id);
		candidateRepository.deleteCandidatesByPartyId(id);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/api/ADMIN/constituencycsv", method = RequestMethod.POST)
	@ApiOperation(value = "[ADMIN] - Upload constituency candidates CSV")
	public ResponseEntity constituencyCSV(@RequestBody CandidateDataPackage data) {
		log.debug("was used.");
		return candidateValidationService.validateSaveConstituencyData(data);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/api/ADMIN/partycsv", method = RequestMethod.POST)
	@ApiOperation(value = "[ADMIN] - Upload party candidates CSV")
	public ResponseEntity partyCSV(@RequestBody CandidateDataPackage data) {
		log.debug("was used.");
		return candidateValidationService.validateSavePartyData(data);
	}
}
