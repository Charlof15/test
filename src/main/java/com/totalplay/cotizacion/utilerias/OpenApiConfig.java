package com.totalplay.cotizacion.utilerias;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.totalplay.utils.arquetipo.output.Output401;
import com.totalplay.utils.arquetipo.output.Output403;
import com.totalplay.utils.arquetipo.output.Output424;
import com.totalplay.utils.arquetipo.output.Output500;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@OpenAPIDefinition(
info = @Info(title = "${ffm.api.title}",version = "${ffm.api.version}",description = "${ffm.api.description}",
contact = @Contact(name = "${ffm.developer.name}",email = "${ffm.developer.email}",url = "${ffm.developer.url}")))
@SecurityScheme(name = "Authorization",type = SecuritySchemeType.APIKEY,in = SecuritySchemeIn.HEADER,description = "Bearer JWT generado por FFM-MS-SEG")
@ApiResponse(responseCode = "204", description = "Recurso no encontrado")
@ApiResponse(responseCode = "401", description = "Sin permisos de acceso", content = @Content(schema = @Schema(implementation = Output401.class)))		
@ApiResponse(responseCode = "403", description = "JWT no valido", content = @Content(schema = @Schema(implementation = Output403.class)))
@ApiResponse(responseCode = "424", description = "Error en dependencias internas", content = @Content(schema = @Schema(implementation = Output424.class)))
@ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content(schema = @Schema(implementation = Output500.class)))
public @interface OpenApiConfig {}
