package dev.jonkursani.thymeleafexample.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniversityCodeValidator implements ConstraintValidator<UniversityCode, String> {
    private String universityPrefix;

    @Override
    public void initialize(UniversityCode code) {
        universityPrefix = code.value(); // vlera qe vjen prej inputit
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        // ne kete metode ndertohet logjika e validimit
        boolean result = false;

        if (code != null) {
            result = code.startsWith(universityPrefix); // a fillon universiteti me ni prefix specifik psh: "CCT"
        } else {
            result = true;
        }

        return result; // nese rezultati kthehet false, kemi error duhet me shfaq errorin te useri
    }
}