package com.totalplay.cotizacion.controller;

import org.springframework.http.ResponseEntity;

import com.totalplay.cotizacion.dto.Persona;
import com.totalplay.cotizacion.utilerias.OpenApiConfig;
import com.totalplay.utils.arquetipo.output.Output;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@OpenApiConfig
public interface Person {

	 @Operation(summary = "Ejemplo busca todo",
	 description = "Retorna personas de tabla temporal",
	 security = @SecurityRequirement(name = "Authorization"))
	 @ApiResponse(responseCode = "200", description = "Información encontrada", content = @Content(schema = @Schema(implementation = Output.class)))
	 public ResponseEntity<?> findAll();
	 
	 @Operation(summary = "Ejemplo busca por id",
	 description = "Busca persona por id en tabla temporal",
	 security = @SecurityRequirement(name = "Authorization"))
	 @ApiResponse(responseCode = "200", description = "Información encontrada", content = @Content(schema = @Schema(implementation = Output.class)))
	 public ResponseEntity<?> findById(@Schema(name = "id",description = "Id de la persona que se busca", example = "5") Integer id);
	 
	 @Operation(summary = "Ejemplo actualiza",
	 description = "Actualiza persona de tabla temporal",
	 security = @SecurityRequirement(name = "Authorization"))
	 @ApiResponse(responseCode = "200", description = "Información encontrada", content = @Content(schema = @Schema(implementation = Output.class)))
	 public ResponseEntity<?> update(@Schema(name = "id",description = "Id de la persona que se edita", example = "5") Integer id,@RequestBody Persona persona);
	 
	 @Operation(summary = "Ejemplo elimina",
	 description = "Elimina persona de tabla temporal",
	 security = @SecurityRequirement(name = "Authorization"))
	 @ApiResponse(responseCode = "200", description = "Información encontrada", content = @Content(schema = @Schema(implementation = Output.class)))
	 public ResponseEntity<?> delete(@Schema (name = "id",description = "Id de la persona que se elimina", example = "5") Integer id);
	 
	 @Operation(summary = "Ejemplo crea",
	 description = "Crea un registro de personas en tabla temporal",
	 security = @SecurityRequirement(name = "Authorization"))
	 @ApiResponse(responseCode = "200", description = "Información encontrada", content = @Content(schema = @Schema(implementation = Output.class)))
	 public ResponseEntity<?> create(@RequestBody Persona persona);
	
}
