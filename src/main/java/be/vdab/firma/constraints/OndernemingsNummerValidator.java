package be.vdab.firma.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OndernemingsNummerValidator implements ConstraintValidator<OndernemingsNummer, Long> {

    @Override
    public void initialize(OndernemingsNummer constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        //426388541

/*        var tekst = value.toString();
        var laatsteTweeCijfers = Long.valueOf(tekst.substring(tekst.length() - 2));
        var overigeCijfers = Long.valueOf(tekst.substring(0, 7));*/
        var laatsteTweeCijfers = value % 100;
        var overigeCijfers = value / 100;
        return laatsteTweeCijfers == 97 - overigeCijfers % 97;
    }
}