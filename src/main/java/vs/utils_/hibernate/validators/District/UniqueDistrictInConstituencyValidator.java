package vs.utils_.hibernate.validators.District;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import vs.admin_.district.District;

public class UniqueDistrictInConstituencyValidator
		implements ConstraintValidator<UniqueDistrictInConstituency, District> {

	// ===============================================
	private static final String FIND_ALL = "SELECT x FROM District x WHERE deletedTime IS null AND constituencyId IS ";
	@Autowired
	private EntityManager em;
	// ===============================================

	@Override
	public void initialize(UniqueDistrictInConstituency constraintAnnotation) {
	}

	@Override
	public boolean isValid(District district, ConstraintValidatorContext context) {
		if (district == null) {
			return true;
		}

		@SuppressWarnings("unchecked")
		List<District> districts = em.createQuery(FIND_ALL + district.getConstituencyId()).getResultList();
		boolean duplicate = false;

		for (District dis : districts) {
			if (dis.getTitle().equals(district.getTitle())) {
				duplicate = true;
			}
		}

		if (duplicate) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Apygardos pavadinimas negali kartotis apylinkėje")
					.addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}
