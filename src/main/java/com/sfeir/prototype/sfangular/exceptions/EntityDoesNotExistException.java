package com.sfeir.prototype.sfangular.exceptions;

/**
 * This exception is thrown if an entity is not found
 */
public class EntityDoesNotExistException extends Exception {

	private static final long serialVersionUID = -469294449375047874L;
	
	public EntityDoesNotExistException(@SuppressWarnings("rawtypes") Class clazz, Long id) {
		super(clazz.toString() + " not found, id = " + id);
	}
	
	public EntityDoesNotExistException(@SuppressWarnings("rawtypes") Class clazz, String id) {
		super(clazz.toString() + " not found, id = " + id);
	}

}
