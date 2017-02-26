package vs.representative.features.election.multi.results;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MultiElectionResultsController {

	@Autowired
	MultiElectionResultsService multiElectionResultsService;

	@RequestMapping(value = "/api/multicons", method = RequestMethod.GET)
	public List<MultiElectionConstituency> getConstituencyResults() {
		return multiElectionResultsService.getMultiElectionResults();
	}

}
