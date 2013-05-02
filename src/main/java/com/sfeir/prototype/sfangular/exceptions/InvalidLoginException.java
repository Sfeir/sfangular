package com.sfeir.prototype.sfangular.exceptions;

/**
 * Exception thrown if the authentication has failed
 */
public class InvalidLoginException extends Exception {

	private static final long serialVersionUID = 5607093202316295119L;

	public InvalidLoginException(String login) {
		super("Invalid login (username = " + login + ")");
	}
}
