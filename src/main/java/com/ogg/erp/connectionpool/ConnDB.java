package com.ogg.erp.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;

import com.ogg.erp.util.AESUtils;
import com.ogg.erp.util.CommonService;

public class ConnDB
{
	private Connection ct = null;

	public Connection getConn()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = CommonService.getJDBCConfValue("jdbc.url") + "?user="
			        + CommonService.getJDBCConfValue("jdbc.username") + "&password="
			        + AESUtils.decrypt(CommonService.getJDBCConfValue("jdbc.password"))
			        + "&useUnicode=true&characterEncoding=utf8";
			ct = DriverManager.getConnection(url);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ct;

	}
}