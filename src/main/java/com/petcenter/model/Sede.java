package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_sede")
@Data
public class Sede {
	
	@Id
	@GeneratedValue
	@Column(name="idSede")
	private long idSede;
	
	private String descripcionSede;

}
