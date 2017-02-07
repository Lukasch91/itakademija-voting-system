package vs.representative.features.election.single.results;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SingleElectionConstituencyController {

	@Autowired
	SingleElectionConstitencyService singleElectionConstitencyService;

	@RequestMapping(value = "/api/constresults", method = RequestMethod.GET)
	public List<SingleElectionConstituencyResults> getConstituencyResults() {
		return singleElectionConstitencyService.singleElectionConstituencyResults();
	}

}
