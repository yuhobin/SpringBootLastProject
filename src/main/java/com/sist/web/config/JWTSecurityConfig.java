package com.sist.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


import com.sist.web.security.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
/*
 * 	사용자 
 * 	  | /member/login
 * 	login.html
 * 	----------
 * 		| id / pwd => spring security (username, password)
 * 	AuthenticationManager
 * 		|
 * 	UserDetailService
 * 		|
 * 	 DB
 */
public class JWTSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		http
			.csrf(csrf-> csrf.disable()) // 위조 방지
			.formLogin(form->form.disable())
			.httpBasic(basic->basic.disable())
			.authorizeHttpRequests(auth->auth
					.requestMatchers("/","/login","/member").permitAll()
					.requestMatchers("/admin").hasRole("ADMIN")
					.anyRequest().permitAll()
			);
		return http.build();
	}
}
