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

  @Value("${app.secret}")
  private String jwtSecret;

  @Value("${app.jwtExpiration}")
  private String jwtExpiration;

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)); // generate the key
  }

  public String generateJwtToken(Authentication authentication) { // This method generate the token

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime() + Long.parseLong(jwtExpiration) ))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean validateJwtToken(String authToken) { // validate the token. This mehtod is used to validate the token in the AuthTokenFilter

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

  public String getUsernameFromToken(String authToken) { // This method is used get the userName from token in the AuthTokenFilter class.
    return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken).getBody().getSubject();
  }

  // Summary: This class provides methods for token filter process and for authController.

}
