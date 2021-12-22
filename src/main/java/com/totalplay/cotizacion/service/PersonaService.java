package com.totalplay.cotizacion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.totalplay.cotizacion.dto.Persona;
import com.totalplay.cotizacion.mapper.PersonaMapper;
import com.totalplay.cotizacion.model.OutputPersona;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonaService {
	
	@Autowired
	private PersonaMapper personaMapper;
	
	public OutputPersona findAll() {
		OutputPersona output = new OutputPersona();
		try {
			output.setPersonas(personaMapper.findAll()); 
			output.setMensaje("Operacion exitosa");
			output.setDescription("Persona encontrados");
			return output;
		} catch (Exception e) {
			log.error("Error al buscar personas {}", e.toString());
			output.setMensaje("Error no controlado");
			output.setDescription("Error al actualizar persona");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error no controlado");
		}
	}
	
	public OutputPersona findById(Integer id) {
		OutputPersona output = new OutputPersona();
		try {
			List<Persona> listPersona = new ArrayList<>();
			listPersona.add(personaMapper.findById(id));
			output.setPersonas(listPersona);
			output.setMensaje("Operacion exitosa");
			output.setDescription("Persona encontrado");
			return output;
		} catch (Exception e) {
			log.error("Error al buscar persona {}", e.toString());
			output.setMensaje("Error no controlado");
			output.setDescription("Error al buscar persona");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error no controlado");
		}
	}
	
	public OutputPersona elimina(Integer id) {
		OutputPersona output = new OutputPersona();
		try {
			personaMapper.deletePersona(id);
			output.setMensaje("Operacion exitosa");
			output.setDescription("Persona eliminada");
			return output;
		} catch (Exception e) {
			log.error("Error al eliminar persona {}", e.toString());
			output.setMensaje("Error no controlado");
			output.setDescription("Error al eliminar persona");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error no controlado");
		}
	}
	
	public OutputPersona actualiza(Integer id,Persona persona) {
		OutputPersona output = new OutputPersona();
		try {
			Persona original = personaMapper.findById(id);
			List<Persona> listPersona = new ArrayList<>();
			if (original == null) 
				throw new ResponseStatusException(HttpStatus.NO_CONTENT,"No se encontro el usuario a actualizar");
			
			original.setNombre(persona.getNombre() == null ? original.getNombre() : persona.getNombre());
			original.setEdad(persona.getEdad() == null ? original.getEdad() : persona.getEdad());
			original.setDireccion(persona.getDireccion() == null ? original.getDireccion() : persona.getDireccion());
			original.setId(id);
			personaMapper.updatePersona(original);
			listPersona.add(personaMapper.findById(id));
			output.setPersonas(listPersona);
			output.setMensaje("Operacion exitosa");
			output.setDescription("Persona actualizada correctamente");
			return output;
		} catch (Exception e) {
			log.error("Error al actualizar persona {}", e.toString());
			output.setMensaje("Error no controlado");
			output.setDescription("Error al actualizar persona");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error no controlado");
		}
	}
	
	public OutputPersona crea(Persona persona) {
		OutputPersona output = new OutputPersona();
		try {
			List<Persona> listPersona = new ArrayList<>();
			output.setMensaje("Operacion exitosa");
			Integer id = personaMapper.insertPersona(persona);
			listPersona.add(personaMapper.findById(id));
			output.setPersonas(listPersona); 
			return output;
		} catch (Exception e) {
			log.error("Error al crerar persona {}", e.toString());
			output.setMensaje("Error no controlado");
			output.setDescription("Error al crear persona");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error no controlado : " + e.toString());
		}
	}

}
