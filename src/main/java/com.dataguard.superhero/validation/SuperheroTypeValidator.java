package com.dataguard.superhero.validation;

import com.dataguard.superhero.web.SuperheroAttributeType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SuperheroTypeValidator implements ConstraintValidator<Type, String> {

    @Override
    public boolean isValid(String typeStr, ConstraintValidatorContext constraintValidatorContext) {
        if(SuperheroAttributeType.findByName ( typeStr ).isPresent ())
            constraintValidatorContext.buildConstraintViolationWithTemplate ( "type parameter is wrong : " + typeStr  ).
                    addConstraintViolation ();
        return true;
    }
}
