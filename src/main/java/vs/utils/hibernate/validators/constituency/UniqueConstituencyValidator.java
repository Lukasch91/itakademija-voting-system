package vs.utils.hibernate.validators.constituency;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import vs.admin.features.admin.constituency.Constituency;
import vs.admin.features.admin.representative.Representative;
import vs.admin.features.party.model.Party;

public class UniqueConstituencyValidator
		implements ConstraintValidator<UniqueConstituency, String> {

	// ===============================================
	private static final String FIND_ALL = "SELECT x FROM Constituency x";
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
