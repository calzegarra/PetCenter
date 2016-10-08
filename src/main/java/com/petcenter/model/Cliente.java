package com.petcenter.model;

import java.util.Date;

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
import org.springframework.format.annotation.DateTimeFormat;

import com.petcenter.validation.MayorDeEdad;

import lombok.Data;

@Entity
@Table(name="tb_cliente")
@Data
public class Cliente {
	
	public Cliente(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcliente")
	private long idCliente;
	
	@Column(name="codcliente")
	@NotNull(message="Debe informar Codigo")
	@NotBlank(message="Debe informar Codigo")
	private String codCliente;
	
	@ManyToOne
	@JoinColumn(name="idtipocliente", insertable=true, updatable=true)
	@NotNull(message="Debe informar Tipo de Cliente")
	private TipoCliente tipoCliente;
	
	@ManyToOne
	@JoinColumn(name="idtipodocumento", insertable=true, updatable=true)
	@NotNull(message="Debe informar Tipo de Documento")
	private TipoDocumento tipoDocumento;
	
	@Column(name="nrodocumento")
	@NotNull(message="Debe informar Nº del documento")
	@NotBlank(message="Debe informar Nº del documento")
	private String nroDocumento;
	
	@Column(name="nomcliente")
	@NotNull(message="Debe informar Nombres")
	@NotBlank(message="Debe informar Nombres")
	private String nomCliente;
	
	@Column(name="apepaternocliente")
	@NotNull(message="Debe informar Apellido Paterno")
	@NotBlank(message="Debe informar Apellido Paterno")
	private String apePaternoCliente;
	
	@Column(name="apematernocliente")
	@NotNull(message="Debe informar Apellido Materno")
	@NotBlank(message="Debe informar Apellido Materno")
	private String apeMaternoCliente;
	
	@ManyToOne
	@JoinColumn(name="idgenerocliente", insertable=true, updatable=true)
	@NotNull(message="Debe informar Genero")
	private GeneroCliente generoCliente;
	
	@Column(name="fecnaccliente")
	@NotNull(message="Debe informar Fecha de Nacimiento")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@MayorDeEdad
	private Date fecNacCliente;
	
	@ManyToOne
	@JoinColumn(name="idsede", insertable=true, updatable=true)
	@NotNull(message="Debe informar Sede")
	private Sede sede;
	
	@ManyToOne
	@JoinColumn(name="iddistrito", insertable=true, updatable=true)
	@NotNull(message="Debe informar Distrito")
	private Distrito distrito;
	
	@Column(name="direccliente")
	@NotNull(message="Debe informar Direccion")
	@NotBlank(message="Debe informar Direccion")
	private String direcCliente;
	
	@Column(name="celcliente")
	@NotNull(message="Debe informar Celular")
	@NotBlank(message="Debe informar Celular")
	private String celCliente;
	
	@Column(name="telfdomcliente")
	@NotNull(message="Debe informar Telefono de Domicilio")
	@NotBlank(message="Debe informar Telefono de Domicilio")
	private String telfDomCliente;
	
	@Column(name="telftracliente")
	@NotNull(message="Debe informar Telefono de Trabajo")
	@NotBlank(message="Debe informar Telefono de Trabajo")
	private String telfTraCliente;
	
	@Column(name="indnotificaciones")
	private boolean indNotificaciones;
	
	@Column(name="correoelectcliente")
	@NotNull(message="Debe informar Correo Electronico")
	@NotBlank(message="Debe informar Correo Electronico")
	private String correoElectCliente;
	
	@Column(name="estadocliente")
	private int estadoCliente;
	
}
