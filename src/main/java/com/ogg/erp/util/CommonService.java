package com.ogg.erp.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Random;

/**
 * ����������
 * @author TCL
 *
 */
public class CommonService
{
	/**
	 * ��ȡidm������
	 * 
	 * @param confKey
	 *            ���������
	 * @return confValue ������ֵ
	 */
	public static String getIdmConfValue(String confKey)
	{
		return CommonService.getBaseConfValue("idm.properties", confKey);
	}

	/**
	 * ��ȡJDBC������
	 * 
	 * @param confKey
	 *            ���������
	 * @return confValue ������ֵ
	 */
	public static String getJDBCConfValue(String confKey)
	{
		return CommonService.getBaseConfValue("jdbc.properties", confKey);
	}

	/**
	 * �����������ƻ�ȡ��Ӧ��ֵ
	 * 
	 * @param propertitesFile
	 *            property file
	 * @param confKey
	 *            ���������
	 * @return confValue ������ֵ
	 */
	public static String getBaseConfValue(String propertitesFile, String confKey)
	{
		String confValue = "";

		InputStream inputStream = new CommonService().getClass().getClassLoader().getResourceAsStream(propertitesFile);
		Properties p = new Properties();

		try
		{
			p.load(inputStream);
			confValue = p.getProperty(confKey);
		}
		catch (Exception e)
		{
			confValue = "";
		}
		finally
		{
			try
			{
				if (null != inputStream)
				{
					inputStream.close();
				}
			}
			catch (IOException e)
			{
				confValue = "";
			}
		}

		return confValue;
	}

	/**
	 * ��ͨJAVA��ȡ WEB��Ŀ�µ�WEB-INFĿ¼·��
	 * 
	 * @return WEB-INFĿ¼·��
	 */
	public static String getWebInfPath()
	{
		URL url = new CommonService().getClass().getProtectionDomain().getCodeSource().getLocation();
		String path = url.toString();
		int index = path.indexOf("WEB-INF");

		if (index == -1)
		{
			index = path.indexOf("classes");
		}

		if (index == -1)
		{
			index = path.indexOf("bin");
		}

		path = path.substring(0, index);

		if (path.startsWith("zip"))
		{
			// ��class�ļ���war��ʱ����ʱ����zip:D:/...�����·��
			path = path.substring(4);
		}
		else if (path.startsWith("file"))
		{
			// ��class�ļ���class�ļ���ʱ����ʱ����file:/D:/...�����·��
			path = path.substring(6);
		}
		else if (path.startsWith("jar"))
		{
			// ��class�ļ���jar�ļ�����ʱ����ʱ����jar:file:/D:/...�����·��
			path = path.substring(10);
		}

		path = CommonService.getURLDecodeString(path);

		return path;
	}

	/**
	 * ����������
	 * 
	 * @param seconds
	 *            ����
	 */
	public static void sleep(long seconds)
	{
		try
		{
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e)
		{
			// ignore
		}
	}

	/**
	 * ���ַ����URL����
	 * 
	 * @param str
	 * @return
	 */
	public static String getURLDecodeString(String str)
	{
		try
		{
			return URLDecoder.decode(str, "UTF-8");
		}
		catch (Exception e)
		{
		}

		return str;
	}

	/**
	 * ��url����
	 * 
	 * @param url
	 * @return
	 */
	public static String getURLEncodeString(String url)
	{
		try
		{
			return URLEncoder.encode(url, "UTF-8");
		}
		catch (Exception e)
		{
		}

		return url;
	}

	/**
	 * ��ɰ����ֺʹ�Сд��ĸ������ַ�
	 * 
	 * @param length �ַ���
	 * @return �����ɵ��ַ�
	 */
	public static String randomString(int length)
	{
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int strLen = str.length();
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			int num = random.nextInt(strLen);
			buf.append(str.charAt(num));
		}
		return buf.toString();
	}

	public static String convertMonthFromNumber(int month)
	{
		if (month < 1)
		{
			return "01";
		}
		else if (month >= 1 && month <= 9)
		{
			return "0" + month;
		}
		else if (month >= 10 && month <= 12)
		{
			return "" + month;
		}
		else
		{
			return "12";
		}
	}

	/**
	 * 
	 * 
	 * @param yearMonth  yyyy-MM
	 * @return
	 */
	public static String getLastYearMonth(String yearMonth)
	{
		String lastYearMonth = "";
		int year = Integer.valueOf(yearMonth.split("-")[0]);
		int month = Integer.valueOf(yearMonth.split("-")[1]);
		if (month == 1)
		{
			lastYearMonth = (year - 1) + "-12";
		}
		else
		{
			lastYearMonth = year + "-" + CommonService.convertMonthFromNumber(month - 1);
		}
		return lastYearMonth;
	}
}
