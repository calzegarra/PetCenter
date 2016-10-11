package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name="tb_contactosecundario")
@Data
public class ContactoSecundario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcontactosecundario")
	private long idContactoSecundario;
	
	@ManyToOne
	@JoinColumn(name="idparentcontactoseccli", insertable=true, updatable=true)
	@NotNull(message="Debe informar Parentesco Contacto Secundario")
	private ParentContactoSecCli parentContactoSecCli;
	
	@ManyToOne
	@JoinColumn(name="idcliente", insertable=true, updatable=true)
	private Cliente cliente;
	
	@Column(name="nomcontactosec")
	@NotNull(message="Debe informar Nombre")
	@NotBlank(message="Debe informar Nombre")
	private String nomContactoSec;
	
	@Column(name="apepaternocontactosec")
	@NotNull(message="Debe informar Apellido Paterno")
	@NotBlank(message="Debe informar Apellido Paterno")
	private String apePaternoContactoSec;
	
	@Column(name="apematernocontactosec")
	@NotNull(message="Debe informar Apellido Materno")
	@NotBlank(message="Debe informar Apellido Materno")
	private String apeMaternoContactoSec;
	
	@Column(name="celcontactosec")
	@NotNull(message="Debe informar Celular")
	@NotBlank(message="Debe informar Celular")
	private String celContactoSec;
	
	@Column(name="telfdomcontactosec")
	@NotNull(message="Debe informar Tefelono Domiciliario")
	@NotBlank(message="Debe informar Telefono Domiciliario")
	private String telfDomContactoSec;
	
	@Column(name="desccontactosecundario")
	@NotNull(message="Debe informar Descripción")
	@NotBlank(message="Debe informar Descripción")
	private String descContactoSecundario;
	
}
