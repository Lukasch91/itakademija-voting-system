package vs.admin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.CurrentUser;

@RestController
public class AdminController {
	private static final Logger log = Logger.getLogger(AdminController.class.getName());

	@Autowired
	private AdminRepository adminRepository;

	@RequestMapping(value = "/api/admin", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get Admin")
	public List<Admin> findAdmin() {
		log.info("started, result:  " + adminRepository.findAdmin());
		return adminRepository.findAdmin();
	}

	@RequestMapping(value = "/api/ADMIN/createadmin", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	@ApiOperation(value = "Create or update admin")
	public Admin saveOrUpdateAdmin(@Valid @RequestBody Admin admin) {
		log.info("started.." );
		return adminRepository.saveOrUpdateAdmin(admin);
	}

	@RequestMapping(value = "/api/ADMIN/admin", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Find By LoginName")
	public Admin findByLogin(@RequestBody String loginName) {
		log.info("started, result: " + adminRepository.findByLoginName(loginName));
		return adminRepository.findByLoginName(loginName);
	}

	@RequestMapping(value = "/api/ADMIN/changepass", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Change password")
	public boolean changePassword(@CurrentUser Admin admin, @RequestParam String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		log.info("started... ");
		boolean check = adminRepository.changePassword(admin, password);
		if (check){
			log.info("Password was changed!");
			return true;
		
		}
		log.info("Password was to weak!");
		return false;
	}
}
