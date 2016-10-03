package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_pais")
@Data
public class Pais {
	
	@Id
	@GeneratedValue
	@Column(name="idPais")
	private long idPais;
	
	private String descripcionPais;

}
