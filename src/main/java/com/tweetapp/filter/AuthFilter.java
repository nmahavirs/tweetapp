package com.tweetapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tweetapp.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class AuthFilter extends OncePerRequestFilter {
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (!request.getRequestURI().contains("register") && !request.getRequestURI().contains("login")) {
			String authHeader = request.getHeader("Authorization");
			if (authHeader != null && authHeader.startsWith("Bearer")) {
				String jwtToken = authHeader.substring(7);
				try {
					String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
//					throw ne
				} catch (ExpiredJwtException e) {
					System.out.println("JWT Token has expired");
				}
			}
		}
	}
}