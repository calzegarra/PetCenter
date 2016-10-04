package com.petcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Clientes {
	
	@RequestMapping("/clientes")
	public String clientes(){
		return "clientes";
	}
	
}
