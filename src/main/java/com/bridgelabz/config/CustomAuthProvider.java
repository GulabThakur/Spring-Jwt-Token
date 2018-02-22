package com.bridgelabz.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.bridgelabz.model.User;
import com.bridgelabz.repository.UserRepository;

//@Component
public class CustomAuthProvider implements AuthenticationProvider {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Map<String, String> details = new LinkedHashMap<>((Map<String, String>) authentication.getDetails());
				//(Map<String, String>) authentication.getDetails();
		String id = details.get("id");
		String otp = details.get("otp");
		
		 User user = userRepository.findByUsername(id);

	        if(user == null) {
	            throw new BadCredentialsException(String.format("The username %s doesn't exist", id));
	        }
	        
	        if (!user.getPassword().equals(otp)) {
	        	throw new BadCredentialsException(String.format("Wrong otp"));
			}

	        List<GrantedAuthority> authorities = new ArrayList<>();
	        user.getRoles().forEach(role -> {
	            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
	        });
		
		
		
		/*List<GrantedAuthority> authorities = new LinkedList<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ADMIN_USER");
		authorities.add(authority);*/
		return new UsernamePasswordAuthenticationToken(id, null, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	

}
