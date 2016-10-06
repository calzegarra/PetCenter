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

import lombok.Data;

@Entity
@Table(name="tb_cliente")
@Data
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcliente")
	private long idCliente;
	
	@Column(name="codcliente")
	private String codCliente;
	
	@ManyToOne
	@JoinColumn(name="idtipocliente", insertable=true, updatable=true)
	private TipoCliente tipoCliente;
	
	@ManyToOne
	@JoinColumn(name="idtipodocumento", insertable=true, updatable=true)
	private TipoDocumento tipoDocumento;
	
	@Column(name="nrodocumento")
	private String nroDocumento;
	
	@Column(name="nomcliente")
	private String nomCliente;
	
	@Column(name="apepaternocliente")
	private String apePaternoCliente;
	
	@Column(name="apematernocliente")
	private String apeMaternoCliente;
	
	@ManyToOne
	@JoinColumn(name="idgenerocliente", insertable=true, updatable=true)
	private GeneroCliente generoCliente;
	
	@Column(name="fecnaccliente")
	private Date fecNacCliente;
	
	@ManyToOne
	@JoinColumn(name="idsede", insertable=true, updatable=true)
	private Sede sede;
	
	@ManyToOne
	@JoinColumn(name="iddistrito", insertable=true, updatable=true)
	private Distrito distrito;
	
	@Column(name="direccliente")
	private String direcCliente;
	
	@Column(name="celcliente")
	private String celCliente;
	
	@Column(name="telfdomcliente")
	private String telfDomCliente;
	
	@Column(name="telftracliente")
	private String telfTraCliente;
	
	@Column(name="indnotificaciones")
	private boolean indNotificaciones;
	
	@Column(name="correoelectcliente")
	private String correoElectCliente;
	
	@Column(name="estadocliente")
	private int estadoCliente;
	
}
