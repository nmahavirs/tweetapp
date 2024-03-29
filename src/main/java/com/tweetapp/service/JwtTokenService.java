package com.tweetapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class JwtTokenService {

	private final Algorithm hmac512;
	private final JWTVerifier verifier;

	public static final long ACCESS_TOKEN_VALIDITY = 30 * 60 * 1000;
	public static final long REFRESH_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;

	public JwtTokenService(@Value("${jwt.secret}") final String secret) {
		this.hmac512 = Algorithm.HMAC512(secret);
		this.verifier = JWT.require(this.hmac512).build();
	}

	public String generateAcccessToken(final String username) {
		return JWT.create().withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY)).sign(this.hmac512);
	}

	public String generateRefreshToken(final String username) {
		return JWT.create().withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY)).sign(this.hmac512);
	}

	public String validateTokenAndGetUsername(final String token) {
		try {
			return verifier.verify(token).getSubject();
		} catch (final JWTVerificationException verificationEx) {
			return null;
		}
	}
}