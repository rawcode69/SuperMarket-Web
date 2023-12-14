package com.example.database.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.database.security.jwt.AuthEntryPoint;
import com.example.database.security.jwt.AuthTokenFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Autowired
  private AuthEntryPoint unaouthorizedHandler;

  @Bean // Bean are used to point out the configuratons. Poiting out the this configuration gonna work like this. 
  public UserDetailsService userDetailsService() {
    return userDetailService; // return userDetail service
  }

  @Bean
  public AuthTokenFilter authenticationJwTokenFilter() { // return the AuthTokeFilter type Obeject. This class was created before.
    return new AuthTokenFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() { //To encode the password
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {

    return authConfig.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 

    http.csrf(csrf -> csrf.disable())
      .exceptionHandling(exception -> exception.authenticationEntryPoint(unaouthorizedHandler)) // handle if there any exception
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // mange session and give the session creation policy as STATELESS 
      .authorizeHttpRequests(auth -> auth
      .requestMatchers("/auth/***").permitAll()
    //give authorized to the all request coming from the path /auth/. (*** means the all) for users without logging.
      .anyRequest().authenticated()); //to access apart from the any other request above mentioned path users are need be logged in
      
      http.authenticationProvider(authenticationProvider());// set the authentication provider.

      http.addFilterBefore(authenticationJwTokenFilter(),UsernamePasswordAuthenticationFilter.class); //add the authenticaton filter

      return http.build();
  }

}
