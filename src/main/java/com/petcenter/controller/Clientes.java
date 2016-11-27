package com.petcenter.controller;


import com.petcenter.crud.*;
import com.petcenter.dto.ClienteBusquedaDto;
import com.petcenter.dto.ClienteDto;
import com.petcenter.model.Cliente;
import com.petcenter.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
	public void clientesInit(HttpServletRequest request, Model model,
			@RequestParam(value = "buscarpor", required = false, defaultValue = "0") String buscarpor, @Valid ClienteBusquedaDto clientebusqueda, 
			BindingResult bindingResult) {
		clientes(request, 1, model, buscarpor, clientebusqueda, bindingResult);
	}

	@RequestMapping("/clientes/{page}")
	public String clientes(HttpServletRequest request, @PathVariable("page") int page, Model model,
			@RequestParam(value = "buscarpor", required = false, defaultValue = "0") String buscarpor,
			@Valid @ModelAttribute("clientebusqueda") ClienteBusquedaDto clientebusqueda, BindingResult bindingResult) {
		
		model.addAttribute("clientebusqueda", clientebusqueda);
		
		model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());

		List<ClienteDto> clientesDto = new ArrayList<>();

		Page<Cliente> clientes = null;
		if (buscarpor.equals("1")) {
			
			if (clientebusqueda.getCodigo().isEmpty()) {
				ObjectError error = new ObjectError("codigo", "Debe ingresar el codigo");
				bindingResult.addError(error);
			}
			if (bindingResult.hasErrors()) {
				return "clientes";
			} else {
				clientes = clienteRepository.findByCodCliente(clientebusqueda.getCodigo().trim(), new PageRequest(page - 1, 1));
			}
			
		} else if (buscarpor.equals("2")) {
			
			if (clientebusqueda.getNombre().isEmpty()) {
				ObjectError error = new ObjectError("nombre", "Debe ingresar el Nombre");
				bindingResult.addError(error);
			}
			if (clientebusqueda.getNroDocumento().isEmpty()) {
				ObjectError error = new ObjectError("nroDocumento", "Debe ingresar el Número de Documento");
				bindingResult.addError(error);
			}
			if (clientebusqueda.getApMaterno().isEmpty()) {
				ObjectError error = new ObjectError("nombre", "Debe ingresar el Apellido Paterno");
				bindingResult.addError(error);
			}
			if (clientebusqueda.getApMaterno().isEmpty()) {
				ObjectError error = new ObjectError("nombre", "Debe ingresar el Apellido Materno");
				bindingResult.addError(error);
			}
			if (bindingResult.hasErrors()) {
				return "clientes";
			} else {
				clientes = clienteRepository.findByNomClienteAndTipoDocumentoAndNroDocumentoAndApePaternoClienteAndApeMaternoCliente
						(clientebusqueda.getNombre().trim(), tipoDocumentosRep.findByIdTipoDocumento(clientebusqueda.getTipoDocumento()), 
						 clientebusqueda.getNroDocumento().trim(), clientebusqueda.getApPaterno().trim(), clientebusqueda.getApMaterno().trim(), 
						 new PageRequest(page - 1, 1));
			}
			
		} else {
			
			clientes = clienteRepository.findAll(new PageRequest(page - 1, 6));
			
		}
		
		boolean existaData = false;

		if (clientes.getTotalPages() != 0) {
			
			existaData = true;

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

			model.addAttribute("totalPages", clientes.getTotalPages());

			String previusPage = "";

			if (clientes.previousPageable() != null) {
				previusPage = String.valueOf(clientes.previousPageable().getPageNumber() + 1);
			} else {
				previusPage = "1";
			}

			model.addAttribute("previusPage", previusPage);

			String nextPage = "";

			if (clientes.nextPageable() != null) {
				nextPage = String.valueOf(clientes.nextPageable().getPageNumber() + 1);
			} else {
				nextPage = String.valueOf(clientes.getTotalPages());
			}
			
			model.addAttribute("nextPage", nextPage);

		} else {
			
			model.addAttribute("totalPages", 1);
			
		}
		
		model.addAttribute("existaData", existaData);
		
		request.getSession().setAttribute("t", "fff");
		new Util().log(this.getClass(), "test 4 :" + request.getSession().getAttribute("t"));

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
	public String guardarcliente(Model model, @Valid Cliente cliente, BindingResult bindingResult)
			throws ParseException {
		model.addAttribute("fechaNacimiendo", new Util().DatetoString(cliente.getFecNacCliente()));
		if (clienteRepository.findByNroDocumento(cliente.getNroDocumento()) != null) {
			ObjectError error = new ObjectError("nroDocumento", "Cliente ya se encuentra registrado.");
			bindingResult.addError(error);
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());
			model.addAttribute("tipoClientes", tipoClienteRep.findAll());
			model.addAttribute("generos", generoRep.findAll());
			model.addAttribute("sedes", sedeRep.findAll());
			model.addAttribute("distritos", distritoRep.findAll());
			model.addAttribute("cliente", cliente);
			return "crearcliente";
		} else {
			cliente.setCodCliente("CLI" + StringUtils.leftPad(String.valueOf(clienteRepository.countRows()+1), 7, '0'));
			clienteRepository.save(cliente);
			return "redirect:/clientes";
		}
	}

	@RequestMapping("/clientes/modificar/{id}")
	public String modificarcliente(@PathVariable("id") long id, Model model) {
		Cliente cliente = clienteRepository.findByIdCliente(id);
		model.addAttribute("cliente", cliente);
		model.addAttribute("fechaNacimiendo", new Util().DatetoString(cliente.getFecNacCliente()));
		model.addAttribute("clienteEnc", clienteRepository.findByIdCliente(id));
		model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());
		model.addAttribute("tipoClientes", tipoClienteRep.findAll());
		model.addAttribute("generos", generoRep.findAll());
		model.addAttribute("sedes", sedeRep.findAll());
		model.addAttribute("distritos", distritoRep.findAll());
		return "modificarcliente";
	}

	@RequestMapping(value = "/clientes/actualizar", method = { RequestMethod.POST })
	public String actualizarcliente(Model model, @Valid Cliente cliente, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("cliente", cliente);
			model.addAttribute("fechaNacimiendo", new Util().DatetoString(cliente.getFecNacCliente()));
			model.addAttribute("clienteEnc", clienteRepository.findByIdCliente(cliente.getIdCliente()));
			model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());
			model.addAttribute("tipoClientes", tipoClienteRep.findAll());
			model.addAttribute("generos", generoRep.findAll());
			model.addAttribute("sedes", sedeRep.findAll());
			model.addAttribute("distritos", distritoRep.findAll());
			return "modificarcliente";
		} else {
			clienteRepository.save(cliente);
			return "redirect:/clientes";
		}
	}

}
