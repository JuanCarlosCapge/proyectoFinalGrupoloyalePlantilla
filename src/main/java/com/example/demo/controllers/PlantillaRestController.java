package com.example.demo.controllers;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Jugadores;
import com.example.demo.entity.Plantilla;
import com.example.demo.service.PlantillaService;







@RestController
@RequestMapping("/api")
public class PlantillaRestController {

	@Autowired
	private PlantillaService plantillaservice;
	
	
	@GetMapping("/plantilla")
	public List<Plantilla> index(){
		return plantillaservice.findAll();
	}
	
	
	@GetMapping("/plantilla/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Plantilla plantilla = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			plantilla = plantillaservice.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar consulta en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(plantilla == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Plantilla>(plantilla,HttpStatus.OK);
	}
	
	@PostMapping("/plantilla")
	public ResponseEntity<?> create(@RequestBody Plantilla plantilla){
		Plantilla plantillanew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			plantillanew = plantillaservice.save(plantilla);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar insert en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido creado con éxito!");
		response.put("cliente", plantillanew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/plantilla/{id}")
	public ResponseEntity<?> update(@RequestBody Plantilla plantilla, @PathVariable Long id) {
		Plantilla plantillaActual= plantillaservice.findById(id);
		
		Plantilla plantillaUpdate = null;
		Map<String,Object> response = new HashMap<>();
		
		if(plantillaActual == null){
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: ".concat(id.toString().concat("no existe el id en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			plantillaActual.setApellido(plantilla.getApellido());
			plantillaActual.setNombre(plantilla.getNombre());
			plantillaActual.setEmail(plantilla.getEmail());
			plantillaActual.setTelefono(plantilla.getTelefono());
			plantillaActual.setCreatedAt(plantilla.getCreatedAt());
			plantillaActual.setJugadores(plantilla.getJugadores());
			
			plantillaUpdate = plantillaservice.save(plantillaActual);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al actualizar el cliente en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido actualizado con éxito!");
		response.put("cliente", plantillaUpdate);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("plantilla/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			Plantilla plantilla = plantillaservice.findById(id);
			String nombreFotoAnterior = plantilla.getImagen();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
					
					archivoFotoAnterior.delete();
				}
			}
			
			plantillaservice.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El cliente ha sido eliminado con éxito!");

		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("plantilla/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String,Object> response = new HashMap<>();
		
		Plantilla plantilla = plantillaservice.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString()+"_"+ archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(),rutaArchivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = plantilla.getImagen();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
					
					archivoFotoAnterior.delete();
				}
			}
			
			plantilla.setImagen(nombreArchivo);
			
			plantillaservice.save(plantilla);
			
			response.put("cliente",plantilla);
			response.put("mensaje","Has subido correctamente la imagen: "+ nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()){
			throw new RuntimeException("Error no se puede cargar la imagen "+nombreFoto);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		//cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+recurso.getFilename()+"\"");
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"file=\""+recurso.getFilename()+"\"");
	
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	@GetMapping("plantilla/jugadores")
	public List<Jugadores> listarJugadores(){
		return plantillaservice.findAllJugador();
	}
	
}
