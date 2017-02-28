package vs.admin_.party;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
public class PartyController {

	@Autowired
	private PartyRepository partyRepository;
	@Autowired
	private PartyService partyService;

	@RequestMapping(value = "/api/REPRES/party", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[REPRES] - Get all  Parties")
	public List<Party> findAllParties() {
		return partyRepository.findAllParties();
	}

	@RequestMapping(value = "/api/ADMIN/party", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[ADMIN] - Save or update Party")
	public Party createOrUpdateParty(@Valid @RequestBody Party party) {
		return partyRepository.saveOrUpdate(party);
	}

	@RequestMapping(value = "/api/ADMIN/party/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Find Party by id")
	public Party getPartyById(@PathVariable("id") Integer id) {
		return partyRepository.findPartyById(id);
	}

	@RequestMapping(value = "/api/ADMIN/party/{id}", method = RequestMethod.PUT)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[ADMIN] - Delete Party by id")
	public void detelePartyById(@PathVariable("id") Integer id) {
		partyRepository.deleteParty(id);
	}

	@RequestMapping(value = "/api/ADMIN/partyExtended", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[ADMIN] - Find Parties and add Number of candidates")
	public List<PartyExtension> findAllPartiesExtended() {
		return partyService.findAllPartiesExtended();
	}

}
