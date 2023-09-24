package com.akash.employeemanagementsystem.auth_service;


import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private static final String SECRET_KEY = "8212a0ec24627f8fe328522443f4f85156d13d88fae183b8c245645879de64f1";


    //    This is a method to create token without extra claims.
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

//    This is a method to create token with extra claims.
    public String generateToken(Map<String,Object> extraClaims , UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    This is method is used to extract the username(email) from the token.
    public String extractUserEmail(String jwtToken) {
        return extractClaim(jwtToken,Claims::getSubject);
    }
//  Checks whether token is valid or not.
    public boolean isTokenValid(String jwtToken,UserDetails userDetails){
        final String username = extractUserEmail(jwtToken);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

// Checks Whether token is expired or not.
    private boolean isTokenExpired(String jwtToken) {
        return extractExpirationDate(jwtToken).before(new Date(System.currentTimeMillis()));
    }


    //    This is the method which is used to extract the expiration date from token.
    private Date extractExpirationDate(String jwtToken){
        return extractClaim(jwtToken,Claims::getExpiration);
    }

//    This is method is used to get all the claims from the token.
    private Claims getAllExtraClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

//    Returns a key which is used to sign while create a token and also validate while decode.
    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    This method is used to extract the particular claims needed.
    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllExtraClaims(token);
        return claimsResolver.apply(claims);
    }

}
