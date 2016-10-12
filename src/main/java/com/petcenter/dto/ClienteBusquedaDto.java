package com.petcenter.dto;

import lombok.Data;

@Data
public class ClienteBusquedaDto {
	
	ClienteBusquedaDto(){};
	
	private String codigo;
	
	private String nombre;
	
	private long tipoDocumento;
	
	private String nroDocumento;
	
	private String apPaterno;
	
	private String apMaterno;
	
}
