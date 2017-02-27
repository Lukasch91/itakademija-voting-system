package vs.admin_.constituency;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class ConstituencyController {

	@Autowired
	private ConstituencyRepository constituencyRepository;
	
	@Autowired
	private ConstituencyService constituencyService;

	@RequestMapping(value = "/api/constituency", method = RequestMethod.GET)
	public List<Constituency> findAllConstituencies() {
		return constituencyRepository.findAllConstituencies();
	}

	@RequestMapping(value = "/api/constituencyExtended", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Find Constituencies and add Number of candidates")
	public List<ConstituencyExtension> findAllConstituenciesExtended() {
		return constituencyService.findAllConstituenciesExtended();
	}

	@RequestMapping(value = "/api/constituency", method = RequestMethod.POST)
	public Constituency createOrUpdateConstituency(@Valid @RequestBody Constituency con) {
		return constituencyRepository.saveOrUpdate(con);
	}

	@RequestMapping(value = "/api/constituency/{id}", method = RequestMethod.GET)
	public Constituency getConstituencyById(@PathVariable("id") Integer id) {
		return constituencyRepository.findConstituencyById(id);
	}

	@RequestMapping(value = "/api/constituency/{id}", method = RequestMethod.PUT)
	public void deteleConstituencyById(@PathVariable("id") Integer id) {
		constituencyRepository.deleteConstituency(id);
	}

}
