package vs.admin.features.mail;

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

	public void SendMail() {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("vrk.sistema@zoho.com");
		mail.setFrom("vrk.sistema@zoho.com");
		mail.setSubject("VRK sistema: slaptažodis");
		mail.setText("Slaptažodį prašome saugoti: abc123");

		javaMailSender.send(mail);
	}
}
