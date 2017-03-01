package vs.utils_.hibernate.validators.District;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDistrictInConstituencyValidator.class)
@Documented
public @interface UniqueDistrictInConstituency {

	String message() default "{UniqueDistrictInConstituency - default message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
