package com.project.CarManagementSystem.annotations_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {CarColorValidator.class}
)
public @interface CarColorValidation {
    String message() default "{value should be {\"Red\",\"Green\",\"Purple\",\"Orange\",\"Yellow\"}}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
