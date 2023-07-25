package com.thevotes.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class VoteId implements Serializable  {
	private static final long serialVersionUID = 2578727012482871768L;
	private User user;
	private Feature feature;
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
}
