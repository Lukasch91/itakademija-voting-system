package vs.admin.features.mail;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailSendService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Transactional
	public void SendMail(String toMail, String password, String loginName) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(toMail);
		mail.setFrom("vrk.sistema@zoho.com");
		mail.setSubject("VRK sistema. Vartotojo slaptažodis");
		mail.setText("Jūsų vartotojo vardas :" + loginName + "\n" + "Jūsų nuolatinis slaptažodis yra:" + password);

		javaMailSender.send(mail);
	}
}