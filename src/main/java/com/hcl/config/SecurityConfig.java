package com.hcl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
				.antMatchers("/signin").permitAll()
				.anyRequest()
						.authenticated()
							.and()
								.formLogin()
									.loginPage("/signin")
										.loginProcessingUrl("/dologin")
											.defaultSuccessUrl("/welcomePage", true);

	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("inside configurer...");

		String passwordEncoded = this.passwordEncoder().encode("Ali");
		System.out.println("Encoded Password " + passwordEncoded);

		auth.inMemoryAuthentication().withUser("Suhail").password(passwordEncoded).roles("EMPLOYEE");
		auth.inMemoryAuthentication().withUser("Shalini").password(this.passwordEncoder().encode("Singh")).roles("ADMIN");
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		
//		return NoOpPasswordEncoder.getInstance();
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

}
