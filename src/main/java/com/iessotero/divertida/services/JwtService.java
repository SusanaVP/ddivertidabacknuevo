package com.iessotero.divertida.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iessotero.divertida.model.User;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * Servicio para la gestión de tokens JWT (JSON Web Tokens).
 */
@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    /**
     * Genera un token JWT para un usuario con reclamos adicionales.
     *
     * @param user        el usuario para el cual se genera el token.
     * @param extraClaims reclamos adicionales a incluir en el token.
     * @return el token JWT generado.
     */
    public String generateToken(User user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el nombre de usuario (email) del token JWT.
     *
     * @param jwt el token JWT del que se extraerá el nombre de usuario.
     * @return el nombre de usuario extraído del token.
     */
    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Key generateKey() {
        byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
    }
}
