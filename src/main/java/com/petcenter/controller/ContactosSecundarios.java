package com.petcenter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.petcenter.crud.ClienteRepository;
import com.petcenter.crud.ContactoSecundarioRepository;
import com.petcenter.crud.ParentContactoSecCliRepository;
import com.petcenter.model.Cliente;
import com.petcenter.model.ContactoSecundario;

@Controller
public class ContactosSecundarios {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ContactoSecundarioRepository contactoSecundarioRep;
	
	@Autowired
	ParentContactoSecCliRepository parentescoContactoRep;
	
	@RequestMapping("/clientes/contactosecundario/{id}")
	public String contactosecundario(@PathVariable("id") long id, Model model) {
		Cliente cliente = clienteRepository.findByIdCliente(id);
		model.addAttribute("cliente", cliente);
		if(contactoSecundarioRep.findByCliente(cliente) != null){
			model.addAttribute("contactosecundario", contactoSecundarioRep.findByCliente(cliente));
			model.addAttribute("idContactoSecundario", ""+contactoSecundarioRep.findByCliente(cliente).getIdContactoSecundario());
		} else {
			model.addAttribute("contactosecundario", new ContactoSecundario());
			model.addAttribute("idContactoSecundario", "");
		}
		model.addAttribute("parestescos",parentescoContactoRep.findAll());
		return "contactosecundario";
	}
	
	@RequestMapping(value = "/clientes/contactosecundario/", method = { RequestMethod.POST })
	public String guardar(Model model, @RequestParam("idContSecundario") String id, @Valid ContactoSecundario contactoSecundario, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			model.addAttribute("contactosecundario", contactoSecundario);
			model.addAttribute("parestescos",parentescoContactoRep.findAll());
			return "contactosecundario";
		} else {
			if(!id.equals("")){
				ContactoSecundario contactoSecundarioEcontrado = contactoSecundarioRep.findByIdContactoSecundario(Long.valueOf(id));
				contactoSecundarioEcontrado.setNomContactoSec(contactoSecundario.getNomContactoSec());
				contactoSecundarioEcontrado.setApePaternoContactoSec(contactoSecundario.getApePaternoContactoSec());
				contactoSecundarioEcontrado.setApePaternoContactoSec(contactoSecundario.getApeMaternoContactoSec());
				contactoSecundarioEcontrado.setParentContactoSecCli(contactoSecundario.getParentContactoSecCli());
				contactoSecundarioEcontrado.setCelContactoSec(contactoSecundario.getCelContactoSec());
				contactoSecundarioEcontrado.setTelfDomContactoSec(contactoSecundario.getTelfDomContactoSec());
				contactoSecundarioEcontrado.setDescContactoSecundario(contactoSecundario.getDescContactoSecundario());
				contactoSecundarioRep.save(contactoSecundarioEcontrado);
				return "redirect:/clientes";
			} else {
				contactoSecundarioRep.save(contactoSecundario);
				return "redirect:/clientes";
			}
		}
	}
	
}
