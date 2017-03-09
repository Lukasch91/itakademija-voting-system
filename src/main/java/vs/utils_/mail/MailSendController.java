package vs.utils_.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
public class MailSendController {

	@Autowired
	private MailSendService mailSendService;

	@RequestMapping(value = "/api/ADMIN/mail", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Sending mail to representative")
	public void MailSending(@RequestParam String toMail, @RequestParam String p,
			@RequestParam String loginName) {
		try {
			mailSendService.SendMail(toMail, p, loginName);
		} catch (MailException ex) {
			System.err.println("***KLAIDA*** " + ex);
		}

	}

}