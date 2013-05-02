package com.sfeir.prototype.sfangular.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sfeir.prototype.sfangular.beans.Auth;
import com.sfeir.prototype.sfangular.exceptions.EntityDoesNotExistException;
import com.sfeir.prototype.sfangular.exceptions.UnauthorizedException;
import com.sfeir.prototype.sfangular.exceptions.UserNotLoggedException;

public class ParentController {

	private static final Logger logger = Logger.getLogger(ParentController.class);

	/**
	 * Session login object
	 */
	@Autowired
	protected Auth auth;
	
	/**
	 * Check if the user is logged in
	 * @throws UserNotLoggedException if the user is not logged in
	 */
	protected void checkAuthLogin() throws UserNotLoggedException {
		auth.checkIsLogged();
	}

	/**
	 * These handlers deal with specific exceptions and are used to return specific HTTP codes
	 */
	@ExceptionHandler({ UserNotLoggedException.class })
	ResponseEntity<String> handleLoginExceptions(Exception e) {
		return handleException(e, false, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
	}

	@ExceptionHandler({ UnauthorizedException.class })
	ResponseEntity<String> handleUnauthorizedExceptions(Exception e) {
		return handleException(e, false, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler({ EntityDoesNotExistException.class, IllegalArgumentException.class })
	ResponseEntity<String> handleBadRequests(Exception e) {
		return handleException(e, true, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	ResponseEntity<String> handleUnknownExceptions(Exception e) {
		return handleException(e, true, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Common exception method
	 * @param e exception to process
	 * @param logError log the exception
	 * @param httpStatus HTTP code to return
	 * @return response
	 */
	protected ResponseEntity<String> handleException(Exception e, boolean logError, HttpStatus httpStatus) {
		if (logError) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<String>(String.format("{ \"reason\": \"%s\"}", e.getMessage().replaceAll("\"", "'")).replaceAll("\n", " "), httpStatus);
	}
}
