package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.TipoCliente;

public interface TipoClienteRepository extends CrudRepository<TipoCliente, Long> {
	
	List<TipoCliente> findAll();

}
