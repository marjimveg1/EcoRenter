package com.ispp.EcoRenter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
 

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/smallholding/**").permitAll()
				.antMatchers("/owner/smallholding/**").hasAnyAuthority("OWNER")
				.anyRequest().authenticated()
				.and()
			.formLogin()
//				.loginPage("/login")
				.and()
			.logout()
				.logoutSuccessUrl("/");
	}

	/*
	 * Este método simula que hay un usuario en la base de datos con:
	 * usuario: user
	 * contraseña: password
	 * rol: USER
	 * 
	 * Debe eliminarse una vez que esté implementada la lógica de 
	 * usuarios para que utilice la base de datos*/
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}