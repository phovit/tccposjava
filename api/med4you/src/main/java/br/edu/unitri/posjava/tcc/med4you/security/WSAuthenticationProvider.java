package br.edu.unitri.posjava.tcc.med4you.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.service.UserService;

@Component
public class WSAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userservice;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = new User();
		user.setPassword(password);
		user.setUsername(username);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Void> resposta;

		if( userservice.autenticate(user) ) {
			resposta = ResponseEntity.status(HttpStatus.OK).build();
		}else {
			resposta = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		if (resposta.getStatusCode().is2xxSuccessful() == false) {
			throw new RuntimeException("Erro ao autenticar");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, authorities);

		return token;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
		
	}

}
