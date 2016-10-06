package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_generomascota")
@Data
public class GeneroMascota {
	
	@Id
	@GeneratedValue
	@Column(name="idgeneromascota")
	private long idGeneroMascota;
	
	@Column(name="descripciongenmascota")
	private String descripcionGenMascota;
	
}
