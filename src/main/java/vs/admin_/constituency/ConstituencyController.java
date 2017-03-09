package vs.admin_.constituency;

import java.util.List;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.admin_.candidate.CandidateRepository;

@RestController
@CrossOrigin
public class ConstituencyController {

	private static final Logger log = Logger.getLogger(ConstituencyController.class.getName());
	
	@Autowired
	private ConstituencyRepository constituencyRepository;
	
	@Autowired
	private ConstituencyService constituencyService;

	@RequestMapping(value = "/api/ADMIN/constituency", method = RequestMethod.GET)
	@ApiOperation(value = "[ADMIN] - ")
	public List<Constituency> findAllConstituencies() {
		log.debug("ConstituencyController - findAllConstituencies was used.");
		return constituencyRepository.findAllConstituencies();
	}

	@RequestMapping(value = "/api/ADMIN/constituencyExtended", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[ADMIN] - Find Constituencies and add Number of candidates")
	public List<ConstituencyExtension> findAllConstituenciesExtended() {
		log.debug("ConstituencyController - findAllConstituenciesExtended was used.");
		return constituencyService.findAllConstituenciesExtended();
	}

	@RequestMapping(value = "/api/ADMIN/constituency", method = RequestMethod.POST)
	@ApiOperation(value = "[ADMIN] - ")
	public Constituency createOrUpdateConstituency(@Valid @RequestBody Constituency con) {
		log.debug("ConstituencyController - createOrUpdateConstituency was used.");
		return constituencyRepository.saveOrUpdate(con);
	}

	@RequestMapping(value = "/api/ADMIN/constituency/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[ADMIN] - ")
	public Constituency getConstituencyById(@PathVariable("id") Integer id) {
		log.debug("ConstituencyController - getConstituencyById was used. Id : " + id);
		return constituencyRepository.findConstituencyById(id);
	}

	@RequestMapping(value = "/api/ADMIN/constituency/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "[ADMIN] - ")
	public void deteleConstituencyById(@PathVariable("id") Integer id) {
		log.debug("ConstituencyController - deteleConstituencyById was used. Id : " + id);
		constituencyRepository.deleteConstituency(id);
	}
}
