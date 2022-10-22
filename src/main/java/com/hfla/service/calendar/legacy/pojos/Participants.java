package com.hfla.service.calendar.legacy.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Participants {
	private String name;
	private String email;
	@JsonIgnore
	private String comment;
	@JsonIgnore
	private String status;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
