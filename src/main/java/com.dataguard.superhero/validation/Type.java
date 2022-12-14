package com.dataguard.superhero.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@NotBlank(message = "type must not be null or empty")
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SuperheroTypeValidator.class})
@Documented
public @interface Type {

    String message() default "Invalid type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
