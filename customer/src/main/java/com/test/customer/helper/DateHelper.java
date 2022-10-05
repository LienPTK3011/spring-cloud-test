package com.test.customer.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Convert string to local date.
	 *
	 * @param date the date
	 * @return the local date
	 */
	public static LocalDate convertStringToLocalDate(String date, String pattern) {

		// Pattern of date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDate.parse(date, formatter);
	}
}
