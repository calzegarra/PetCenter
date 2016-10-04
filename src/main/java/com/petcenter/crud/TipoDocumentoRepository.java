package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.TipoDocumento;

public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, Long> {

	List<TipoDocumento> findAll();

}
