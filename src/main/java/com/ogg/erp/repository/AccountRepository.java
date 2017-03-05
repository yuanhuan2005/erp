package com.ogg.erp.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ogg.erp.connectionpool.ConnDB;
import com.ogg.erp.model.Account;
import com.ogg.erp.util.AESUtils;
import com.ogg.erp.util.DatabaseUtils;

/**
 * 
 * 
 * @author yuanhuan
 */
public class AccountRepository
{
	final static private Log DEBUGGER = LogFactory.getLog(AccountRepository.class);
	final static private String TABLE_NAME = "account";

	private Statement sm = null;
	private ResultSet rs = null;
	private Connection ct = null;

	public void close()
	{
		//关闭资源  
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

	/**
	 * 将数据库查询的ResultSet转换成Account对象
	 * 
	 * @param resultSet
	 * @return
	 */
	private Account convertResultSetToAccount(ResultSet resultSet)
	{
		Account account = null;
		try
		{
			account = new Account();
			account.setUsername(resultSet.getString("username"));
			account.setPassword(AESUtils.decrypt(resultSet.getString("password")));
			account.setType(resultSet.getString("type"));
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e.toString());
		}

		return account;
	}

	/**
	 * 创建一个新的Account
	 * 
	 * @param account
	 * @return 创建结果，true表示成功，false表示失败
	 */
	public boolean createAccount(Account account)
	{
		if (null == account)
		{
			return false;
		}

		String sql = "insert into " + AccountRepository.TABLE_NAME + "(username,password) values('"
		        + account.getUsername() + "','" + account.getPassword() + "')";
		boolean result = new DatabaseUtils().insert(sql);
		return result;
	}

	public Account getAccount(String username)
	{
		Account account = null;

		if (StringUtils.isEmpty(username))
		{
			return account;
		}

		String sql = "select * from " + AccountRepository.TABLE_NAME + " where username = '" + username + "'";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			if (rs.next())
			{
				account = convertResultSetToAccount(rs);
			}
		}
		catch (Exception e)
		{
			AccountRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return account;
	}

}
