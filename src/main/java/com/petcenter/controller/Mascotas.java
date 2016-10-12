package com.petcenter.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.petcenter.crud.ClienteRepository;
import com.petcenter.crud.EspecieRepository;
import com.petcenter.crud.GeneroMascotaRepository;
import com.petcenter.crud.MascotaRepository;
import com.petcenter.crud.RazaRepository;
import com.petcenter.crud.RelClienteMascotaRepository;
import com.petcenter.crud.TipoDocumentoRepository;
import com.petcenter.dto.MascotaBusquedaDto;
import com.petcenter.dto.MascotaDto;
import com.petcenter.model.Mascota;

@Controller
public class Mascotas {
	
	@Autowired
	MascotaRepository mascotaRep;
	
	@Autowired
	ClienteRepository clienteRep;
	
	@Autowired
	TipoDocumentoRepository tipoDocumentos;
	
	@Autowired
	RazaRepository razaRep;
	
	@Autowired
	GeneroMascotaRepository generoMascotaRep;
	
	@Autowired
	RelClienteMascotaRepository relCLienteMascotaRep;
	
	@Autowired
	EspecieRepository especieRep;
	
	@RequestMapping("/mascotas")
	public void mascotasInit(HttpServletRequest request, Model model, 
			@RequestParam(value = "buscarpor", required=false, defaultValue = "0") String buscarpor,
			@Valid @ModelAttribute("mascotabusqueda") MascotaBusquedaDto mascotabusqueda, BindingResult bindingResult) {
		mascotas(request, 1, model, buscarpor, mascotabusqueda, bindingResult);
	}
	
