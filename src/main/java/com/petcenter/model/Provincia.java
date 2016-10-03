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
	@Column(name="idProvincia")
	private long idProvincia;
	
	private String descripcionProvincia;
	
	@ManyToOne
	private Pais idPais;
	
}
