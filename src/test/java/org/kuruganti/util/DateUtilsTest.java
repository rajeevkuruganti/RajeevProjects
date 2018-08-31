package org.kuruganti.util;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testGetLocalDate() {
		String input = "Thu Aug 30 21:24:10 EDT 2018";
		LocalDate ld = DateUtils.getLocalDate(input);
		assertTrue("2018-08-30".equals(ld.toString()));
	}

}
