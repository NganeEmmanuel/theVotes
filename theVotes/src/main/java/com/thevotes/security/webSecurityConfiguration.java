package com.thevotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class webSecurityConfiguration {
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("emmanuelngane2018@gmail.com")
            .password(encoder.encode("asdfasdf"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth ->{
				auth.requestMatchers("/").permitAll();
				auth.anyRequest().hasRole("USER");
				try {
					auth.and().formLogin().loginPage("/login").permitAll();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			})
			.httpBasic(Customizer.withDefaults())
			.build();
			
	}
    
}