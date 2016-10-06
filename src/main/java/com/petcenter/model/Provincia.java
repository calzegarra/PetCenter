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
@Table(name="tb_provincia")
@Data
public class Provincia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idprovincia")
	private long idProvincia;
	
	@Column(name="descripcionprovincia")
	private String descripcionProvincia;
	
	@ManyToOne
	@JoinColumn(name="idpais", insertable=true, updatable=true)
	private Pais pais;
	
}
