package com.camiloduran.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.camiloduran.modelos.Artista;
import com.camiloduran.servicios.ServicioArtistas;

import jakarta.validation.Valid;

@Controller
public class ControladorArtistas {
	@Autowired
	private final ServicioArtistas servicioArtistas;

	public ControladorArtistas(ServicioArtistas servicioArtistas) {
		this.servicioArtistas = servicioArtistas;
	}
	
	@GetMapping("/artistas")
	public String desplegarArtistas(Model model) {
		List<Artista> artistas = this.servicioArtistas.obtenerTodosLosArtistas();
		model.addAttribute("artistas", artistas);
		return "artistas.jsp";
	}
	
	@GetMapping("/artistas/detalle/{idArtista}")
    public String desplegarDetalleArtista(@PathVariable Long idArtista, Model model) {
        Artista artista = servicioArtistas.obtenerArtistaPorId(idArtista);
        model.addAttribute("artista", artista);
        return "detalleArtista.jsp";
    }
	
	@GetMapping("/artistas/formulario/agregar/{idArtista}")
	public String formularioAgregarArtista(@ModelAttribute Artista artista) {
		return "agregarArtista.jsp";
	}
	
	@PostMapping("/artistas/procesa/agregar")
	public String procesarAgregarArtista(@Valid @ModelAttribute Artista artista, BindingResult validation) {
		if(validation.hasErrors()) {
			return "agregarArtista.jsp";
		}
		this.servicioArtistas.agregarArtista(artista);
		return "redirect:/artistas";
	}
	
}
