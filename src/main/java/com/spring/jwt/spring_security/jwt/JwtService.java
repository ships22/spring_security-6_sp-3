package com.spring.jwt.spring_security.jwt;

import com.spring.jwt.spring_security.service.security.AppUserDetails;
import io.jsonwebtoken.Claims;


import java.security.Key;

public interface JwtService {

    Claims extractClaims(String token);

    Key getKey();

    String generateToken(AppUserDetails userDetails);

    boolean isValidToken(String token);
}