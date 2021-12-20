package com.example.demo.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.PlantillaDao;
import com.example.demo.entity.Jugadores;
import com.example.demo.entity.Plantilla;


@Service
public class PlantillaServiceImpl  implements PlantillaService{

	@Autowired
	private PlantillaDao plantillaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Plantilla> findAll() {
		
		return (List<Plantilla> )plantillaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Plantilla findById(Long id) {
		
		return plantillaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Plantilla save(Plantilla plantilla) {
		
		return plantillaDao.save(plantilla);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		plantillaDao.deleteById(id);
	}

	@Override
	public List<Jugadores> findAllJugador() {
		
		return plantillaDao.findAllJugador();
	}





}
