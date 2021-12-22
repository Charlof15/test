package com.totalplay.cotizacion.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.totalplay.cotizacion.dto.Persona;

@Mapper
public interface PersonaMapper {

	public List<Persona> findAll();
	
	public Persona findById(Integer id);
	
	public Integer updatePersona(Persona persona);
	
	public Integer deletePersona(Integer id);
	
	public Integer insertPersona(Persona persona);
}
