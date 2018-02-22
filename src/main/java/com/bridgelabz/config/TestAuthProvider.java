package com.bridgelabz.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class TestAuthProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		throw new BadCredentialsException(String.format("By Test provider"));
		/*List<GrantedAuthority> authorities = new ArrayList<>();
		 authorities.add(new SimpleGrantedAuthority("STANDARD_USER"));
		 String id = "john_the_fake";
		 String otp = "1234";
		 return new UsernamePasswordAuthenticationToken(id, otp, authorities);*/
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
