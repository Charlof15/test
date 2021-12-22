package com.totalplay.cotizacion.utilerias;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utilerias {
	
	public static ResponseEntity<String> llamadaApiRest(HttpMethod metodo,String uri,String body,String jwt) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.setBearerAuth(jwt);
		    HttpEntity<String> entity = new HttpEntity<String>(body!=null ? body.toString() : body, headers);
		    return restTemplate.exchange(uri, metodo, entity, String.class);
		} catch (Exception e) {
			log.error("Error en Utilerias:llamaApiRest {}",e.toString());
			throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY,"Error en dependencias internas");
		}
	}

}
