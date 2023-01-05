package com.hcl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hcl.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

//	protected void configure(HttpSecurity http) throws Exception {
//
//		http
//			.authorizeHttpRequests()
//				.antMatchers("/message").permitAll()
//				.antMatchers("/personalMessage").hasRole("ADMIN")
//					.anyRequest()
//						.authenticated()
//							.and()
//								.formLogin();
//
//	}
	
	
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeHttpRequests()
				.antMatchers("/signin", "/registrationPage", "/register").permitAll()
				.anyRequest()
						.authenticated()
							.and()
								.formLogin()
									.loginPage("/signin")
										.loginProcessingUrl("/dologin")
											.defaultSuccessUrl("/welcomePage", true);

	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("inside configurer...1");

//		String suhailPasswordEncoded = this.passwordEncoder().encode("Ali");
//		System.out.println("suhailPasswordEncoded " + suhailPasswordEncoded);
//		
//		String shaliniEncodedPassword = this.passwordEncoder().encode("Singh");
//		System.out.println("shaliniEncodedPassword "+shaliniEncodedPassword);
//
//		auth.inMemoryAuthentication().withUser("Suhail").password(suhailPasswordEncoded).roles("EMPLOYEE");
//		auth.inMemoryAuthentication().withUser("Shalini").password(shaliniEncodedPassword).roles("ADMIN");
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(this.passwordEncoder());
		System.out.println("inside configurer...2");
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		
//		return NoOpPasswordEncoder.getInstance();
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("Inside PasswordEncoder()...");
		return new BCryptPasswordEncoder();
	}

}
