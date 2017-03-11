package vs.admin_.representative;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import vs.CurrentUser;
import vs.admin.Admin;
import vs.admin_.party.PartyRepository;

@RestController
@Api
@CrossOrigin
public class RepresentativeController {

	private static final Logger log = Logger.getLogger(RepresentativeController.class.getName());
	
	@Autowired
	private RepresentativeRepository representativeRepository;

	@RequestMapping(value = "/api/ADMIN/representative", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Get all representatives")
	public List<Representative> findAllRepresentatives() {
		log.info("||--> was used");
		return representativeRepository.findAllRepresentatives();
	}

	@RequestMapping(value = "/api/ADMIN/representative", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "[ADMIN] - Create or update representative", notes = "Data: {\"districtId\": 13, "
			+ "\"email\": \"Sablonskis@gmail.com\", \"id\": null, \"loginName\": \"Sab\", "
			+ "\"name\": \"Sablonius\", \"password\": \"xxx\", \"surname\": \"SABLONSKIS\"}")
	public Representative createOrUpdateRepresentative(@Valid @RequestBody Representative representative) {
		log.info("||--> was used");
		return representativeRepository.saveOrUpdateRepresentative(representative);		
	}

	@RequestMapping(value = "/api/ADMIN/representative/{id}", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[ADMIN] - Get representative by id")
	public Representative getRepresentativeById(@PathVariable("id") Integer id) {
		log.info("||--> was used");
		return representativeRepository.findRepresentativeById(id);
	}

	@RequestMapping(value = "/api/ADMIN/representative/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	@ApiOperation(value = "[ADMIN] - Delete representative by id(realDelete)")
	public void deleteRepresentativeById(@PathVariable("id") Integer id) {
		log.info("||--> was used");
		representativeRepository.deleteRepresentative(id);
	}
	
	@RequestMapping(value = "/api/ADMIN/representative/find", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Find By LoginName")
	public Representative findByLogin( @RequestBody String loginName) {
		log.info("||--> was used");
		return representativeRepository.findByLoginName(loginName);		
	}
	
	@RequestMapping(value = "/api/REPRES/changepass", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Change password")
	public void changePassword(@CurrentUser Representative representative, @RequestParam String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		log.info("||--> was used");
		representativeRepository.changePassword(representative, password);
	}
	
}
