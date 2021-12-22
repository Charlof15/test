package com.totalplay.cotizacion.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.totalplay.cotizacion.component.Variables;
import com.totalplay.utils.arquetipo.component.NoSecurity;

@Configuration
@EnableWebSecurity
@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.PATCH,RequestMethod.POST,RequestMethod.DELETE})
public class SeguridadConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Variables vr;
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.cors().and().csrf().disable()
		.authorizeRequests()
		.anyRequest().authenticated().and()
		.addFilterAfter(new SeguridadFiltroConfig(vr), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(NoSecurity.getValues()).antMatchers("/actuator/**");
    }
}
