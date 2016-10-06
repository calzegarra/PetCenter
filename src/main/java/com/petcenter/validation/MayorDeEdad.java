package com.petcenter.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MayorDeEdadValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MayorDeEdad {
	
	String message() default "No cumple con la mayoria de edad";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
