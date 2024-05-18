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
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

	@Value("${security.jwt.expiration-minutes}")
	private long EXPIRATION_MINUTES;

	@Value("${security.jwt.secret-key}")
	private String SECRET_KEY;

	public String generateToken(User user, Map<String, Object> extraClaims) {

		Date issuedAt = new Date(System.currentTimeMillis());
		Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

		return Jwts.builder().setClaims(extraClaims).setSubject(user.getName()).setIssuedAt(issuedAt)
				.setExpiration(expiration).setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.signWith(SignatureAlgorithm.HS256, generateKey()).compact();
	}

	public String extractUsername(String jwt) {

		return extractAllClaims(jwt).getSubject();

	}

	private Key generateKey() {
		byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
		System.out.println("Mi token: " + Arrays.toString(secretAsBytes));
		return Keys.hmacShaKeyFor(secretAsBytes);

	}

	private Claims extractAllClaims(String jwt) {
		 return Jwts.parser()
	                .setSigningKey(generateKey())
	                .parseClaimsJws(jwt)
	                .getBody();
	}
}