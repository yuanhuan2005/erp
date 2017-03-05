package com.ogg.erp.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ogg.erp.model.ImageUploadResult;
import com.ogg.erp.model.Product;
import com.ogg.erp.repository.ProductRepository;
import com.ogg.erp.util.CommonService;
import com.ogg.erp.util.FileUtils;

public class ImageUploadController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private static final int IMAGE_NAME_LEN = 64;

	final static private Log DEBUGGER = LogFactory.getLog(ImageUploadController.class);

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		int result = ImageUploadResult.SUCCESS;
		String errorMsg = "success";
		String uploadDir = "images";
		String fileUrlPath = uploadDir + "/";
		request.setCharacterEncoding("UTF-8");

		String orderId = request.getParameter("orderId");
		int productId = Integer.valueOf(request.getParameter("productId"));

		try
		{
			//创建磁盘文件项工厂  
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			//设置缓冲区的大小，如果文件的大小超过了缓冲区的大小，就会产生临时文件  
			diskFileItemFactory.setSizeThreshold(1024 * 1024 * 3);
			//获得核心解析类  
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			//解决中文文件名上传乱码  
			fileUpload.setHeaderEncoding("UTF-8");
			//解析request,返回list集合  
			List<FileItem> list = fileUpload.parseRequest(request);
			//获得每个部分，将遍历的值存入到一个map集合中  
			Map<String, String> map = new HashMap<>();
			//设置上传文件的文件名  
			String fileName = null;
			for (FileItem fileItem : list)
			{
				//判断普通项和文件上传项  
				if (fileItem.isFormField())
				{
					//普通项  
					String name = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");
					map.put(name, value);
				}
				else
				{
					//文件上传项  
					//获得文件名  
					fileName = fileItem.getName();
					String format = FileUtils.getFileFormat(fileName).toLowerCase();
					if ((!format.equals("jpg")) && (!format.equals("png")))
					{
						result = ImageUploadResult.INVALID_FORMAT;
						errorMsg = "图片格式不合法！";
						break;
					}
					fileName = CommonService.randomString(ImageUploadController.IMAGE_NAME_LEN) + "." + format;

					//获得文件的输入流  
					InputStream is = fileItem.getInputStream();
					//获得文件上传的路径  
					String path = getServletContext().getRealPath("/" + uploadDir + "/");
					OutputStream os = new FileOutputStream(path + "/" + fileName);
					int len = 0;
					byte[] b = new byte[1024];

					while ((len = is.read(b)) != -1)
					{
						os.write(b, 0, len);
					}
					is.close();
					os.close();

					fileUrlPath += fileName;
					ProductRepository productRepository = new ProductRepository();
					Product product = productRepository.getProduct(productId);
					product.setPicture(fileUrlPath);
					boolean editProductResult = productRepository.editProduct(product);
					if (!editProductResult)
					{
						result = ImageUploadResult.FAILED_TO_SAVE_DB;
						errorMsg = "图片上传成功，但是没有成功添加给产品！";
					}
				}
			}
		}
		catch (FileUploadException e)
		{
			ImageUploadController.DEBUGGER.error("文件上传失败！");
			result = ImageUploadResult.FAILED_TO_UPLOAD;
			errorMsg = "图片上传失败！";
		}

		if (result == ImageUploadResult.SUCCESS)
		{
			response.sendRedirect(request.getContextPath() + "/order_products.jsp?orderId=" + orderId);
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/add_order_product_image.jsp?productId=" + productId
			        + "&result=" + result);
		}

		PrintWriter out;
		try
		{
			out = response.getWriter();
			String resp = "{\"result\":" + result + ",\"errorMsg\":\"" + errorMsg + "\"}";
			out.print(resp);
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
