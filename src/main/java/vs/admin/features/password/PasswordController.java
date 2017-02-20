package vs.admin.features.password;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.representative.features.corrupted.votes.CorruptedVotes;
import vs.representative.features.corrupted.votes.CorruptedVotesRepository;

@RestController
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	@RequestMapping(value = "/api/password", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get Password")
	public @ResponseBody String getGeneratedPassword() {

		String password = passwordService.PassGen();
		return password;
	}

}
