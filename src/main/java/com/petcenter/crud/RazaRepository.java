package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Especie;
import com.petcenter.model.Raza;

public interface RazaRepository extends CrudRepository<Raza, Long> {
	
	List<Raza> findAll();
	
	List<Raza> findByEspecie(Especie especie);
	
}
