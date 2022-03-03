package be.vdab.firma.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OndernemingsNummerValidator.class)
public @interface OndernemingsNummer {
    String message() default "{be.vdab.OndernemingsNummer.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
