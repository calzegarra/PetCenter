package com.petcenter.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Mascota;

public interface MascotaRepository extends CrudRepository<Mascota, Long> {
	
	Page<Mascota> findAll(Pageable pegeable);
	
	Page<Mascota> findByCodMascota(String codigoMascota, Pageable pegeable);
	
	Page<Mascota> findByNomMascota(String nombreMascota, Pageable pegeable);
	
}
