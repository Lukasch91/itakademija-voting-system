package vs.public_.consolidated.results;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class ConsolidatedResultsController {

	@Autowired
	ConsolidatedResultsService consolidatedResultsService;

	@RequestMapping(value = "/api/PUBLIC/consolidatedResults/", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<ConsolidatedResults> getConsolidatedResults() {
		return consolidatedResultsService.getAllResults();
	}

}
