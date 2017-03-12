package vs.utils_.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService implements PasswordEncoder {
	private static final Logger log = Logger.getLogger(PasswordService.class.getName());

	@Override
	public String encode(CharSequence rawPassword) {
		log.info("Password encode started...");

		String hashValue = "";
		try {
			long time = System.currentTimeMillis();
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(rawPassword.toString().getBytes());
			byte[] digestBytes = messageDigest.digest();
			hashValue = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(13);
			String passwordSalt = passwordEncoder.encode(hashValue);
			time = System.currentTimeMillis() - time;
			log.info("Password encode success! Time ms:" + time);
			
			return passwordSalt;
			
		} catch (NoSuchAlgorithmException e) {
			log.error("Password encoding probblem: " + e);
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		log.info("Password checking started...");
		
		String hashValue = "";
		try {
			long time = System.currentTimeMillis();
			
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(rawPassword.toString().getBytes());
			byte[] digestBytes = messageDigest.digest();
			hashValue = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();

			boolean passCheck = BCrypt.checkpw(hashValue, encodedPassword);
			
			time = System.currentTimeMillis() - time;
			log.info("Password encode success! Time ms:" + time);
			
			return passCheck;
			
		} catch (NoSuchAlgorithmException e) {
			log.info("Password encode problem! :" + e);
			e.printStackTrace();
			return false;
		}
	}

	private String password;
	private int randNumber;
	private StringBuilder stringBuilder;
	private List<Integer> numberList;
	/*
	 * @Autowired private RepresentativeRepository representativeRepository;
	 */

	@Transactional
	public String passGenerator() {
		this.numberList = new ArrayList<>();
		this.stringBuilder = new StringBuilder();
		// log.info("PassGenerator method started...");
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
		// log.info("PassGenerator method process finished!");
		return password;
	}

	@Transactional
	public String PassHashing(String password) {
		log.info("Password encode started...");

		String hashValue = "";
		try {
			long time = System.currentTimeMillis();
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.toString().getBytes());
			byte[] digestBytes = messageDigest.digest();
			hashValue = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(13);
			String passwordSalt = passwordEncoder.encode(hashValue);

			time = System.currentTimeMillis() - time;
			log.info("Password encode success! Time ms: " + time);
			
			return passwordSalt;
			
		} catch (NoSuchAlgorithmException e) {
			log.error("Problem with password hashing, encoding: " + e);
			e.printStackTrace();
			return null;
		}



	}

	@Transactional
	public ArrayList<String> GeneratedPasswordList() {
		log.info("Password list generating started...");
		PasswordService passwordService = new PasswordService();
		ArrayList<String> kodai = new ArrayList<String>();
		for (int a = 0; a < 20; a++) {
			String pass = passwordService.passGenerator();
			kodai.add(pass);
		}
		log.info("Password list generated successfully!");
		return kodai;
	}

	/*
	 * public boolean PasswordCheck(String loginName, String passwordToCheck)
	 * throws NoSuchAlgorithmException, UnsupportedEncodingException{
	 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 * String passwordInDb =
	 * representativeRepository.findByLoginName(loginName).getPassword();
	 * 
	 * boolean result = passwordEncoder.matches(passwordToCheck, passwordInDb);
	 * return result; }
	 */

}