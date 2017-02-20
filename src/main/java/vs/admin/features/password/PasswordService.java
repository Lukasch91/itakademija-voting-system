package vs.admin.features.password;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class PasswordService {

	private String passwordService;

	private String password;
	private int randNumber;
	private StringBuilder stringBuilder;
	private List<Integer> numberList;

	@Transactional
	public String PassGen() {
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

}
