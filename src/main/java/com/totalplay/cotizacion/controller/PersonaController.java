package com.totalplay.cotizacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.totalplay.cotizacion.dto.Persona;
import com.totalplay.cotizacion.model.OutputPersona;
import com.totalplay.cotizacion.service.PersonaService;

@RestController
public class PersonaController implements Person{
	
	@Autowired
	private PersonaService personaService;

	@Override
	@GetMapping(path = "/findAll")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<OutputPersona>(personaService.findAll(),HttpStatus.OK);
	}

	@Override
	@GetMapping(path="/id/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<OutputPersona>(personaService.findById(id),HttpStatus.OK);
	}

	@Override
	@PatchMapping(path="/id/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,@RequestBody Persona persona) {
		return new ResponseEntity<OutputPersona>(personaService.actualiza(id,persona),HttpStatus.OK);
	}

	@Override
	@DeleteMapping(path="/id/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<OutputPersona>(personaService.elimina(id),HttpStatus.OK);
	}

	@Override
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Persona persona) {
		return new ResponseEntity<OutputPersona>(personaService.crea(persona),HttpStatus.OK);
	}
	
}
