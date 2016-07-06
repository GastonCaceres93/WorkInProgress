package com.gcaceres.mongoDB.blog.entity;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Comment {

	private String author;
	private String email;
	private String body;

	public Comment() {
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
