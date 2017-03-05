package com.ogg.erp.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ogg.erp.connectionpool.ConnDB;
import com.ogg.erp.model.Product;

public class ProductRepository
{
	final static private Log DEBUGGER = LogFactory.getLog(ProductRepository.class);

	private Statement sm = null;
	private ResultSet rs = null;
	private Connection ct = null;

	public void close()
	{
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

	private Product convertResultSetToProduct(ResultSet resultSet)
	{
		Product product = null;
		try
		{
			product = new Product();
			product.setProductId(resultSet.getInt("productId"));
			product.setModel(resultSet.getString("model"));
			product.setName(resultSet.getString("name"));
			product.setColor(resultSet.getString("color"));
			product.setCount(resultSet.getInt("count"));
			product.setPrice(resultSet.getFloat("price"));
			product.setCarton(resultSet.getInt("carton"));
			product.setCommonts(resultSet.getString("commonts"));
			product.setCbm(resultSet.getFloat("cbm"));
			product.setWeight(resultSet.getFloat("weight"));
			product.setPicture(resultSet.getString("picture"));
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e.toString());
		}

		return product;
	}

	public Product getProduct(int productId)
	{
		Product product = null;
		if (productId <= 0)
		{
			return product;
		}

		String sql = "select * from `product` where productId = " + productId;

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			if (rs.next())
			{
				product = convertResultSetToProduct(rs);
			}
		}
		catch (Exception e)
		{
			ProductRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return product;
	}

	public boolean addOrderProduct(String orderId, int productId)
	{
		boolean sqlResult = false;
		if (StringUtils.isEmpty(orderId) || productId <= 0)
		{
			return sqlResult;
		}

		String sql = "insert into `sold_products`(`orderId`,`productId`) " + "values ('" + orderId + "'," + productId
		        + ");";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			int result = sm.executeUpdate(sql);
			if (result == 1)
			{
				sqlResult = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ProductRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return sqlResult;
	}

	public boolean addProduct(Product product)
	{
		boolean sqlResult = false;
		if (product == null)
		{
			return sqlResult;
		}

		String sql = "insert  into `product`(`model`,`name`,`color`,`count`,`price`,`carton`,"
		        + "`cbm`,`weight`,`picture`,`commonts`) " + "values ('" + product.getModel() + "','"
		        + product.getName() + "','" + product.getColor() + "'," + product.getCount() + "," + product.getPrice()
		        + "," + product.getCarton() + "," + product.getCbm() + "," + product.getWeight() + ",'"
		        + product.getPicture() + "','" + product.getCommonts() + "');";
		String getIDSql = "SELECT LAST_INSERT_ID() as productId;";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			int result = sm.executeUpdate(sql);
			if (result == 1)
			{
				sqlResult = true;
				rs = sm.executeQuery(getIDSql);
				if (rs.next())
				{
					product.setProductId(rs.getInt("productId"));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ProductRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return sqlResult;
	}

	public boolean editProduct(Product product)
	{
		boolean sqlResult = false;
		if (product == null)
		{
			return sqlResult;
		}

		Product productInSql = getProduct(product.getProductId());
		if (productInSql == null)
		{
			return sqlResult;
		}

		String sql = "update `product` set `model`= '" + product.getModel() + "',`name`= '" + product.getName()
		        + "',`color`= '" + product.getColor() + "',`count`= " + product.getCount() + ",`price`= "
		        + product.getPrice() + "," + "`cbm`= " + product.getCbm() + ",`weight`= " + product.getWeight()
		        + ",`picture`= '" + product.getPicture() + "',`commonts` = '" + product.getCommonts()
		        + "' where `productId` = " + product.getProductId();

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			if (sm.executeUpdate(sql) == 1)
			{
				sqlResult = true;
			}
		}
		catch (Exception e)
		{
			ProductRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return sqlResult;
	}

	public boolean deleteProduct(int productId)
	{
		boolean result = false;
		if (productId <= 0)
		{
			return result;
		}

		Product product = getProduct(productId);
		if (product == null)
		{
			return result;
		}

		String sql = "delete from `product` where productId = " + productId;

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			int sqlResult = sm.executeUpdate(sql);
			if (sqlResult == 1)
			{
				result = true;
			}
		}
		catch (Exception e)
		{
			ProductRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return result;
	}

	public boolean deleteOrderProduct(String orderId, int productId)
	{
		boolean result = false;
		if (StringUtils.isEmpty(orderId) || productId <= 0)
		{
			return result;
		}

		String sql = "delete from `sold_products` where orderId = '" + orderId + "' and " + "productId = " + productId;

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			int sqlResult = sm.executeUpdate(sql);
			if (sqlResult == 1)
			{
				result = true;
			}
		}
		catch (Exception e)
		{
			ProductRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return result;
	}

	public List<Product> listProducts(String orderId)
	{
		Product product = null;
		List<Product> productList = new ArrayList<Product>();

		String sql = "SELECT p.* FROM `sold_products` sp, `product` p WHERE sp.productId = p.productId AND orderId = '"
		        + orderId + "';";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			while (rs.next())
			{
				product = convertResultSetToProduct(rs);
				productList.add(product);
			}
		}
		catch (Exception e)
		{
			ProductRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return productList;
	}

	public List<Product> listProducts()
	{
		Product product = null;
		List<Product> productList = new ArrayList<Product>();

		String sql = "select * from `product`";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			while (rs.next())
			{
				product = convertResultSetToProduct(rs);
				productList.add(product);
			}
		}
		catch (Exception e)
		{
			ProductRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return productList;
	}
}
