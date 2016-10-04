package com.petcenter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petcenter.crud.ClienteRepository;
import com.petcenter.crud.TipoDocumentoRepository;
import com.petcenter.dto.ClienteDto;
import com.petcenter.model.Cliente;

@Controller
public class Clientes {
	
	@Autowired
	TipoDocumentoRepository tipoDocumentosRep;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@RequestMapping("/clientes")
	public String clientes(Model model){
		model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());
		
		List<ClienteDto> clientesDto = new ArrayList<>();
		List<Cliente> clientes = clienteRepository.findAll();
		for(Cliente c : clientes){
			ClienteDto cDto = new ClienteDto();
			cDto.setIdCliente(c.getIdCliente());
			cDto.setCodCliente(c.getCodCliente());
			cDto.setTipoCliente(c.getIdTipoCliente().getDescripcionTipoCliente());
			cDto.setDocumento(c.getTipoDocumento().getDescripcionTipoDocumento() + " - " + c.getNroDocumento());
			cDto.setNombreCompleto(c.getApePaternoCliente() + " " + c.getApeMaternoCliente() + ", " + c.getNomCliente());
			clientesDto.add(cDto);
		}
		model.addAttribute("clientes", clientesDto);
		
		return "clientes";
	}
	
}
