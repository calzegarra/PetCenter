package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_tipodocumento")
@Data
public class TipoDocumento {
	
	@Id
	@GeneratedValue
	@Column(name="idTipoDocumento")
	private long idTipoDocumento;
	
	private String descripcionTipoDocumento;
	
}
