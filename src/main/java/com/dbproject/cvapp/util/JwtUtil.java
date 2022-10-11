package com.dbproject.cvapp.util;

import com.dbproject.cvapp.exception.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("${codingshadows.app.jwtSecret}")
    private String SECRET_KEY;
    @Value("${codingshadows.app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(key).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws JwtException {
        final String username = extractEmail(token);
        if (!username.equals((userDetails.getUsername()))) {
            throw new JwtException(username, "token is not valid.");
        }
        if (isTokenExpired(token)) {
            throw new JwtException(username, "due to token expiration.");
        }
        if(!userDetails.isEnabled()){
            throw new JwtException(username, "the user is disabled.");
        }
        if(!userDetails.isAccountNonExpired()){
            throw new JwtException(username, "the user account is expired.");
        }
        if(!userDetails.isCredentialsNonExpired()){
            throw new JwtException(username, "the credentials are expired.");
        }
        if(!userDetails.isAccountNonLocked()){
            throw new JwtException(username, "the account is locked.");
        }
        return true;
    }
}
