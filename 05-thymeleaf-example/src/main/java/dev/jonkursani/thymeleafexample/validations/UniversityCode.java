package dev.jonkursani.thymeleafexample.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniversityCodeValidator.class) // i tregon prej ciles klase validaohet annotation
@Target({ElementType.FIELD, ElementType.METHOD}) // i tregon ku mundemi me perdor annotation
@Retention(RetentionPolicy.RUNTIME) // kjo i tregon qe ka mu ekzekutu ne runtime
public @interface UniversityCode {
    // vlera default
    String value() default "CCT";

    // mesazhi default
    String message() default "University code must start with CCT";

    // grupi i validimeve
    Class<?>[] groups() default {};

    // payloads
    Class<? extends Payload>[] payload() default {};
}