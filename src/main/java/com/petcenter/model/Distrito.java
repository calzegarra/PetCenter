package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_distrito")
@Data
public class Distrito {
	
	@Id
	@GeneratedValue
	@Column(name="iddistrito")
	private long idDistrito;
	
	@Column(name="descripciondistrito")
	private String descripcionDistrito;
	
	@ManyToOne
	@Column(name="idprovincia")
	private Provincia idProvincia;
	
}
