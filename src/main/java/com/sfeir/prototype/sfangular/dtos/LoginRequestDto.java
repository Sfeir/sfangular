package com.sfeir.prototype.sfangular.dtos;

import java.io.Serializable;

public class LoginRequestDto implements Serializable {

	private static final long serialVersionUID = 8015678035663249105L;

	private String login = null;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
