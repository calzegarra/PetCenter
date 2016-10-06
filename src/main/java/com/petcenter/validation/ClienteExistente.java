package com.petcenter.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ClienteExistenteValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteExistente {
	
	String message() default "Cliente ya se encuentra registrado";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
