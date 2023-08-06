package com.thevotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thevotes.domain.User;
import com.thevotes.repositories.UserRepository;
import com.thevotes.security.CustomSecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);

		if(user == null)
			throw new UsernameNotFoundException("User details not valid. Please try again");
		return new CustomSecurityUser(user);
	}

}
