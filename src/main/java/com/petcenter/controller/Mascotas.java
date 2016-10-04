package com.petcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Mascotas {

	@RequestMapping("/mascotas")
	public String mascotas() {
		return "mascotas";
	}
	
}
