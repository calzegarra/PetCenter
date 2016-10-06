package com.petcenter.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.petcenter.crud.ClienteRepository;
import com.petcenter.crud.DistritoRepository;
import com.petcenter.crud.GeneroRepository;
import com.petcenter.crud.SedeRepository;
import com.petcenter.crud.TipoClienteRepository;
import com.petcenter.crud.TipoDocumentoRepository;
import com.petcenter.dto.ClienteDto;
import com.petcenter.model.Cliente;

@Controller
public class Clientes {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	TipoDocumentoRepository tipoDocumentosRep;

	@Autowired
	TipoClienteRepository tipoClienteRep;

	@Autowired
	GeneroRepository generoRep;

	@Autowired
	SedeRepository sedeRep;

	@Autowired
	DistritoRepository distritoRep;

	@RequestMapping("/clientes")
	public String clientes(Model model) {
		model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());

		List<ClienteDto> clientesDto = new ArrayList<>();
		List<Cliente> clientes = clienteRepository.findAll();
		for (Cliente c : clientes) {
			ClienteDto cDto = new ClienteDto();
			cDto.setIdCliente(c.getIdCliente());
			cDto.setCodCliente(c.getCodCliente());
			cDto.setTipoCliente(c.getTipoCliente().getDescripcionTipoCliente());
			cDto.setDocumento(c.getTipoDocumento().getDescripcionTipoDocumento() + " - " + c.getNroDocumento());
			cDto.setNombreCompleto(
					c.getApePaternoCliente() + " " + c.getApeMaternoCliente() + ", " + c.getNomCliente());
			clientesDto.add(cDto);
		}
		model.addAttribute("clientes", clientesDto);

		return "clientes";
	}

	@RequestMapping("/clientes/crear")
	public String crearcliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());
		model.addAttribute("tipoClientes", tipoClienteRep.findAll());
		model.addAttribute("generos", generoRep.findAll());
		model.addAttribute("sedes", sedeRep.findAll());
		model.addAttribute("distritos", distritoRep.findAll());
		return "crearcliente";
	}

	@RequestMapping(value = "/clientes/guardar", method = { RequestMethod.POST })
	public String guardarcliente(Model model, @Valid Cliente cliente, BindingResult bindingResult) throws ParseException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());
			model.addAttribute("tipoClientes", tipoClienteRep.findAll());
			model.addAttribute("generos", generoRep.findAll());
			model.addAttribute("sedes", sedeRep.findAll());
			model.addAttribute("distritos", distritoRep.findAll());
            return "crearcliente";
        } else {
        	clienteRepository.save(cliente);
        	return "redirect:/clientes";
        }
	}

}
