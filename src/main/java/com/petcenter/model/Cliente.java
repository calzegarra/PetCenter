package com.petcenter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_cliente")
@Data
public class Cliente {
	
	@Id
	@GeneratedValue
	@Column(name="idCliente")
	private long idCliente;
	
	private String codCliente;
	
	@ManyToOne
	private TipoCliente idTipoCliente;
	
	@ManyToOne
	private TipoDocumento idTipoDocumento;
	
	private String nroDocumento;
	
	private String nomCliente;
	
	private String apePaternoCliente;
	
	private String apeMaternoCliente;
	
	@ManyToOne
	private GeneroCliente idGeneroCliente;
	
	private Date fecNacCliente;
	
	@ManyToOne
	private Sede idSede;
	
	@ManyToOne
	private Distrito idDistrito;
	
	private String direcCliente;
	
	private String celCliente;
	
	private String telfDomCliente;
	
	private String telfTraCliente;
	
	private boolean indNotificaciones;
	
	private String correoElectCliente;
	
	private int estadoCliente;
	
}
