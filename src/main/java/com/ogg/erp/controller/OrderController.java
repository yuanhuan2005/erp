package com.ogg.erp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ogg.erp.model.Order;
import com.ogg.erp.repository.OrderRepository;
import com.ogg.erp.repository.ProductRepository;

@Controller
@RequestMapping("/")
public class OrderController
{
	//final static private Log DEBUGGER = LogFactory.getLog(OrderController.class);

	@RequestMapping(value = "/getOrder", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void getOrder(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		String orderId = request.getParameter("orderId");
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print(JSONObject.fromObject(new OrderRepository().getOrder(orderId)).toString()); //将json数据写入流中
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	void addOrder(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		boolean sqlResult = false;
		try
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			{
				stringBuffer.append(line);
			}

			String orderJson = stringBuffer.toString();
			OrderRepository orderRepository = new OrderRepository();
			JSONObject jsonObject = JSONObject.fromObject(orderJson);
			Order order = (Order) JSONObject.toBean(jsonObject, Order.class);
			sqlResult = orderRepository.addOrder(order);
			if (sqlResult)
			{
				response.setStatus(HttpStatus.SC_OK);
			}
			else
			{
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			}
			PrintWriter out;
			out = response.getWriter();
			out.print("{\"result\":" + sqlResult + "}"); //将json数据写入流中
			out.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/editOrder", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	void editOrder(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		boolean sqlResult = false;
		try
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			{
				stringBuffer.append(line);
			}

			String orderJson = stringBuffer.toString();
			OrderRepository orderRepository = new OrderRepository();
			JSONObject jsonObject = JSONObject.fromObject(orderJson);
			Order order = (Order) JSONObject.toBean(jsonObject, Order.class);
			sqlResult = orderRepository.editOrder(order);
			if (sqlResult)
			{
				response.setStatus(HttpStatus.SC_OK);
			}
			else
			{
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			}
			PrintWriter out;
			out = response.getWriter();
			out.print("{\"result\":" + sqlResult + "}"); //将json数据写入流中
			out.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/deleteOrder", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void deleteOrder(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		String orderId = request.getParameter("orderId");
		PrintWriter out;
		try
		{
			out = response.getWriter();
			boolean sqlResult = new OrderRepository().deleteOrder(orderId);
			if (sqlResult)
			{
				response.setStatus(HttpStatus.SC_OK);
			}
			else
			{
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			}
			out.print("{\"result\":" + sqlResult + "}"); //将json数据写入流中
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/listOrders", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void listOrders(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print(JSONArray.fromObject(new OrderRepository().listOrders()).toString()); //将json数据写入流中
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/listOrderProducts", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void listOrderProducts(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		String orderId = request.getParameter("orderId");
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print(JSONArray.fromObject(new ProductRepository().listProducts(orderId)).toString()); //将json数据写入流中
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
