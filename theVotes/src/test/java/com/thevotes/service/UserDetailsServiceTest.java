package com.thevotes.service;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserDetailsServiceTest {

	@Test
	void generate_encrypted_password() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "test123$";
		String encodedPassword = encoder.encode(rawPassword);

		System.out.println(encodedPassword);
	}

}
