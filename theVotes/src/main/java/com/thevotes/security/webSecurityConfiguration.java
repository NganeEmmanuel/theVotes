package com.thevotes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class webSecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(getPasswordEncoder());
	}
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetails user = User.withUsername("emmanuelngane2018@gmail.com")
//            .password(encoder.encode("asdfasdf"))
//            .roles("USER")
//            .build();
//        return new InMemoryUserDetailsManager(user);
//    }

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests(auth ->{
				auth.requestMatchers("/").permitAll();
				auth.requestMatchers("/register").permitAll();
				auth.requestMatchers("/admin/**").hasRole("ADMIN");
				auth.anyRequest().hasAnyRole("USER","ADMIN");
				try {
					auth.and().logout().logoutSuccessUrl("/login");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					auth.and().formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/dashboard")
						.permitAll();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			})
			.httpBasic(Customizer.withDefaults())
			.build();

	}

}