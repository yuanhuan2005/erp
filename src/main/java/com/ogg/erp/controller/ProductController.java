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

import com.ogg.erp.model.Product;
import com.ogg.erp.repository.ProductRepository;

@Controller
@RequestMapping("/")
public class ProductController
{
	//final static private Log DEBUGGER = LogFactory.getLog(OrderController.class);

	@RequestMapping(value = "/getProduct", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void getProduct(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		int productId = Integer.valueOf(request.getParameter("productId"));
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print(JSONObject.fromObject(new ProductRepository().getProduct(productId).toString())); //将json数据写入流中
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addOrderProduct", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	void addOrderProduct(HttpServletRequest request, HttpServletResponse response)
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

			String productJson = stringBuffer.toString();
			ProductRepository productRepository = new ProductRepository();
			JSONObject jsonObject = JSONObject.fromObject(productJson);
			String orderId = jsonObject.getString("orderId");
			Product product = (Product) JSONObject.toBean(jsonObject, Product.class);
			sqlResult = productRepository.addProduct(product);
			if (sqlResult)
			{
				sqlResult = productRepository.addOrderProduct(orderId, product.getProductId());
			}
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
			String resp = "{\"result\":" + sqlResult + ",\"productId\":\"" + product.getProductId() + "\"}";
			out.print(resp); //将json数据写入流中
			out.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/editProduct", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	void editProduct(HttpServletRequest request, HttpServletResponse response)
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

			String productJson = stringBuffer.toString();
			ProductRepository productRepository = new ProductRepository();
			JSONObject jsonObject = JSONObject.fromObject(productJson);
			Product product = (Product) JSONObject.toBean(jsonObject, Product.class);

			// 特殊处理图片，传过来0表示不改变图片路径
			if (product.getPicture().equals("0"))
			{
				Product productInSql = productRepository.getProduct(product.getProductId());
				product.setPicture(productInSql.getPicture());
			}
			sqlResult = productRepository.editProduct(product);
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

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void deleteProduct(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		int productId = Integer.valueOf(request.getParameter("productId"));
		PrintWriter out;
		try
		{
			out = response.getWriter();
			boolean sqlResult = new ProductRepository().deleteProduct(productId);
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

	@RequestMapping(value = "/deleteOrderProduct", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void deleteOrderProduct(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		String orderId = request.getParameter("orderId");
		int productId = Integer.valueOf(request.getParameter("productId"));
		PrintWriter out;
		try
		{
			out = response.getWriter();
			boolean sqlResult = new ProductRepository().deleteOrderProduct(orderId, productId);
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

	@RequestMapping(value = "/listProducts", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	void listProducts(HttpServletRequest request, HttpServletResponse response)
	{
		String contentType = "text/json;charset=UTF-8";
		response.setContentType(contentType);
		response.setCharacterEncoding("utf8");

		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print(JSONArray.fromObject(new ProductRepository().listProducts()).toString()); //将json数据写入流中
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
