package com.petcenter.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import lombok.Data;

@Entity
@Table(name="tb_mascota")
@Data
public class Mascota {
	
	public static final AtomicLong contador = new AtomicLong(1);
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idmascota")
	private long idMascota;
	
	@Column(name="codmascota")
	private String codMascota;
	
	@Column(name="nommascota")
	private String nomMascota;
	
	@ManyToOne
	@JoinColumn(name="idcliente", insertable=true, updatable=true)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idrelclientemascota", insertable=true, updatable=true)
	private RelClienteMascota relClienteMascota;
	
	@ManyToOne
	@JoinColumn(name="idraza", insertable=true, updatable=true)
	private Raza raza;
	
	@Column(name="fotomascota")
	private byte[] fotoMascota;
	
	@Column(name="estadomascota")
	private boolean estadoMascota;
	
	@Column(name="descmascota")
	private String descMascota;
	
	@ManyToOne
	@JoinColumn(name="idgeneromascota", insertable=true, updatable=true)
	private GeneroMascota generoMascota;
	
	@Column(name="fechanacmascota")
	private Date fechaNacMascota;
	
	@PrePersist
	public void onPrePersist() {
		// con esto se genera el codigo automaticamente
		this.codMascota = "MAS" + StringUtils.leftPad(String.valueOf(contador.getAndIncrement()), 7, '0');
	}

}
