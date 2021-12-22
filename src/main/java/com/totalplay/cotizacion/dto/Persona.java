package com.totalplay.cotizacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Persona {
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer id;
		
	@Schema(name = "nombre",description = "Nombre de la persona a crear/editar", example = "Juan Perez")
	private String nombre;
	
	@Schema(name = "edad",description = "Edad de la persona que se crea/edita", example = "30")
	private Integer edad;
	
	@Schema(name = "direccion",description = "Dirección de la persona que se crea/edita", example = "Calle Yobain 295")
	private String direccion;

}
