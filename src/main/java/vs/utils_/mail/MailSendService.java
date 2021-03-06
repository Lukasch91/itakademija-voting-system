package vs.utils_.mail;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

	private static final Logger log = Logger.getLogger(MailSendService.class.getName());
	
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
		mail.setText("Jūsų vartotojo vardas :" + loginName + 
				"\n" + "Jūsų laikinas slaptažodis yra:" + password +
				"\n" + "Prisijungti galite: http://localhost:8080/login");

		javaMailSender.send(mail);
		log.info("sendFrom: " + mail.getFrom() + " subject: " + mail.getSubject() + " toMail: " + mail.getTo());
	}
}