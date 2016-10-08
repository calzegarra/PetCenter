package com.petcenter.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Page<Cliente> findAll(Pageable pageable);
	
	Cliente findByIdCliente(long idCliente);
	
	Cliente findByNroDocumento(String nroDocumento);
	
}
