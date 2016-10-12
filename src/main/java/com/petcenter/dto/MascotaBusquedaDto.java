package com.petcenter.dto;

import lombok.Data;

@Data
public class MascotaBusquedaDto {
	
	private String codigo;
	
	private String nombre;
	
	private long tipoDocumento;
	
	private String nroDocumento;
	
	private String nombreCliente;
	
	private String apPaterno;
	
	private String apMaterno;
	
}
