package com.amy.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "199D43AA954AB1DA6AB133A6CB371";


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUserName(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);

    }

    public Boolean isTokenValid(String token,UserDetails userDetails){//se necesita validar que el token pertenece al dueño del user details
        final String username = extractUser(token);
        if (username.equals(userDetails.getUsername()))&& !isTokenExpired(token)//si el nombre de usuario coincide con el nombre de usuario del userdetail


    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());//cuando se expira el token
    }

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }


    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails)
    {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 ))
                .signWith(getSigningKey(), SignatureAlgorithm.ES256)
                .compact()

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build().parseSignedClaims(token).getPayload();

    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
