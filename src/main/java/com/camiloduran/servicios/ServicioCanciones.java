package com.camiloduran.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camiloduran.modelos.Cancion;
import com.camiloduran.repositorios.RepositorioCanciones;

@Service
public class ServicioCanciones {
	@Autowired
	private final RepositorioCanciones repositorioCanciones;
	
	public ServicioCanciones(RepositorioCanciones repositorioCanciones) {
		this.repositorioCanciones = repositorioCanciones;
	}
	
	public List<Cancion> obtenerTodasLasCanciones(){
		return this.repositorioCanciones.findAll();
	}
	
	public Cancion obtenerCancionPorId(Long id) {
		return repositorioCanciones.findById(id).orElse(null);
    }
}
