package com.petcenter.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.Cliente;
import com.petcenter.model.TipoDocumento;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Page<Cliente> findAll(Pageable pageable);
	
	Cliente findByIdCliente(long idCliente);
	
	Page<Cliente> findByCodCliente(String codCliente, Pageable pageable);
	
	Page<Cliente> findByNomClienteAndTipoDocumentoAndNroDocumentoAndApePaternoClienteAndApeMaternoCliente
				  (String nomCliente, TipoDocumento tipoDocumento, String nroDocumento, String apePaternoCliente, 
				   String apeMaternoCliente, Pageable pageable);
	
	Cliente findByNroDocumento(String nroDocumento);
	
}
