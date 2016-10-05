package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.GeneroCliente;

public interface GeneroRepository extends CrudRepository<GeneroCliente, Long> {
	
	List<GeneroCliente> findAll();
	
}
