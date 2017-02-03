package vs.utils.hibernate.validators.representative;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import vs.admin.features.admin.representative.Representative;

public class UniqueRepresentativeEmailValidator
		implements ConstraintValidator<UniqueRepresentativeEmail, String> {

	// ===============================================
	private static final String FIND_ALL = "SELECT x FROM Representative x";
	@Autowired
	private EntityManager em;
	// ===============================================

	@Override
	public void initialize(UniqueRepresentativeEmail constraintAnnotation) {
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
			if (rep.getEmail().equals(value)) {
				duplicate = true;
			}
		}

		if (duplicate) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("E-paštas jau panaudotas").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}
