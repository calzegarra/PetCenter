package com.petcenter.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.petcenter.crud.ClienteRepository;
import com.petcenter.model.Cliente;

public class ClienteExistenteValidation implements ConstraintValidator<ClienteExistente, String>{
	
	@Autowired
	ClienteRepository clienteRep;
	
	@Override
	public void initialize(ClienteExistente arg0) {
	}

	@Override
	public boolean isValid(String codigo, ConstraintValidatorContext arg1) {
		if(codigo != "" || codigo != null){
			Cliente c = clienteRep.findByNroDocumento(codigo);
			if(c != null){
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

}
