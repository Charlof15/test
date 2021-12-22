package com.totalplay.cotizacion.configuration.security;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.totalplay.cotizacion.component.Variables;
import com.totalplay.utils.arquetipo.component.JWT;
import com.totalplay.utils.arquetipo.component.JwtInfo;
import com.totalplay.utils.arquetipo.output.Output401;
import com.totalplay.utils.arquetipo.output.Output403;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class SeguridadFiltroConfig extends OncePerRequestFilter{

	private String authorizationHeader;
	private String authorizationPrefix;
	private String authorizationDetail;
	private String authorizationSecret;
	private String codificacion;
	private Variables vr;
	
	public SeguridadFiltroConfig(Variables vr) {
		this.authorizationHeader = vr.authorizationHeader;
		this.authorizationPrefix = vr.authorizationPrefix;
		this.authorizationDetail = vr.authorizationDetail;
		this.authorizationSecret = vr.authorizationSecret;
		this.vr = vr;
		this.codificacion = "application/json; charset=UTF-8";
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			configuraPermisosSpring(validateToken(request));
			filterChain.doFilter(request, response);
		} catch (ServletException |ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | NullPointerException | IOException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		    response.addHeader( HttpHeaders.CONTENT_TYPE, codificacion);
		    response.getOutputStream().write(new Gson().toJson(new Output403()).getBytes(StandardCharsets.UTF_8));
			SecurityContextHolder.clearContext();
		}catch (SignatureException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    response.addHeader( HttpHeaders.CONTENT_TYPE, codificacion);
		    response.getOutputStream().write(new Gson().toJson(new Output401("Sin permisos de acceso",String.format("Llave incorrecta : %s", e.getMessage()))).getBytes(StandardCharsets.UTF_8));
		}
	}
	
	protected boolean formatoValidoJwt(HttpServletRequest request) {
		String authenticationHeader = request.getHeader(authorizationHeader);
		return (authenticationHeader != null && authenticationHeader.startsWith(authorizationPrefix));
	}

	protected Claims validateToken(HttpServletRequest request) {
		if (Boolean.TRUE.equals(formatoValidoJwt(request))) {
			String jwtToken = request.getHeader(authorizationHeader).replace(authorizationPrefix, "");
			Claims jwtInformation = Jwts.parser().setSigningKey(authorizationSecret.getBytes()).parseClaimsJws(jwtToken).getBody();
			vr.setSessionId(jwtToken.trim());
			detallesJwt(jwtInformation);
			return jwtInformation;
		}else {
			return null;
		}
	}
	
	protected void configuraPermisosSpring(Claims claims) {
		List<String> authorities = Arrays.asList(claims.get(authorizationDetail).toString());
		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())));
	}
	
	@SuppressWarnings("rawtypes")
	private void detallesJwt(Claims jwtInfo) {
		Gson gson = new Gson();
        Map<String, Object> info = new HashMap<>();
		Arrays.asList(JwtInfo.getValues()).forEach(k -> 
			info.put(k.toString(), jwtInfo.get(k))
		);
		Type gsonType = new TypeToken<HashMap>(){}.getType();
		vr.setDetalleJWT(new Gson().fromJson(gson.toJson(info,gsonType), JWT.class));
	}
}
