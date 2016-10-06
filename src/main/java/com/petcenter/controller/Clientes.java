package com.petcenter.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.petcenter.crud.ClienteRepository;
import com.petcenter.crud.DistritoRepository;
import com.petcenter.crud.GeneroRepository;
import com.petcenter.crud.SedeRepository;
import com.petcenter.crud.TipoClienteRepository;
import com.petcenter.crud.TipoDocumentoRepository;
import com.petcenter.dto.ClienteDto;
import com.petcenter.model.Cliente;
import com.petcenter.model.Distrito;
import com.petcenter.model.GeneroCliente;
import com.petcenter.model.Sede;
import com.petcenter.model.TipoCliente;
import com.petcenter.model.TipoDocumento;

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
		model.addAttribute("tipoDocumentos", tipoDocumentosRep.findAll());
		model.addAttribute("tipoClientes", tipoClienteRep.findAll());
		model.addAttribute("generos", generoRep.findAll());
		model.addAttribute("sedes", sedeRep.findAll());
		model.addAttribute("distritos", distritoRep.findAll());
		return "crearcliente";
	}

	@RequestMapping(value = "/clientes/guardar", method = { RequestMethod.POST })
	public String guardarcliente(@RequestParam("codCliente") String codCliente,
		@RequestParam("tipoCliente") long tipoCliente,
		@RequestParam("tipoDocumento") long tipoDocumento,
		@RequestParam("nroDocumento") String nroDocumento,
		@RequestParam("nomCliente") String nomCliente,
		@RequestParam("apePaternoCliente") String apePaternoCliente,
		@RequestParam("apeMaternoCliente") String apeMaternoCliente,
		@RequestParam("generoCliente") long generoCliente,
		@RequestParam("fecNacCliente") String fecNacCliente,
		@RequestParam("sede") long sede,
		@RequestParam("distrito") long distrito,
		@RequestParam("direcCliente") String direcCliente,
		@RequestParam("celCliente") String celCliente,
		@RequestParam("telfDomCliente") String telfDomCliente,
		@RequestParam("telfTraCliente") String telfTraCliente,
		@RequestParam("correoElectCliente") String correoElectCliente) throws ParseException {
		
		Cliente cliente = new Cliente();
		
		cliente.setCodCliente(codCliente);
		
		TipoCliente tcEncontrao =  tipoClienteRep.findByIdTipoCliente(tipoCliente);
		cliente.setTipoCliente(tcEncontrao);
		
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "test:  "+ tcEncontrao.getDescripcionTipoCliente());
		
		TipoDocumento tdEncontrado = tipoDocumentosRep.findByIdTipoDocumento(tipoDocumento);
		cliente.setTipoDocumento(tdEncontrado);
		
		
		cliente.setNroDocumento(nroDocumento);
		cliente.setNomCliente(nomCliente);
		cliente.setApePaternoCliente(apePaternoCliente);
		cliente.setApeMaternoCliente(apeMaternoCliente);
		
		GeneroCliente gcEncontrado = generoRep.findByIdGeneroCliente(generoCliente);
		cliente.setGeneroCliente(gcEncontrado);
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaNacimiento = format.parse(fecNacCliente);
		cliente.setFecNacCliente(fechaNacimiento);
		
		Sede sEncontrada = sedeRep.findByIdSede(sede);
		cliente.setSede(sEncontrada);
		
		Distrito dEncontrado = distritoRep.findByIdDistrito(distrito);
		cliente.setDistrito(dEncontrado);
		
		cliente.setDirecCliente(direcCliente);
		cliente.setCelCliente(celCliente);
		cliente.setTelfDomCliente(telfDomCliente);
		cliente.setTelfTraCliente(telfTraCliente);
		cliente.setCorreoElectCliente(correoElectCliente);
		
		cliente.setIndNotificaciones(true);
		cliente.setEstadoCliente(1);
		
		clienteRepository.save(cliente);
		
		return "redirect:/clientes";
	}

}
