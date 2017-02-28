package vs.utils_.password;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPassword implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordSalt = passwordEncoder.encode(rawPassword);
		return passwordSalt;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
//		 System.err.println("matches-raw password: " + rawPassword);
//		 System.err.println("matches-encoded password: " + encodedPassword);
//		 System.err.println("true or false : " + BCrypt.checkpw(rawPassword.toString(), encodedPassword));
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}

}
