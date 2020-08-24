package br.com.zerosystems.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zerosystems.cursomc.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
			JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
		
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req
			,HttpServletResponse res){
		
		try {
			CredenciaisDTO creds = new ObjectMapper()
					.readValue(res.getInputStream(),CredenciaisDTO.class)
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		
		
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req
			,HttpServletResponse res
			,FilterChain chain
			,Authentication auth) {
		
	}
	
}
