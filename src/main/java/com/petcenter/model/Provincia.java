package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_provincia")
@Data
public class Provincia {
	
	@Id
	@GeneratedValue
	@Column(name="idprovincia")
	private long idProvincia;
	
	@Column(name="descripcionprovincia")
	private String descripcionProvincia;
	
	@ManyToOne
	@Column(name="idpais")
	private Pais idPais;
	
}
