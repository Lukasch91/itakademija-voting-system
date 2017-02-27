package vs.utils_.hibernate.validators.representative;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import vs.admin_.representative.Representative;

public class UniqueRepresentativeUsernameValidator
		implements ConstraintValidator<UniqueRepresentativeUsername, String> {

	// ===============================================
	private static final String FIND_ALL = "SELECT x FROM Representative x";
	@Autowired
	private EntityManager em;
	// ===============================================

	@Override
	public void initialize(UniqueRepresentativeUsername constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		@SuppressWarnings("unchecked")
		List<Representative> representatives = em.createQuery(FIND_ALL).getResultList();
		boolean duplicate = false;

		for (Representative rep : representatives) {
			if (rep.getLoginName().equals(value)) {
				duplicate = true;
			}
		}

		if (duplicate) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Slapyvardis jau panaudotas").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}
