package com.petcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petcenter.crud.EspecieRepository;
import com.petcenter.crud.RazaRepository;
import com.petcenter.model.Especie;
import com.petcenter.model.Raza;

@Controller
public class Razas {
	
	@Autowired
	EspecieRepository especieRep;
	
	@Autowired
	RazaRepository razaRep;

	@RequestMapping(value = "/razas/{especie}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Raza> razas(@PathVariable("especie") long especie) {
		Especie especieEncontrada =  especieRep.findByIdEspecie(especie);
		return razaRep.findByEspecie(especieEncontrada);
	}
	
}
