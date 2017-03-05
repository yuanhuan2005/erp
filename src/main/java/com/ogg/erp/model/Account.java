package com.ogg.erp.model;

public class Account
{
	private String username = "";

	private String password = "";

	private String type = "";

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "Account [username=" + username + ", password=" + password + ", type=" + type + "]";
	}

}