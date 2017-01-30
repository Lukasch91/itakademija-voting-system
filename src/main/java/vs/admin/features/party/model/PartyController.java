package vs.admin.features.party.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PartyController {

	@Autowired
	private PartyRepository partyRepository;

	@RequestMapping(value = "/api/party", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public List<Party> findAllParties() {
		return partyRepository.findAllParties();
	}

	@RequestMapping(value = "/api/party", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public Party createOrUpdateParty(@RequestBody Party party) {
		return partyRepository.saveOrUpdate(party);
	}
	@RequestMapping(value = "/api/party/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public Party getPartyById(@PathVariable("id") Integer id) {
		return partyRepository.findPartyById(id);
	}
	
	@RequestMapping(value = "/api/party/{id}", method = RequestMethod.PUT)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	public void detelePartyById(@PathVariable("id") Integer id) {
		partyRepository.deleteParty(id);
	}
}
