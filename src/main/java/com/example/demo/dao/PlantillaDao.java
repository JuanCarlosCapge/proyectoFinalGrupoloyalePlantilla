package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Jugadores;
import com.example.demo.entity.Plantilla;


public interface PlantillaDao extends CrudRepository<Plantilla, Long>{

	@Query("from Jugadores")
	public List<Jugadores> findAllJugador();

}
