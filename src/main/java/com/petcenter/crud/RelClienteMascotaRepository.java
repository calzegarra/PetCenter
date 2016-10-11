package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.RelClienteMascota;

public interface RelClienteMascotaRepository extends CrudRepository<RelClienteMascota, Long> {
	
	List<RelClienteMascota> findAll();
	
}
