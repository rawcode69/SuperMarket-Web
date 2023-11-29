package com.example.database.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

  @Value("$(app.secret)")
  private String jwtSecret;

  @Value("$(app.jwtExpiration)")
  private String jwtExpiration;

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String gernerateJwtToken(Authentication authentication) {

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() + jwtExpiration))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();

  }

  public boolean validateJwtToken(String authToken) {

    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      System.out.println("Invalid Token");
    } catch (ExpiredJwtException e) {
      System.out.println("Expired Token");
    } catch (UnsupportedJwtException e) {
      System.out.println("Unsupported token format");
    } catch (IllegalArgumentException e) {
      System.out.println("Token Blanck");
    }

    return false;

  }

  public String getUsernameFromToken(String authToken) {
    return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken).getBody().getSubject();
  }

}
