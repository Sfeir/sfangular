package com.sfeir.prototype.sfangular.utils;

/**
 * If a checkbox is not checked, the associated boolean is not submitted
 * The purpose of this class is to solve this issue
 */
public class BooleanUtils {
	
	public static Boolean dtoBooleanToBoolean(Boolean dtoBoolean) {
		if (dtoBoolean != null) {
			return dtoBoolean;
		}
		return new Boolean(false);
	}
}
