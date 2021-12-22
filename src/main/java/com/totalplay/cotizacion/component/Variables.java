package com.totalplay.cotizacion.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.totalplay.utils.arquetipo.component.JWT;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Variables {
	
    private JWT detalleJWT ;
    @Value("${seguridad.jwt.header}")
    public String authorizationHeader;
    @Value("${seguridad.jwt.prefix}")
    public String authorizationPrefix;
    @Value("${seguridad.jwt.detail}")
    public String authorizationDetail;
    @Value("${seguridad.jwt.secret}")
    public String authorizationSecret;
    private String sessionId;
}
