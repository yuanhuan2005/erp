package com.ogg.erp.service;

import java.util.ArrayList;
import java.util.List;

import com.ogg.erp.model.MonthOrderNumber;
import com.ogg.erp.model.Order;
import com.ogg.erp.repository.OrderRepository;
import com.ogg.erp.util.CommonService;
import com.ogg.erp.util.DateFormatterUtils;

public class OrderService
{
	//final static private Log DEBUGGER = LogFactory.getLog(OrderService.class);

	public float getOrderTotalPrice(String orderId)
	{
		OrderRepository orderRepository = new OrderRepository();
		return orderRepository.getOrderTotalPrice(orderId);
	}

	public long getCurrMonthOrderCount()
	{
		long count = 0;
		String currDate = DateFormatterUtils.getCurrentDate();
		OrderRepository orderRepository = new OrderRepository();
		List<Order> ordersList = orderRepository.listOrders();
		for (Order order : ordersList)
		{
			if (order.getOrderDate().equals(currDate))
			{
				count++;
			}
		}
		return count;
	}

	public long getTotalOrderCount()
	{
		OrderRepository orderRepository = new OrderRepository();
		return orderRepository.listOrders().size();
	}

	public List<MonthOrderNumber> getLastYearOrderNumbers()
	{
		OrderRepository orderRepository = new OrderRepository();
		List<MonthOrderNumber> monthOrderNumberList = new ArrayList<MonthOrderNumber>();
		MonthOrderNumber monthOrderNumber = null;
		String currYearMonth = DateFormatterUtils.getCurrentYearMonth();
		String yearMonth = currYearMonth;
		for (int i = 0; i < 12; i++)
		{
			monthOrderNumber = new MonthOrderNumber();
			monthOrderNumber.setMonth(yearMonth);
			monthOrderNumber.setNumber(orderRepository.listOrders(yearMonth).size());
			monthOrderNumberList.add(monthOrderNumber);
			yearMonth = CommonService.getLastYearMonth(yearMonth);
		}

		return monthOrderNumberList;
	}
}
