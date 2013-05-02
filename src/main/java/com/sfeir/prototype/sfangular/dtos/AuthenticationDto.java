package com.sfeir.prototype.sfangular.dtos;

import java.io.Serializable;

public class AuthenticationDto implements Serializable {

	private static final long serialVersionUID = 8015678035663249105L;

	private String login = null;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
