/**
 * 
 */
package com.mmt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author pkumar
 *
 */
@Configuration
@EnableWebSecurity
public class SequrityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);
	}

	/*@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	

		http.authorizeRequests().antMatchers("/mmt/**","/login").permitAll()
		.antMatchers("/login/**").permitAll()
		.antMatchers("/report/**").permitAll()
		.antMatchers("/customer/**").access("hasAnyRole('ROLE_USER','ROLE_SUPER_USER')")
		.antMatchers("/inventory/**").access("hasAnyRole('ROLE_USER','ROLE_SUPER_USER')")
		.antMatchers("/purchase/**").access("hasAnyRole('ROLE_USER','ROLE_SUPER_USER')")
		//.antMatchers("/sell/**").access("hasAnyRole('ROLE_USER','ROLE_SUPER_USER')")
		//.antMatchers("/super/**").access("hasRole('ROLE_SUPER_USER')")
		//.antMatchers("/customer/**").access("hasRole('ROLE_SUPER_USER')")
		//.antMatchers("/inventory/**").access("hasRole('ROLE_SUPER_USER')")
		//.antMatchers("/purchase/**").access("hasRole('ROLE_SUPER_USER')")
		.antMatchers("/sell/sellitem.html").access("hasRole('ROLE_USER')")
		.antMatchers("/gst/**").access("hasRole('ROLE_SUPER_USER')")
		
		.and().formLogin().loginPage("/mmt/login.html").failureUrl("/mmt/login.html?error")
		
		.loginProcessingUrl("/mmt/logindo.html")
				

		.and().logout().clearAuthentication(true).logoutSuccessUrl("/mmt/login.html?logout").and().csrf()
		.and().exceptionHandling().accessDeniedPage("/mmt/accessdenied.html");;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}


}
