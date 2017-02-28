package vs.public_.multi.results;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class MultiElectionResultsController {

	@Autowired
	MultiElectionResultsService multiElectionResultsService;

	@RequestMapping(value = "/api/PUBLIC/multicons", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionResults> getConstituencyResults() {
		return multiElectionResultsService.getMultiElectionResults();
	}

	@RequestMapping(value = "/api/PUBLIC/multiconslist", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionConstituencyList> getConstituencyResultsList() {
		return multiElectionResultsService.getMultiElectionConstituencyList();
	}

	@RequestMapping(value = "/api/PUBLIC/multidistlist/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionDistrictList> getDistrictsResultsList(@PathVariable Integer id) {
		return multiElectionResultsService.getResultsOfDistricts(id);
	}
	
	@RequestMapping(value = "/api/PUBLIC/multidis/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionResults> getPartiesDistrictResultsList(@PathVariable Integer id) {
		return multiElectionResultsService.getDistrictPartiesResults(id);
	}

	@RequestMapping(value = "/api/PUBLIC/multicons/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionResults> getPartiesConsResultsList(@PathVariable Integer id) {
		return multiElectionResultsService.getConstituencyPartiesResults(id);
	}
	
	
}
