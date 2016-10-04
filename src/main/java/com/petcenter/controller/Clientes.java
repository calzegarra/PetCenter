package com.petcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petcenter.crud.TipoDocumentoRepository;

@Controller
public class Clientes {
	
	@Autowired
	TipoDocumentoRepository tipoDocumentos;
	
	@RequestMapping("/clientes")
	public String clientes(Model model){
		model.addAttribute("tipoDocumentos", tipoDocumentos.findAll());
		return "clientes";
	}
	
}
