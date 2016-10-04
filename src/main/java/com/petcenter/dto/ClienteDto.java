package com.petcenter.dto;

import lombok.Data;

@Data
public class ClienteDto {
	
	private long idCliente;
	
	private String codCliente;
	
	private String tipoCliente;
	
	private String documento;
	
	private String nombreCompleto;
	
}
