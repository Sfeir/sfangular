package com.sfeir.prototype.sfangular.dtos;

import java.io.Serializable;
import java.util.Collection;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -8473419903128060685L;
	
	private Long id = null;
	
	private String lastname = null;
	
	private String firstname = null;
	
	private String login = null;
	
	private String email = null;
	
	private String creatingDate = null;
	
	private String creatingTime = null;
	
	private String updatingDate = null;
	
	private String updatingTime = null;
	
	private String leavingDate = null;
	
	private String leavingTime = null;
	
	private Collection<Long> rights = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatingDate() {
		return creatingDate;
	}

	public void setCreatingDate(String creatingDate) {
		this.creatingDate = creatingDate;
	}

	public String getCreatingTime() {
		return creatingTime;
	}

	public void setCreatingTime(String creatingTime) {
		this.creatingTime = creatingTime;
	}

	public String getUpdatingDate() {
		return updatingDate;
	}

	public void setUpdatingDate(String updatingDate) {
		this.updatingDate = updatingDate;
	}

	public String getUpdatingTime() {
		return updatingTime;
	}

	public void setUpdatingTime(String updatingTime) {
		this.updatingTime = updatingTime;
	}

	public String getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(String leavingDate) {
		this.leavingDate = leavingDate;
	}

	public String getLeavingTime() {
		return leavingTime;
	}

	public void setLeavingTime(String leavingTime) {
		this.leavingTime = leavingTime;
	}

	public Collection<Long> getRights() {
		return rights;
	}

	public void setRights(Collection<Long> rights) {
		this.rights = rights;
	}

}
