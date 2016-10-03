package com.petcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("titulo", "Pet Center");
		return "index";
	}
	
	@RequestMapping("/clientesmascotas")
	public String clientesmascotas(Model model) {
		model.addAttribute("titulo", "Pet Center");
		return "clientesmascotas";
	}
	
}
