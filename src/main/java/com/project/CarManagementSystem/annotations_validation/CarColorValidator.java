package com.project.CarManagementSystem.annotations_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CarColorValidator implements ConstraintValidator<CarColorValidation,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> color = List.of("Red","Green","Purple","Orange","Yellow");
        return color.contains((s));
    }
}
