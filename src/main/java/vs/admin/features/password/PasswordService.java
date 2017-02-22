package vs.admin.features.password;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

	private String password;
	private int randNumber;
	private StringBuilder stringBuilder;
	private List<Integer> numberList;

	@Transactional
	public String PassGenerator() {
		this.numberList = new ArrayList<>();
		this.stringBuilder = new StringBuilder();

		// Add ASCII numbers of characters commonly acceptable in passwords
		for (int i = 33; i < 127; i++) {
			numberList.add(i);
		}

		// Remove characters /, \, and " as they're not commonly accepted
		numberList.remove(new Integer(34));
		numberList.remove(new Integer(47));
		numberList.remove(new Integer(92));

		/*
		 * Randomise over the ASCII numbers and append respective character
		 * values into a StringBuilder
		 */
		for (int i = 0; i < 12; i++) {
			randNumber = numberList.get(new SecureRandom().nextInt(91));
			stringBuilder.append((char) randNumber);
		}

		password = stringBuilder.toString();

		return password;
	}

	public String PassHashing(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String passwordMd5 = DatatypeConverter
				.printHexBinary(MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8")));

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordSalt = passwordEncoder.encode(passwordMd5);
		return passwordSalt;

	}
	

}
