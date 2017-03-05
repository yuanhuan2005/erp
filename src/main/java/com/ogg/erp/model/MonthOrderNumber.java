package com.ogg.erp.model;

public class MonthOrderNumber
{
	private String month;

	private int number;

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	@Override
	public String toString()
	{
		return "MonthOrderNumber [month=" + month + ", number=" + number + "]";
	}

}