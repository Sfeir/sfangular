package com.sfeir.prototype.sfangular.exceptions;

/**
 * Exception thrown if the user is supposed to be logged in and he is not
 */
public class UserNotLoggedException extends Exception {

	private static final long serialVersionUID = 5607093202316295119L;

	public UserNotLoggedException() {
		super("User not logged");
	}
}
