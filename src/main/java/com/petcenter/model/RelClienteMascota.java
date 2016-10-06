package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_relacionclientemascota")
@Data
public class RelClienteMascota {
	
	@Id
	@GeneratedValue
	@Column(name="idrelclientemascota")
	private long idRelClienteMascota;
	
	@Column(name="descripcionrelclientemascota")
	private String descripcionRelClienteMascota;
	
}
