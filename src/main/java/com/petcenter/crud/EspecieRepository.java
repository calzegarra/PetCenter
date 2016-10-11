package com.petcenter.crud;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Especie;

public interface EspecieRepository extends CrudRepository<Especie, Long> {

}
