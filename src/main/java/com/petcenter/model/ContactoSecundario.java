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
@Table(name="tb_contactosecundario")
@Data
public class ContactoSecundario {
	
	@Id
	@GeneratedValue
	@Column(name="idcontactosecundario")
	private long idContactoSecundario;
	
	@ManyToOne
	@JoinColumn(name="idparentcontactoseccli", insertable=false, updatable=false)
	private ParentContactoSecCli parentContactoSecCli;
	
	@ManyToOne
	@JoinColumn(name="idcliente", insertable=false, updatable=false)
	private Cliente cliente;
	
	@Column(name="nomcontactosec")
	private String nomContactoSec;
	
	@Column(name="apepaternocontactosec")
	private String apePaternoContactoSec;
	
	@Column(name="apematernocontactosec")
	private String apeMaternoContactoSec;
	
	@Column(name="celcontactosec")
	private String celContactoSec;
	
	@Column(name="telfdomcontactosec")
	private String telfDomContactoSec;
	
	@Column(name="desccontactosecundario")
	private String descContactoSecundario;
	
}
