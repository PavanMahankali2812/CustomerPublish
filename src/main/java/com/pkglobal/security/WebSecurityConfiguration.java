package com.pkglobal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	public static final String USER_NAME = "user";
	public static final String USER_PASSWORD = "secret";

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		UserDetails user = User.builder().username(USER_NAME).password(passwordEncoder().encode(USER_PASSWORD))
				.roles("USER").build();

		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public static ExceptionTranslationFilter exceptionTranslationFilter() {
		RestExceptionTranslationFilter exceptionTranslationFilter = new RestExceptionTranslationFilter(
				new RestAuthenticationEntryPoint());

		exceptionTranslationFilter.afterPropertiesSet();
		return exceptionTranslationFilter;
	}

}
