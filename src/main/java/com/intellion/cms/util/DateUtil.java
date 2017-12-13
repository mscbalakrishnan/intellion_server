package com.intellion.cms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static String getShortDateTime(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
	}

	public static String convertDate(Long date) {
		if (date == null || date == 0) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date startDate = new Date(date);
		String dateStr = sdf.format(startDate);
		return dateStr;
	}

	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date startDate = new Date();
		String dateStr = sdf.format(startDate);
		return dateStr;
	}

	public static Long getLongCurrentDate() {
		Date startDate = new Date();
		return startDate.getTime();
	}

	public static int getAge(long dob) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		Date startDate = new Date(dob);
		String yearStr = sdf.format(startDate);
		Date currentDate = new Date();
		String currentYearStr = sdf.format(currentDate);
		int currentYear = Integer.valueOf(currentYearStr);
		int year = Integer.valueOf(yearStr);
		if (currentYear < year) {
			return 0;
		}
		return currentYear - year;
	}

	public static long convertDate(String date) throws ParseException {
		if (date == null || date.isEmpty()) {
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date startDate = sdf.parse(date);
		return startDate.getTime();
	}

	public static String addDays(long longDate, int numberOfDays) {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date startDate = new Date(longDate);
		Date nextDate = DateUtils.addDays(startDate, numberOfDays);
		long nextLongDate = nextDate.getTime();
		return convertDate(nextLongDate);
	}

	public static String addDays(String dateStr, int numberOfDays) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date startDate = sdf.parse(dateStr);
		Date nextDate = DateUtils.addDays(startDate, numberOfDays);
		long nextLongDate = nextDate.getTime();
		// System.out.println(nextLongDate);
		return convertDate(nextLongDate);
	}
}
