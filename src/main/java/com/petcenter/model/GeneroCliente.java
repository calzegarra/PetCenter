package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_generocliente")
@Data
public class GeneroCliente {
	
	@Id
	@GeneratedValue
	@Column(name="idgenerocliente")
	private long idGeneroCliente;
	
	@Column(name="descripciongenerocliente")
	private String descripcionGeneroCliente;

}
