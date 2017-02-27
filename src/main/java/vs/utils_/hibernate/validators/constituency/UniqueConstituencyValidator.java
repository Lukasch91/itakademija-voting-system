package vs.utils_.hibernate.validators.constituency;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import vs.admin_.constituency.Constituency;
import vs.admin_.party.Party;
import vs.admin_.representative.Representative;

public class UniqueConstituencyValidator
		implements ConstraintValidator<UniqueConstituency, String> {

	// ===============================================
	private static final String FIND_ALL = "SELECT x FROM Constituency x WHERE deletedTime IS null";
	@Autowired
	private EntityManager em;
	// ===============================================

	@Override
	public void initialize(UniqueConstituency constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		@SuppressWarnings("unchecked")
		List<Constituency> constituency = em.createQuery(FIND_ALL).getResultList();
		boolean duplicate = false;

		for (Constituency con : constituency) {
			if (con.getTitle().equals(value)) {
				duplicate = true;
			}
		}

		if (duplicate) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Apylinkės pavadinimas negali kartotis").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}
