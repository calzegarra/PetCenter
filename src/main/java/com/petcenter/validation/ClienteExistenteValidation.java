package com.petcenter.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.petcenter.crud.ClienteRepository;

public class ClienteExistenteValidation implements ConstraintValidator<ClienteExistente, String>{
	
	@Autowired
	ClienteRepository clienteRep;
	
	@Override
	public void initialize(ClienteExistente arg0) {
	}

	@Override
	public boolean isValid(String codigo, ConstraintValidatorContext arg1) {
		if(codigo != ""){
			if(clienteRep != null){
				if(clienteRep.findByNroDocumento(codigo) != null){
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		return true;
	}

}
