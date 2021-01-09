package com.isilona.registry.application.validation.allowedcountry;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = AllowedCountryValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedCountryConstraint {

    String message() default "{com.isilona.registry.validation.AllowedCountry.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}