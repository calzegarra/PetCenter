package com.petcenter.dto;

import lombok.Data;

@Data
public class MascotaDto {
	
	private long idMascota;
	
	private String codigo;
	
	private String raza;
	
	private boolean estado;
	
	private String dueno;
	
	private String relacionConMascota;
	
}
