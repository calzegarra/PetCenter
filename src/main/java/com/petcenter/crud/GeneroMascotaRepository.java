package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.GeneroMascota;

public interface GeneroMascotaRepository extends CrudRepository<GeneroMascota, Long> {

	List<GeneroMascota> findAll();
	
}
