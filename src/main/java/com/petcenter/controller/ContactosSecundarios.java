package com.petcenter.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String contactosecundario(HttpSession session, @PathVariable("id") long id, Model model) {
		Cliente cliente = clienteRepository.findByIdCliente(id);
		
		session.setAttribute("idCliente", cliente.getIdCliente());
		
		model.addAttribute("cliente", cliente);
		if(contactoSecundarioRep.findByCliente(cliente) != null){
			ContactoSecundario contactoSecundario = contactoSecundarioRep.findByCliente(cliente);
			model.addAttribute("contactosecundario", contactoSecundario);
			session.setAttribute("idContactoSecundario", contactoSecundario.getIdContactoSecundario());
			model.addAttribute("parentescoID", contactoSecundario.getParentContactoSecCli().getIdParentContactoSecCli());
		} else {
			model.addAttribute("contactosecundario", new ContactoSecundario());
			model.addAttribute("parentescoID", "");
			session.setAttribute("idContactoSecundario", "");
		}
		
		model.addAttribute("parestescos",parentescoContactoRep.findAll());
		
		return "contactosecundario";
	}
	
	@RequestMapping(value = "/clientes/contactosecundario", method = { RequestMethod.POST })
	public String guardar(Model model, @RequestParam("idContSecundario") String id, 
			@Valid @ModelAttribute("contactosecundario") ContactoSecundario contactosecundario, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			model.addAttribute("contactosecundario", contactosecundario);
			model.addAttribute("idContactoSecundario", ""+contactosecundario.getIdContactoSecundario());
			model.addAttribute("parestescos",parentescoContactoRep.findAll());
			return "contactosecundario";
		} else {
			if(!id.equals("")){
				ContactoSecundario contactoSecundarioEcontrado = contactoSecundarioRep.findByIdContactoSecundario(Long.valueOf(id));
				contactoSecundarioEcontrado.setNomContactoSec(contactosecundario.getNomContactoSec());
				contactoSecundarioEcontrado.setApePaternoContactoSec(contactosecundario.getApePaternoContactoSec());
				contactoSecundarioEcontrado.setApeMaternoContactoSec(contactosecundario.getApeMaternoContactoSec());
				contactoSecundarioEcontrado.setParentContactoSecCli(contactosecundario.getParentContactoSecCli());
				contactoSecundarioEcontrado.setCelContactoSec(contactosecundario.getCelContactoSec());
				contactoSecundarioEcontrado.setTelfDomContactoSec(contactosecundario.getTelfDomContactoSec());
				contactoSecundarioEcontrado.setDescContactoSecundario(contactosecundario.getDescContactoSecundario());
				contactoSecundarioRep.save(contactoSecundarioEcontrado);
				return "redirect:/clientes";
			} else {
				contactoSecundarioRep.save(contactosecundario);
				return "redirect:/clientes";
			}
		}
	}
	
}
