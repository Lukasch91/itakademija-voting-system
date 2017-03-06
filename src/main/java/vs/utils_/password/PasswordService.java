package vs.utils_.password;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vs.admin_.representative.RepresentativeRepository;

@Service
public class PasswordService implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordSalt = passwordEncoder.encode(rawPassword);
		return passwordSalt;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}
	

	private String password;
	private int randNumber;
	private StringBuilder stringBuilder;
	private List<Integer> numberList; 
/*	@Autowired
	private RepresentativeRepository representativeRepository;*/
	
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
	@Transactional
	public String PassHashing(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordSalt = passwordEncoder.encode(password);
		return passwordSalt;

	}
	
	@Transactional
	public ArrayList<String> GeneratedPasswordList(){
		PasswordService passwordService = new PasswordService();
		ArrayList<String> kodai = new ArrayList<String>();
		for(int a = 0; a<20; a++){
			String pass = passwordService.PassGenerator();
			kodai.add(pass);
		}
		
		return kodai;
	}
	
/*	public boolean PasswordCheck(String loginName, String passwordToCheck) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordInDb = representativeRepository.findByLoginName(loginName).getPassword();
		
		boolean result = passwordEncoder.matches(passwordToCheck, passwordInDb);
		return result;
	}*/


}