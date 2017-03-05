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
import com.ogg.erp.model.Order;
import com.ogg.erp.model.Product;

public class OrderRepository
{
	final static private Log DEBUGGER = LogFactory.getLog(OrderRepository.class);

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

	private Order convertResultSetToOrder(ResultSet resultSet)
	{
		Order order = null;
		try
		{
			order = new Order();
			order.setOrderId(resultSet.getString("orderId"));
			order.setCustomerName(resultSet.getString("customerName"));
			order.setCustomerTel(resultSet.getString("customerTel"));
			order.setCustomerPhone(resultSet.getString("customerPhone"));
			order.setOrderDate(resultSet.getString("orderDate"));
			order.setPlanDeliveryDate(resultSet.getString("planDeliveryDate"));
			order.setActualDeliveryDate(resultSet.getString("actualDeliveryDate"));
			order.setDeposit(resultSet.getFloat("deposit"));
			order.setCarriage(resultSet.getFloat("carriage"));
			order.setIncidentals(resultSet.getFloat("incidentals"));
			order.setKickback(resultSet.getFloat("kickback"));
			order.setShipAddress(resultSet.getString("shipAddress"));
			order.setCommonts(resultSet.getString("commonts"));
			order.setStatus(resultSet.getString("status"));
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e.toString());
		}

		return order;
	}

	public List<Order> listOrders()
	{
		Order order = null;
		List<Order> orderList = new ArrayList<Order>();

		String sql = "select * from `order`";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			while (rs.next())
			{
				order = convertResultSetToOrder(rs);
				orderList.add(order);
			}
		}
		catch (Exception e)
		{
			OrderRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return orderList;
	}

	public List<Order> listOrders(String yearMonth)
	{
		Order order = null;
		List<Order> orderList = new ArrayList<Order>();

		String sql = "select * from `order` where orderDate like '" + yearMonth + "%'";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			while (rs.next())
			{
				order = convertResultSetToOrder(rs);
				orderList.add(order);
			}
		}
		catch (Exception e)
		{
			OrderRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return orderList;
	}

	public Order getOrder(String orderId)
	{
		Order order = null;

		String sql = "select * from `order` where orderId = '" + orderId + "'";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			if (rs.next())
			{
				order = convertResultSetToOrder(rs);
			}
		}
		catch (Exception e)
		{
			OrderRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return order;
	}

	public Order getOrderFromProductId(int productId)
	{
		Order order = null;

		String sql = "select * from `order` o, `sold_products` sp where o.orderId = sp.orderId and productId = "
		        + productId;

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			if (rs.next())
			{
				order = convertResultSetToOrder(rs);
			}
		}
		catch (Exception e)
		{
			OrderRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return order;
	}

	public boolean addOrder(Order order)
	{
		boolean sqlResult = false;
		if (order == null)
		{
			return sqlResult;
		}

		String sql = "insert  into `order`(`orderId`,`customerName`,`customerTel`,`customerPhone`,`orderDate`,`planDeliveryDate`,"
		        + "`actualDeliveryDate`,`deposit`,`carriage`,`incidentals`,`shipAddress`,`status`,`kickback`,`commonts`) "
		        + "values ('"
		        + order.getOrderId()
		        + "','"
		        + order.getCustomerName()
		        + "','"
		        + order.getCustomerTel()
		        + "','"
		        + order.getCustomerPhone()
		        + "','"
		        + order.getOrderDate()
		        + "','"
		        + order.getPlanDeliveryDate()
		        + "','"
		        + order.getActualDeliveryDate()
		        + "',"
		        + order.getDeposit()
		        + ","
		        + order.getCarriage()
		        + ","
		        + order.getIncidentals()
		        + ",'"
		        + order.getShipAddress()
		        + "','"
		        + order.getStatus()
		        + "',"
		        + order.getKickback() + ",'" + order.getCommonts() + "');";

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
			OrderRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return sqlResult;
	}

	public boolean editOrder(Order order)
	{
		boolean sqlResult = false;
		if (order == null)
		{
			return sqlResult;
		}

		Order orderInSql = getOrder(order.getOrderId());
		if (orderInSql == null)
		{
			return sqlResult;
		}

		String sql = "update `order` set `orderId` = '" + order.getOrderId() + "',`customerName`= '"
		        + order.getCustomerName() + "',`customerTel`= '" + order.getCustomerTel() + "',`customerPhone`= '"
		        + order.getCustomerPhone() + "',`orderDate`= '" + order.getOrderDate() + "',`planDeliveryDate`= '"
		        + order.getPlanDeliveryDate() + "'," + "`actualDeliveryDate`= '" + order.getActualDeliveryDate()
		        + "',`deposit`= " + order.getDeposit() + ",`carriage`= " + order.getCarriage() + ",`incidentals`= "
		        + order.getIncidentals() + ",`shipAddress`= '" + order.getShipAddress() + "',`status`= '"
		        + order.getStatus() + "',`kickback`= " + order.getKickback() + ",`commonts` = '" + order.getCommonts()
		        + "' where `orderId` = '" + order.getOrderId() + "'";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			int sqlRest = sm.executeUpdate(sql);
			if (sqlRest > 0)
			{
				sqlResult = true;
			}
		}
		catch (Exception e)
		{
			OrderRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return sqlResult;
	}

	public boolean deleteOrder(String orderId)
	{
		boolean result = false;
		if (StringUtils.isEmpty(orderId))
		{
			return result;
		}

		Order order = getOrder(orderId);
		if (order == null)
		{
			return result;
		}

		String delete_order_sql = "delete from `order` where orderId = '" + orderId + "'";
		String delete_order_product_sql = "delete from `sold_products` where orderId = '" + orderId + "'";

		try
		{
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			int deleteOrderResult = sm.executeUpdate(delete_order_sql);
			int deleteOrderProductResult = sm.executeUpdate(delete_order_product_sql);
			if (deleteOrderResult > 0 && deleteOrderProductResult >= 0)
			{
				result = true;
			}
		}
		catch (Exception e)
		{
			OrderRepository.DEBUGGER.error("Exception: " + e.toString());
		}
		finally
		{
			close();
		}

		return result;
	}

	public float getOrderTotalPrice(String orderId)
	{
		float total = (float) 0.0;
		List<Product> productList = new ProductRepository().listProducts(orderId);
		for (Product product : productList)
		{
			total += product.getCount() * product.getPrice();
		}
		return total;
	}
}
