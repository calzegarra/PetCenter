package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Sede;

public interface SedeRepository extends CrudRepository<Sede, Long> {

	List<Sede> findAll();
	
}
