/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vtw.portal.auth.authorization.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author Joe Grandja
 * @since 0.1.0
 */
@EnableWebSecurity
public class DefaultSecurityConfig {

	@Autowired
	private DataSource dataSource;

	// formatter:off
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
		//.cors().and()
		.csrf().disable()
				// .authorizeRequests(authorizeRequests ->
				// authorizeRequests.anyRequest().authenticated())
				.authorizeRequests()
				.antMatchers("/auth/login").permitAll()
				.antMatchers("/doLogin").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/pleiades/login").permitAll()
				// .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.anyRequest().authenticated().and()
				// .httpBasic(withDefaults())
				
//				.csrf()
//	            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//	        .and()
	        .formLogin()
				.loginPage("/login").loginProcessingUrl("/doLogin")
				//.and()
				.and().logout().clearAuthentication(true)
				.invalidateHttpSession(true)
				.logoutSuccessUrl("http://localhost:8080/logout")
				.deleteCookies("JSESSIONID")
				.and()
				.sessionManagement().maximumSessions(1);
		// .logoutUrl("/logout")
		// .logoutSuccessUrl("/")
		// .and().cors();
		return http.build();
	}
	// formatter:on

	//@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		//configuration.addAllowedHeader("*");
		//configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
		configuration.applyPermitDefaultValues();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	UserDetailsService users() {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
