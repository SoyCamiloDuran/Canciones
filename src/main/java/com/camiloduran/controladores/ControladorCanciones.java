package com.camiloduran.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.camiloduran.modelos.Artista;
import com.camiloduran.modelos.Cancion;
import com.camiloduran.servicios.ServicioArtistas;
import com.camiloduran.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {
	@Autowired
	private final ServicioCanciones servicioCanciones;
	
	@Autowired
	private final ServicioArtistas servicioArtistas;
	
	
	public ControladorCanciones(ServicioCanciones servicioCanciones, ServicioArtistas servicioArtistas) {
		this.servicioCanciones = servicioCanciones;
		this.servicioArtistas = servicioArtistas;
	}

	@GetMapping("/canciones")
	public String desplegarCanciones(Model model) {
		List<Cancion> canciones = this.servicioCanciones.obtenerTodasLasCanciones();
		model.addAttribute("canciones", canciones);
		return "canciones.jsp";
	}
	
	@GetMapping("/canciones/detalle/{idCancion}")
    public String desplegarDetalleCancion(@PathVariable Long idCancion, Model model) {
        Cancion cancion = servicioCanciones.obtenerCancionPorId(idCancion);
        model.addAttribute("cancion", cancion);
        return "detalleCancion.jsp";
    }
	
	@GetMapping("/canciones/formulario/agregar/{idCancion}")
	public String formularioAgregarCancion(@ModelAttribute Cancion cancion, Model model) {
		model.addAttribute("artistas", servicioArtistas.obtenerTodosLosArtistas());
		return "agregarCancion.jsp";
	}
	
	@PostMapping("/canciones/procesa/agregar")
	public String procesarAgregarCancion(@Valid @ModelAttribute Cancion cancion, BindingResult validation) {
		if(validation.hasErrors()) {
			return "agregarCancion.jsp";
		}
		Artista artista = this.servicioArtistas.obtenerArtistaPorId(cancion.getArtista().getId());
		cancion.setArtista(artista);
		this.servicioCanciones.agregarCancion(cancion);
		return "redirect:/canciones";
	}
	
	@DeleteMapping("/canciones/eliminar/{idCancion}")
	public String procesarEliminarCancion(@PathVariable Long idCancion) {
		this.servicioCanciones.eliminaCancion(idCancion);
		return "redirect:/canciones";
	}
	
	@GetMapping("/canciones/formulario/editar/{idCancion}")
	public String formularioEditarCancion(@ModelAttribute Cancion cancion, @PathVariable Long idCancion, Model model) {
		cancion = servicioCanciones.obtenerCancionPorId(idCancion);
		model.addAttribute("cancion", cancion);
		return "editarCancion.jsp";
	}
	
	@PutMapping("/canciones/procesa/editar/{idCancion}")
	public String procesarEditarCancion(@Valid @ModelAttribute Cancion cancion, BindingResult validation, @PathVariable Long idCancion) {
		if(validation.hasErrors()) {
			return "editarCancion.jsp";
		}
		cancion.setId(idCancion);
		this.servicioCanciones.actualizaCancion(cancion);
		return "redirect:/canciones";
	}
}
