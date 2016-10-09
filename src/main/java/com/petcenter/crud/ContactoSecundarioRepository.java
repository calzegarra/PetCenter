package com.petcenter.crud;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Cliente;
import com.petcenter.model.ContactoSecundario;

public interface ContactoSecundarioRepository extends CrudRepository<ContactoSecundario, Long> {
	
	ContactoSecundario findByIdContactoSecundario(long id);
	
	ContactoSecundario findByCliente(Cliente idCliente);
	
}
