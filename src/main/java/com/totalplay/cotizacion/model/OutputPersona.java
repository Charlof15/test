package com.totalplay.cotizacion.model;

import java.util.List;

import com.totalplay.cotizacion.dto.Persona;
import com.totalplay.utils.arquetipo.output.Output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputPersona extends Output{

	@Schema(name = "personas",description = "Lista de personas encontradas")
	private List<Persona> personas;
}
