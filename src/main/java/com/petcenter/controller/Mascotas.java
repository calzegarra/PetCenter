package com.petcenter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petcenter.crud.ClienteRepository;
import com.petcenter.crud.EspecieRepository;
import com.petcenter.crud.GeneroMascotaRepository;
import com.petcenter.crud.MascotaRepository;
import com.petcenter.crud.RazaRepository;
import com.petcenter.crud.RelClienteMascotaRepository;
import com.petcenter.crud.TipoDocumentoRepository;
import com.petcenter.dto.MascotaDto;
import com.petcenter.model.Mascota;
import com.petcenter.util.Util;

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
	public void mascotasInit(HttpServletRequest request, Model model, @RequestParam(value = "buscarpor", required=false, defaultValue = "0") String buscarpor) {
		new Util().log(this.getClass(), "test :"+ buscarpor);
		mascotas(request, 1, model, buscarpor, "");
	}
	
	@RequestMapping("/mascotas/{page}")
	public String mascotas(HttpServletRequest request, @PathVariable("page") int page, Model model,
			@RequestParam(value = "buscarpor", required=false, defaultValue = "0") String buscarpor, 
			String nroDocumento) {
		
		model.addAttribute("tipoDocumentos", tipoDocumentos.findAll());
		
		Page<Mascota> mascotas = mascotaRep.findAll(new PageRequest(page - 1, 6));
		
		List<MascotaDto> mascotasDto = new ArrayList<>();
		
		boolean existaData = false;
		
		if(mascotas.getTotalPages() != 0){
			
			existaData = true;
			
			for(Mascota m : mascotas) {
				MascotaDto mascotaDto = new MascotaDto();
				mascotaDto.setIdMascota(m.getIdMascota());
				mascotaDto.setCodigo(m.getCodMascota());
				mascotaDto.setRaza(m.getRaza().getEspecie().getDescripcionEspecie()+" - "+m.getRaza().getDescripcionRaza());
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
	
}
