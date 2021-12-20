package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Jugadores;
import com.example.demo.entity.Plantilla;

public interface PlantillaService {

	public List<Plantilla> findAll();
	public Plantilla findById(Long id);
	public Plantilla save(Plantilla plantilla);
	public void delete(Long id);
	public List<Jugadores> findAllJugador();
	
	
}
