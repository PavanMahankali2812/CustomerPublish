package com.pkglobal.security;

/*
 * @EnableWebSecurity
 * 
 * @Configuration public class WebSecurityConfiguration extends
 * WebSecurityConfigurerAdapter {
 * 
 * public static final String USER_NAME = "user"; public static final String
 * USER_PASSWORD = "secret";
 * 
 * @Bean
 * 
 * @Override public AuthenticationManager authenticationManagerBean() throws
 * Exception { return super.authenticationManagerBean(); }
 * 
 * @Bean
 * 
 * @Override public UserDetailsService userDetailsService() {
 * 
 * UserDetails user =
 * User.builder().username(USER_NAME).password(passwordEncoder().encode(
 * USER_PASSWORD)) .roles("USER").build();
 * 
 * return new InMemoryUserDetailsManager(user); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() {
 * 
 * return new BCryptPasswordEncoder(); }
 * 
 * }
 */