package vs.public_.consolidated.results;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.admin_.representative.RepresentativeRepository;

@RestController
@CrossOrigin
public class ConsolidatedResultsController {
	
	private static final Logger log = Logger.getLogger(ConsolidatedResultsController.class.getName());

	@Autowired
	ConsolidatedResultsService consolidatedResultsService;

	@RequestMapping(value = "/api/PUBLIC/consolidatedResults/", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<ConsolidatedResults> getConsolidatedResults() {
		log.info("||--> was used");
		return consolidatedResultsService.getSortedPartyList();

	}

	@RequestMapping(value = "/api/PUBLIC/members/", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MemberOfParliament> getMembersOfParliament() {
		return consolidatedResultsService.getSortedMemberOfParlList();
	}
}
