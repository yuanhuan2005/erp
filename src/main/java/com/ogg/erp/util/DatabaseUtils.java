package com.ogg.erp.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ogg.erp.connectionpool.ConnDB;

public class DatabaseUtils
{
	final static private Log DEBUGGER = LogFactory.getLog(DatabaseUtils.class);

	private Statement sm = null;
	private ResultSet rs = null;
	private Connection ct = null;

	public void close()
	{
		//¹Ø±Õ×ÊÔ´  
		try
		{
			if (rs != null)
			{
				rs.close();
				rs = null;
			}
			if (sm != null)
			{
				sm.close();
				sm = null;
			}
			if (ct != null)
			{
				ct.close();
				ct = null;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// execute selection language
	public ResultSet select(String sql)
	{
		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			if (rs.next())
			{
				return rs;
			}
		}
		catch (Exception e)
		{
			DatabaseUtils.DEBUGGER.debug(e.toString());
		}
		finally
		{
			close();
		}
		return null;

	}

	// execute insertion language
	public boolean execute(String sql)
	{
		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			return true;
		}
		catch (Exception e)
		{
			DatabaseUtils.DEBUGGER.debug(e.toString());
		}
		finally
		{
			close();
		}
		return false;
	}

	// execute insertion language
	public boolean insert(String sql)
	{
		return execute(sql);
	}

	// execute delete language
	public boolean delete(String sql)
	{
		return execute(sql);
	}

	// execute update language
	public boolean update(String sql)
	{
		return execute(sql);
	}
}
