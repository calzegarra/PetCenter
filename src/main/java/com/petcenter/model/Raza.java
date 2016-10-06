package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idraza")
	private long idRaza;
	
	@Column(name="descripcionraza")
	private String descripcionRaza;
	
	@ManyToOne
	@JoinColumn(name="idespecie", insertable=true, updatable=true)
	private Especie especie;
	
}
