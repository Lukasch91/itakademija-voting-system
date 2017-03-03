package vs.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.admin_.party.Party;
import vs.admin_.party.PartyRepository;
import vs.admin_.party.PartyService;
import vs.admin_.representative.Representative;
@RestController
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@RequestMapping(value = "/api/admin", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[REPRES] - Get Admin")
	public List<Admin> findAdmin() {
		return adminRepository.findAdmin();
	}
	
	@RequestMapping(value = "/api/admin", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Find By LoginName")
	public Admin findByLogin( @RequestBody String loginName) {
		return adminRepository.findByLoginName(loginName);		
	}
}
