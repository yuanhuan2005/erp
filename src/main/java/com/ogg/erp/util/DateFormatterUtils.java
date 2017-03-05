package com.ogg.erp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatterUtils
{
	final static private String YEAR_FORMAT = "yyyy";
	final static private String MONTH_FORMAT = "MM";
	final static private String YEAR_MONTH_FORMAT = "yyyy-MM";
	final static private String DATE_FORMAT = "yyyy-MM-dd";
	final static private String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static int getCurrentYear()
	{
		DateFormat formatter = new SimpleDateFormat(DateFormatterUtils.YEAR_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String date = formatter.format(new Date());
		return Integer.valueOf(date);
	}

	public static int getCurrentMonth()
	{
		DateFormat formatter = new SimpleDateFormat(DateFormatterUtils.MONTH_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String date = formatter.format(new Date());
		return Integer.valueOf(date);
	}

	public static String getCurrentYearMonth()
	{
		DateFormat formatter = new SimpleDateFormat(DateFormatterUtils.YEAR_MONTH_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String date = formatter.format(new Date());
		return date;
	}

	public static String getCurrentDate()
	{
		DateFormat formatter = new SimpleDateFormat(DateFormatterUtils.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String date = formatter.format(new Date());
		return date;
	}

	public static String getCurrentTime()
	{
		DateFormat formatter = new SimpleDateFormat(DateFormatterUtils.TIME_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String date = formatter.format(new Date());
		return date;
	}

	public static Date convertStringToDate(String dateString)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormatterUtils.TIME_FORMAT);

		try
		{
			return simpleDateFormat.parse(dateString);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Enter main");

		System.out.println("current  time=" + DateFormatterUtils.getCurrentTime());
		Date date = DateFormatterUtils.convertStringToDate(DateFormatterUtils.getCurrentTime());
		System.out.println("date = " + date.toString());

		System.out.println("System.currentTimeMillis=" + System.currentTimeMillis());
		System.out.println("new Date().getTime()=" + new Date().getTime());
		System.out.println("new  Date().getTime()="
		        + DateFormatterUtils.convertStringToDate(DateFormatterUtils.getCurrentTime()).getTime());

		System.out.println("End main");
	}
}
