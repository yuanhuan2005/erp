<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>  
<%@ page contentType="text/html; charset=utf8"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>奥格家具 - 添加产品图片</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>奥格</span> ERP</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> <%=session.getAttribute("username")%> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#"><span class="glyphicon glyphicon-user"></span> 个人中心</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-cog"></span> 设置</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
	    <br/>
		<ul class="nav menu">
			<li><a href="index.jsp"><span class="glyphicon glyphicon-dashboard"></span> 主页</a></li>
			<li class="active"><a href="orders.jsp"><span class="glyphicon glyphicon-list-alt"></span> 订单管理</a></li>
			<li><a href="purchases.jsp"><span class="glyphicon glyphicon-list-alt"></span> 采购管理</a></li>
		</ul>
	</div><!--/.sidebar-->
 						
	<%@ page language="java" import="com.ogg.erp.service.*,com.ogg.erp.model.*,com.ogg.erp.repository.*"%> 
	<%
		int productId = Integer.valueOf(request.getParameter("productId"));
		String resultStr = request.getParameter("result");
		int result = 0;
		if(StringUtils.isNotEmpty(resultStr))
		{
		    result = Integer.valueOf(resultStr);
		}
		OrderRepository orderRepository = new OrderRepository();
		ProductRepository productRepository = new ProductRepository();
		Product product = productRepository.getProduct(productId);
		Order order = orderRepository.getOrderFromProductId(productId);
		String orderId = order.getOrderId();
	%>
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">添加产品图片</li>
			</ol>
		</div><!--/.row-->
		
        <div class="row">
			<div class="col-lg-12">
				<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<br/>
					<div class="panel-heading">
						<h2 class="panel-title">
							<a>
								客户 <span style="color:red"><%=order.getCustomerName()%></span> 在  <span><%=order.getOrderDate()%></span> 订购的订单： <%=orderId%>
							</a>
						</h2>
					</div>
					<div class="panel-heading">
						<h2 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								填写产品信息（加<span style="color:red"> * </span>表示必填）
							</a>
						</h2>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">
						<form id="image" name="image" method="post" action="servlet/UploadServlet?orderId=<%=orderId%>&productId=<%=productId%>" encType="multipart/form-data" >
						    <font color="blue">上传图片（只能上传jpg/png格式的图片）</font> <br />
						    <input type="file" name="processDef" />
						    <input type="submit"  value="上传"/>
						</form>
						
						
						<form id="product" name="product" class="form-horizontal" action="" method="post">
							<fieldset>
								<div class="form-group">
									<label class="col-md-2 control-label" for="incidentals"></label>
									<div class="col-md-4">
										
									</div>
									<label class="col-md-2 control-label" for="kickback"></label>
									<div class="col-md-4">
										<button id="skip_upload_image" type="button" class="btn btn-primary" onclick="skip_upload_image()">跳过上传图片</button>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					</div>
				</div>
				</div><!-- /.panel-group-->
			</div><!-- /.col-->
		</div><!-- /.row -->
		
	</div><!--/.main-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script>
		$("#skip_upload_image").click(function(){
			window.location.href='order_products.jsp?orderId='+<%=orderId%>;
		});
		
		!function ($) {
			var result = <%=result%>;
			//public static final int INVALID_FORMAT = 1;
			//public static final int FAILED_TO_UPLOAD = 2;
			//public static final int FAILED_TO_SAVE_DB = 3;
			if(result == 1)
			{
				alert("图片格式不合法！");
				window.location.href='add_order_product_image.jsp?productId='+<%=productId%>;
			}
			else if(result == 2)
			{
				alert("图片上传失败！");
				window.location.href='add_order_product_image.jsp?productId='+<%=productId%>;
			}
			else if(result == 3)
			{
				alert("图片上传成功，但是没有成功添加给产品！");
				window.location.href='add_order_product_image.jsp?productId='+<%=productId%>;
			}
			
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");	  
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show');
		});
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide');
		});
	</script>	
</body>

</html>
