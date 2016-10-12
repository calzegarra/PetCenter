package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Especie;

public interface EspecieRepository extends CrudRepository<Especie, Long> {
	
	List<Especie> findAll();

	Especie findByIdEspecie(long id);
	
}
