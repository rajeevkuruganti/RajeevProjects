package org.kuruganti.util;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

	public static LocalDate getLocalDate(String input) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu").withLocale(Locale.US);
		ZonedDateTime zdatetime = ZonedDateTime.parse(input, f);
		LocalDate ld = zdatetime.toLocalDate();
		return ld;
	}
}
