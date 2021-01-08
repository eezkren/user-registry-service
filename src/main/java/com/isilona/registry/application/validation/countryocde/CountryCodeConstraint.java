package com.isilona.registry.application.validation.countryocde;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CountryCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryCodeConstraint {

    String message() default "Invalid country code ({wrongValue})";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}