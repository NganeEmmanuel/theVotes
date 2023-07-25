package com.thevotes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Comment {
	private CommentId pk;
	private String text;

	@EmbeddedId
	public CommentId getPk() {
		return pk;
	}
	@Column(length=5000)
	public String getText() {
		return text;
	}
	public void setPk(CommentId pk) {
		this.pk = pk;
	}
	public void setText(String text) {
		this.text = text;
	}
}
