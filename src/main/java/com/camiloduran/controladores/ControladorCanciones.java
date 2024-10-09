package com.camiloduran.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.camiloduran.modelos.Cancion;
import com.camiloduran.servicios.ServicioCanciones;

@Controller
public class ControladorCanciones {
	@Autowired
	private final ServicioCanciones servicioCanciones;
	
	public ControladorCanciones(ServicioCanciones servicioCanciones) {
		this.servicioCanciones = servicioCanciones;
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
}
