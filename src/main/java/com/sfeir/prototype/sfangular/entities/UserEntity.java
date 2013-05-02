package com.sfeir.prototype.sfangular.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Users")
@Table(name = "USERS")
public class UserEntity {

	@Id
	@GeneratedValue
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_USERS", allocationSize = 1)*/
	@Column(name = "ID")
	private Long id = null;
	
	@Column(name = "LASTNAME")
	private String lastname = null;
	
	@Column(name = "FIRSTNAME")
	private String firstname = null;
	
	@Column(name = "LOGIN")
	private String login = null;
	
	@Column(name = "EMAIL")
	private String email = null;
	
	@Column(name = "CREATING_DATETIME")
	private Date creatingDatetime = null;
	
	@Column(name = "UPDATING_DATETIME")
	private Date updatingDatetime = null;
	
	@Column(name = "LEAVING_DATETIME")
	private Date leavingDatetime = null;
	
	@ManyToMany
	@JoinTable(name = "USERS_RIGHTS", joinColumns = @JoinColumn(name = "ID_USER"), inverseJoinColumns = @JoinColumn(name = "ID_RIGHT"))
	private Collection<RightEntity> rights = null;

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

	public Date getCreatingDatetime() {
		return creatingDatetime;
	}

	public void setCreatingDatetime(Date creatingDatetime) {
		this.creatingDatetime = creatingDatetime;
	}

	public Date getUpdatingDatetime() {
		return updatingDatetime;
	}

	public void setUpdatingDatetime(Date updatingDatetime) {
		this.updatingDatetime = updatingDatetime;
	}

	public Date getLeavingDatetime() {
		return leavingDatetime;
	}

	public void setLeavingDatetime(Date leavingDatetime) {
		this.leavingDatetime = leavingDatetime;
	}

	public Collection<RightEntity> getRights() {
		return rights;
	}

	public void setRights(Collection<RightEntity> rights) {
		this.rights = rights;
	}

}
