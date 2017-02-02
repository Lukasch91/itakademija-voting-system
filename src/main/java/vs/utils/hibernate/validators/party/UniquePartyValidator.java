package vs.utils.hibernate.validators.party;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import vs.admin.features.admin.representative.Representative;
import vs.admin.features.party.model.Party;

public class UniquePartyValidator
		implements ConstraintValidator<UniqueParty, String> {

	// ===============================================
	private static final String FIND_ALL = "SELECT x FROM Party x";
	@Autowired
	private EntityManager em;
	// ===============================================

	@Override
	public void initialize(UniqueParty constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		@SuppressWarnings("unchecked")
		List<Party> party = em.createQuery(FIND_ALL).getResultList();
		boolean duplicate = false;

		for (Party par : party) {
			if (par.getTitle().equals(value)) {
				duplicate = true;
			}
		}

		if (duplicate) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Partijos pavadinimas negali kartotis").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}
