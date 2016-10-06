package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	List<Cliente> findAll();
	
	Cliente findByIdCliente(long idCliente);
	
	Cliente findByNroDocumento(String nroDocumento);
	
}
