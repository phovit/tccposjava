package br.edu.unitri.posjava.tcc.med4you.configurations;

import br.edu.unitri.posjava.tcc.med4you.jwt.JWTAuthenticationFilter;
import br.edu.unitri.posjava.tcc.med4you.jwt.JWTLoginFilter;
import br.edu.unitri.posjava.tcc.med4you.security.WSAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private WSAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").authorities("ROLE_USER");

		auth.authenticationProvider(authProvider);

	}


	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/login/error").permitAll()
				.antMatchers( "/js/**", "/css/**").permitAll()
				.antMatchers( "/index.html","/","/login").permitAll()
				.antMatchers( "/users/isLogged","/users/logged").permitAll()
				.antMatchers( "/screens/medicine.html","/medicine", "/medicine/findByName/*","/screens/medicine_search.html").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/screens/partials/login.html").permitAll()
				.loginProcessingUrl("/login").permitAll()
				.and()


				// filtra requisições de login
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)

				// filtra outras requisições para verificar a presença do JWT no header
				.addFilterBefore(new JWTAuthenticationFilter(),
						UsernamePasswordAuthenticationFilter.class)
/*
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/logout.html").permitAll()*/;
	}



}
