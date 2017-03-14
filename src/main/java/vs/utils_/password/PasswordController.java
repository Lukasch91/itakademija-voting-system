package vs.utils_.password;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class PasswordController {

	private static final Logger log = Logger.getLogger(PasswordController.class.getName());
	
	@Autowired
	private PasswordService passwordService;

	@RequestMapping(value = "/api/ADMIN/password/gen", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Get Password")
	public @ResponseBody ArrayList<String> getGeneratedPassword() {
		log.debug("PasswordController - getGeneratedPassword was used!");
		return passwordService.GeneratedPasswordList();
	}

	@RequestMapping(value = "/api/ADMIN/password/hash", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Hash Password")
	public String getHashedPassword(@RequestParam String p)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		log.debug("PasswordController - getHashedPassword was used!");
		return passwordService.PassHashing(p);
	}

/*	@RequestMapping(value = "/api/ADMIN/password/check", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Check Password")
	public boolean checkPassword(@RequestParam String loginName, @RequestParam String passwordToCheck)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return passwordService.PasswordCheck(loginName, passwordToCheck);
	}*/

}