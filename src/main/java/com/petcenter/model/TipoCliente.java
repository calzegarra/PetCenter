package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_tipocliente")
@Data
public class TipoCliente {
	
	@Id
	@GeneratedValue
	@Column(name="idTipoCliente")
	private long idTipoCliente;
	
	private String descripcionTipoCliente;

}
