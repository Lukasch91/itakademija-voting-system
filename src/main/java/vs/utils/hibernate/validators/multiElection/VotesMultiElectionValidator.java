package vs.utils.hibernate.validators.multiElection;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import vs.admin.features.admin.representative.Representative;
import vs.admin.features.party.model.Party;

public class VotesMultiElectionValidator implements ConstraintValidator<VotesMultiElection, BigDecimal> {

	// ===============================================
	// private static final String FIND_ALL = "SELECT x FROM MultiElection x";
	@Autowired
	private EntityManager em;
	// ===============================================

	@Override
	public void initialize(VotesMultiElection constraintAnnotation) {
	}

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		// System.out.println(value instanceof BigDecimal);
		// System.out.println(value);

		String stringas = String.valueOf(value);
		
		// System.err.println(stringas instanceof String);
		// System.err.println();

		@SuppressWarnings("unchecked")
		// List<Party> party = em.createQuery(FIND_ALL).getResultList();
		boolean duplicate = false;
		String regex = "[1-9][0-9]*";
		//System.out.println("-------------------------------****--------------------------------------");
		//System.out.println("Before if. value = " + stringas);
		//System.out.println(Pattern.compile(regex).matcher(stringas).matches());
		if (!Pattern.compile(regex).matcher(stringas).matches()) {
			//System.out.println("Check pattern: " + !Pattern.compile(regex).matcher(stringas).matches());
			//System.out.println("duplicate = true :" + stringas);
			duplicate = true;
		}

		if (duplicate) {
			//System.out.println("-------------------------------****--------------------------------------");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Įvestas blogas balsų skaičius <" + value + ">, prašome patikrinti.")
					.addConstraintViolation();
			return false;
		} else {
			//System.out.println("-------------------------------****--------------------------------------");
			return true;
		}
	}
}
