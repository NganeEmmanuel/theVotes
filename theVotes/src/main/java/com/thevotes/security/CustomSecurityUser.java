package com.thevotes.security;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.thevotes.domain.User;

public class CustomSecurityUser extends User implements UserDetails {

	private static final long serialVersionUID = -3119867168680197004L;

	public CustomSecurityUser() {}

	public CustomSecurityUser(User user) {
		this.setAuthorities(user.getAuthorities());
		this.setUsername(user.getUsername());
		this.setId(user.getId());
		this.setPassword(user.getPassword());
		this.setName(user.getName());
	}

	@Override
	public Set<Authority> getAuthorities() {
		return super.getAuthorities();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
