package vs.utils_.hibernate.validators.multiElection;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VotesMultiElectionValidator.class) //pakeisti
@Documented
public @interface VotesMultiElection { //pakeisti

	String message() default "{UniqueParty - default message}";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

		
	@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		VotesMultiElection[] value(); //pakeisti
	}
}
