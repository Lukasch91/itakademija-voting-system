package vs.public_.consolidated.results;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.admin_.candidate.Candidate;
import vs.admin_.representative.RepresentativeRepository;
import vs.public_.multi.results.MultiElectionResults;

@RestController
@CrossOrigin
public class ConsolidatedResultsController {

	private static final Logger log = Logger.getLogger(ConsolidatedResultsController.class.getName());

	@Autowired
	ConsolidatedResultsService consolidatedResultsService;

	@Autowired
	ConsolidatetPartyListService consolidatetPartyListService;

	@RequestMapping(value = "/api/PUBLIC/consolidatedResults/", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<ConsolidatedResults> getConsolidatedResults() {
		log.info("||--> was used");
		return consolidatetPartyListService.getSortedPartyList();

	}

	@RequestMapping(value = "/api/PUBLIC/members/", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MemberOfParliament> getMembersOfParliament() {
		return consolidatedResultsService.getSortedMemberOfParlList();
	}

	@RequestMapping(value = "/api/PUBLIC/blabla/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<Candidate> getMembersOfParliaments(@PathVariable Integer id) {
		return consolidatedResultsService.getMultiMembersList(id);

	}

}
