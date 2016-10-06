package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_raza")
@Data
public class Raza {

	@Id
	@GeneratedValue
	@Column(name="idraza")
	private long idRaza;
	
	@Column(name="descripcionraza")
	private String descripcionRaza;
	
	@ManyToOne
	@JoinColumn(name="idespecie", insertable=false, updatable=false)
	private Especie especie;
	
}
