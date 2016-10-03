package com.petcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Index {
	
	@RequestMapping("/index")
	public String index(Model model) {
		String val = "Willy";
		model.addAttribute("name", val);
		return "index";
	}
	
	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false) String name, Model model) { //defaultValue="World"
        model.addAttribute("name", "fff");
        return "greeting";
    }
	
}
