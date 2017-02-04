package vs.admin.features.party.model;

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

	@RequestMapping(value = "/api/party", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get all  Parties")
	public List<Party> findAllParties() {
		return partyRepository.findAllParties();
	}

	@RequestMapping(value = "/api/party", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Save or update Party")
	public Party createOrUpdateParty(@Valid @RequestBody Party party) {
		return partyRepository.saveOrUpdate(party);
	}
	@RequestMapping(value = "/api/party/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Find Party by id")
	public Party getPartyById(@PathVariable("id") Integer id) {
		return partyRepository.findPartyById(id);
	}
	
	@RequestMapping(value = "/api/party/{id}", method = RequestMethod.PUT)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete Party by id")
	public void detelePartyById(@PathVariable("id") Integer id) {
		partyRepository.deleteParty(id);
	}
}
