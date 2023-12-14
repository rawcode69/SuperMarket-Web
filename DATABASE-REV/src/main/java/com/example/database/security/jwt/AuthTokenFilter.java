package com.example.database.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.database.security.UserDetailServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {

      String jwt = parseJwt(request); // get the token that sent by the user

      if (jwt != null && jwtUtils.validateJwtToken(jwt)) { // check not null && validate the token using the method that created in the jwtUtills.

        String username = jwtUtils.getUsernameFromToken(jwt); // get userNmae from the method in jwtUtills.

        UserDetails userDetails = userDetailService.loadUserByUsername(username); // Getting the useDetails. This method was implenmented in userDetailsServiceImpl.useDetails included the both passWord and the UserName.

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
            userDetails.getAuthorities());// Authenticate the UserName and Password. The rolls can be set to the getAuthoriites() method. Such as admin, member, user

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // a security context should be created after the validation. This is also like a authentication object. When a token is validated, security context is created to do the same as the what happen when user logged provoding the userName and password.It means the security context contain the usedetails.(User name and password.)
      }

    } catch (Exception e) {
      System.err.println("Cannot set User auth");
      System.err.println("Exception during token validation: " + e.getMessage());
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    
    String authHeader = request.getHeader("Authorization"); // get the head from the reqeust

    if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) { // check whether string has a text and is it start with the Bearer?
      return authHeader.substring(7);  //then remove the fist 7 characters(including space after the word bearer)
    }

    return null;
  }

  // Summary:  Filter the token and create the security context for it.

}
