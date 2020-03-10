package com.ispp.EcoRenter.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	DataSource dataSource;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/renter").hasRole("RENTER")
		.antMatchers("/owners").hasRole("OWNER")
		.antMatchers("/admin").hasAuthority("ADMIN")
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/sign") //Aqui lo suyo es poner la url del listado de parcelas o lo que sea cuando este logeado, con la opcion de sign out
		
		//.failureUrl("/login-error"), ahora mismo cuando peta se controla en la vista /login, pero se puede hacer una vista nueva, como veamos.
		.and()
		.logout()
		.logoutSuccessUrl("/")
		;
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {


		/*auth.jdbcAuthentication()
		.dataSource(dataSource)	
		.usersByUsernameQuery("select username,password,is_banned "
				+ "from user_account "
				+ "where username = ?")
		.authoritiesByUsernameQuery("select u.username, a.authority "
				+ "from eco_renter.user_account u "
				+ "join eco_renter.user_account_authorities a "
				+ "on u.id = a.user_account_id "
				+ "where username = ?"); 
		 */
		auth.userDetailsService(userDetailsService);

	}



	@Bean
	public static PasswordEncoder passwordEncoder() {
	      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
	@Bean
	 public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	      authenticationProvider.setUserDetailsService(userDetailsService);
	      authenticationProvider.setPasswordEncoder(passwordEncoder());
	      return authenticationProvider;
	 }
}

