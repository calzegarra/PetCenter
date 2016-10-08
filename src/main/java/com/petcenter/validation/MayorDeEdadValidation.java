package com.petcenter.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MayorDeEdadValidation implements ConstraintValidator<MayorDeEdad, Date> {

	@Override
	public void initialize(MayorDeEdad arg0) {}

	@Override
	public boolean isValid(Date fecha, ConstraintValidatorContext arg1) {
		if(fecha != null){
			int edad = new Date().getYear() - fecha.getYear();
			if(edad < 18){
				return false;
			}
		}
		return true;
	}

}
