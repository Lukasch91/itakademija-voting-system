package vs.public_.multi.results;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MultiElectionResultsController {

	@Autowired
	MultiElectionResultsService multiElectionResultsService;

	@RequestMapping(value = "/api/multicons", method = RequestMethod.GET)
	public List<MultiElectionResults> getConstituencyResults() {
		return multiElectionResultsService.getMultiElectionResults();
	}

	@RequestMapping(value = "/api/multiconslist", method = RequestMethod.GET)
	public List<MultiElectionConstituencyList> getConstituencyResultsList() {
		return multiElectionResultsService.getMultiElectionConstituencyList();
	}

	@RequestMapping(value = "/api/multidistlist/{id}", method = RequestMethod.GET)
	public List<MultiElectionDistrictList> getDistrictsResultsList(@PathVariable Integer id) {
		return multiElectionResultsService.getResultsOfDistricts(id);
	}
	
	@RequestMapping(value = "/api/multidis/{id}", method = RequestMethod.GET)
	public List<MultiElectionResults> getPartiesDistrictResultsList(@PathVariable Integer id) {
		return multiElectionResultsService.getDistrictPartiesResults(id);
	}

	@RequestMapping(value = "/api/multicons/{id}", method = RequestMethod.GET)
	public List<MultiElectionResults> getPartiesConsResultsList(@PathVariable Integer id) {
		return multiElectionResultsService.getConstituencyPartiesResults(id);
	}
	
	
}
