package com.sfeir.prototype.sfangular.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * DTOs contain only strings instead of dates
 * This has been done that way because we can not get a proper date object in javascript, we can only have strings for date and time
 * This class is used to switch between strings and date/time
 */
public class DateUtils {

	private static final String DATE_FORMAT = ("dd/MM/YYYY");
	private static final String TIME_FORMAT = ("HH:mm");

	private static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat(DATE_FORMAT);
	private static final SimpleDateFormat TIME_FORMATER = new SimpleDateFormat(TIME_FORMAT);
	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat(DATE_FORMAT + TIME_FORMAT);
	
	public static final String dateToString(Date date) {
		if (date != null) {
			return DATE_FORMATER.format(date);
		}
		return null;
	}
	
	public static final Date stringToDate(String date) throws ParseException {
		if (StringUtils.isNotEmpty(date)) {
			return DATE_FORMATER.parse(date);
		}
		return null;
	}
	
	public static final String timestampToDateString(Date timestamp) {
		if (timestamp != null) {
			return DATE_FORMATER.format(timestamp);
		}
		return null;
	}
	
	public static final String timestampToTimeString(Date timestamp) {
		if (timestamp != null) {
			return TIME_FORMATER.format(timestamp);
		}
		return null;
	}
	
	public static final Date stringToTimestamp(String date, String time) throws ParseException {
		if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(time)) {
			return TIMESTAMP_FORMAT.parse(date + time);
		}
		return null;
	}
}
