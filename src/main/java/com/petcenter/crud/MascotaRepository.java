package com.petcenter.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Mascota;

public interface MascotaRepository extends CrudRepository<Mascota, Long> {
	
	Page<Mascota> findAll(Pageable pegeable);
	
	Page<Mascota> findByCodMascota(String codigoMascota, Pageable pegeable);
	
	Page<Mascota> findByNomMascota(String nombreMascota, Pageable pegeable);
	
	@Query("select m from Mascota m inner join m.cliente c where c.tipoDocumento.idTipoDocumento = ?1 and c.nroDocumento = ?2")
	Page<Mascota> findByTipoDocunento(long tipoDocumento, String nroDocumento, Pageable pegeable);
	
	@Query("select m from Mascota m inner join m.cliente c where c.nomCliente = ?1 and c.apePaternoCliente = ?2 and c.apeMaternoCliente = ?3")
	Page<Mascota> findByNombreCompletoCliente(String nombreCliente, String apPaterno, String apMaterno, Pageable pegeable);
	
	@Query("select count(m.idMascota) FROM Mascota m")
	int countRows();
	
}
