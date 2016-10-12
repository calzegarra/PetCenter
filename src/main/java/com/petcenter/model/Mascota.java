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

import lombok.Data;

@Entity
@Table(name="tb_mascota")
@Data
public class Mascota {
	
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
	@NotNull(message="Debe informar Cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idrelclientemascota", insertable=true, updatable=true)
	@NotNull(message="Debe informar Relación con la Mascota")
	private RelClienteMascota relClienteMascota;
	
	@ManyToOne
	@JoinColumn(name="idraza", insertable=true, updatable=true)
	@NotNull(message="Debe informar Raza")
	private Raza raza;
	
	@Column(name="fotomascota")
	private byte[] fotoMascota;
	
	@Column(name="estadomascota")
	private boolean estadoMascota;
	
	@Column(name="descmascota")
	@NotNull(message="Debe informar Descripción")
	@NotBlank(message="Debe informar Descripción")
	private String descMascota;
	
	@ManyToOne
	@JoinColumn(name="idgeneromascota", insertable=true, updatable=true)
	@NotNull(message="Debe informar Genero")
	private GeneroMascota generoMascota;
	
	@Column(name="fechanacmascota")
	@NotNull(message="Debe informar Fecha Nacimiento")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaNacMascota;

}
