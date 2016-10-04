package com.petcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petcenter.crud.TipoDocumentoRepository;

@Controller
public class Mascotas {
	
	@Autowired
	TipoDocumentoRepository tipoDocumentos;
	
	@RequestMapping("/mascotas")
	public String mascotas(Model model) {
		model.addAttribute("tipoDocumentos", tipoDocumentos.findAll());
		return "mascotas";
	}
	
}