	@RequestMapping("/mascotas/{page}")
	public String mascotas(HttpServletRequest request, @PathVariable("page") int page, Model model,
			@RequestParam(value = "buscarpor", required=false, defaultValue = "0") String buscarpor, 
			@Valid @ModelAttribute("mascotabusqueda") MascotaBusquedaDto mascotabusqueda, BindingResult bindingResult) {
		
		model.addAttribute("mascotabusqueda", mascotabusqueda);
		
		model.addAttribute("tipoDocumentos", tipoDocumentos.findAll());
		
		Page<Mascota> mascotas = null;
		
		if(buscarpor.equals("1")){
			
			if (mascotabusqueda.getCodigo().isEmpty()) {
				ObjectError error = new ObjectError("codigo", "Debe ingresar el Codigo");
				bindingResult.addError(error);
			}
			if (bindingResult.hasErrors()) {
				return "mascotas";
			} else {
				mascotas = mascotaRep.findByCodMascota(mascotabusqueda.getCodigo().trim(), new PageRequest(page - 1, 6));
			}
			
		} else if (buscarpor.equals("2")){
			
			if (mascotabusqueda.getNombre().isEmpty()) {
				ObjectError error = new ObjectError("nombre", "Debe ingresar el Nombre");
				bindingResult.addError(error);
			}
			if (bindingResult.hasErrors()) {
				return "mascotas";
			} else {
				mascotas = mascotaRep.findByNomMascota(mascotabusqueda.getNombre().trim(), new PageRequest(page - 1, 6));
			}
		
		} else if (buscarpor.equals("3")){
			
			if (mascotabusqueda.getNroDocumento().isEmpty()) {
				ObjectError error = new ObjectError("nroDocumento", "Debe ingresar el Nro de Documento");
				bindingResult.addError(error);
			}
			if (bindingResult.hasErrors()) {
				return "mascotas";
			} else {
				mascotas = mascotaRep.findByTipoDocunento(mascotabusqueda.getTipoDocumento(), mascotabusqueda.getNroDocumento().trim(), new PageRequest(page - 1, 6));
			}
		
		} else if (buscarpor.equals("4")){
			
			if (mascotabusqueda.getNombreCliente().isEmpty()) {
				ObjectError error = new ObjectError("nombreCliente", "Debe ingresar el Nombre del Cliente");
				bindingResult.addError(error);
			}
			if (mascotabusqueda.getApPaterno().isEmpty()) {
				ObjectError error = new ObjectError("apPaterno", "Debe ingresar el Apellido Paterno del Cliente");
				bindingResult.addError(error);
			}
			if (mascotabusqueda.getApMaterno().isEmpty()) {
				ObjectError error = new ObjectError("nombreCliente", "Debe ingresar el Apellido Materno del Cliente");
				bindingResult.addError(error);
			}
			if (bindingResult.hasErrors()) {
				return "mascotas";
			} else {
				mascotas = mascotaRep.findByNombreCompletoCliente(mascotabusqueda.getNombreCliente().trim(), 
						mascotabusqueda.getApPaterno().trim(), mascotabusqueda.getApMaterno().trim(), new PageRequest(page - 1, 6));
			}
			
		} else {
			
			mascotas = mascotaRep.findAll(new PageRequest(page - 1, 6));
			
		}
		
		List<MascotaDto> mascotasDto = new ArrayList<>();
		
		boolean existaData = false;
		
		if(mascotas.getTotalPages() != 0){
			
			existaData = true;
			
			for(Mascota m : mascotas) {
				MascotaDto mascotaDto = new MascotaDto();
				mascotaDto.setIdMascota(m.getIdMascota());
				mascotaDto.setCodigo(m.getCodMascota());
				mascotaDto.setMascota(m.getNomMascota() + " (" + m.getRaza().getEspecie().getDescripcionEspecie()+" - "+m.getRaza().getDescripcionRaza() + " )");
				mascotaDto.setEstado(m.isEstadoMascota());
				mascotaDto.setDueno(m.getCliente().getApePaternoCliente() + " " + m.getCliente().getApeMaternoCliente() + ", " + m.getCliente().getNomCliente());
				mascotaDto.setRelacionConMascota(m.getRelClienteMascota().getDescripcionRelClienteMascota());
				mascotasDto.add(mascotaDto);
			}
			
			model.addAttribute("mascotas", mascotasDto);
			
			model.addAttribute("totalPages", mascotas.getTotalPages());
			
			String previusPage = "";
			
			if(mascotas.previousPageable() != null){
				previusPage = String.valueOf(mascotas.previousPageable().getPageNumber()+1);
			} else {
				previusPage = "1";
			}
			
			model.addAttribute("previusPage", previusPage);
			
			String nextPage = "";
			
			if(mascotas.nextPageable() != null) {
				nextPage = String.valueOf(mascotas.nextPageable().getPageNumber()+1);
			} else {
				nextPage = String.valueOf(mascotas.getTotalPages());
			}
			
			model.addAttribute("nextPage", nextPage);
			
		} else {
			
			model.addAttribute("totalPages", 1);
			
		}
		
		model.addAttribute("existaData", existaData);
		
		return "mascotas";
		
	}
	
	@RequestMapping("/mascotas/crear")
	public String crearmascota(Model model) {
		model.addAttribute("mascota", new Mascota());
		model.addAttribute("clientes", clienteRep.findAll());
		model.addAttribute("especies", especieRep.findAll());
		model.addAttribute("generos", generoMascotaRep.findAll());
		model.addAttribute("relacionclientes", relCLienteMascotaRep.findAll());
		return "crearmascota";
	}
	
	@RequestMapping(value = "/mascotas/guardar", method = { RequestMethod.POST })
	public String guardarmascota(@RequestParam("file") MultipartFile file, Model model, @Valid Mascota mascota, 
			BindingResult bindingResult, Exception exception) throws IOException {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("clientes", clienteRep.findAll());
			model.addAttribute("especies", especieRep.findAll());
			model.addAttribute("generos", generoMascotaRep.findAll());
			model.addAttribute("relacionclientes", relCLienteMascotaRep.findAll());
			model.addAttribute("mascota", mascota);
			return "crearmascota";
		} else {
			mascota.setCodMascota("MAS" + StringUtils.leftPad(String.valueOf(mascotaRep.countRows() + 1), 7, '0'));
			mascota.setFotoMascota(file.getBytes());
			mascotaRep.save(mascota);
			return "redirect:/mascotas";
		}
		
	}
	
}
