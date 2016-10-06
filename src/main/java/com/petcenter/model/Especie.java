package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_especie")
@Data
public class Especie {
	
	@Id
	@GeneratedValue
	@Column(name="idespecie")
	private long idEspecie;
	
	@Column(name="descripcionespecie")
	private String descripcionEspecie;

}
