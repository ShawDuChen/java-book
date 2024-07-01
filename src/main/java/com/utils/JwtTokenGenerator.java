package com.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenGenerator {
    private static final String SECRET_KEY = "OQIWUEnaksdjasgd87646546QWEYWQIUAJKSGDJASGDWQVWQEHQWEIWQYEIQWOYASHDKJASHDKJASHdbjkAGUWQTEYQWUqpoi";

    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 24 * 7);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", username);
        claims.put("iat", now.getTime());
        claims.put("exp", expiration.getTime());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}
