package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Distrito;

public interface DistritoRepository extends CrudRepository<Distrito, Long> {

	List<Distrito> findAll();
	
}
