<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>  
<%@ page contentType="text/html; charset=utf8"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>奥格家具 - 添加产品</title>

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
		String orderId = request.getParameter("orderId");
		OrderService orderService = new OrderService();
		OrderRepository orderRepository = new OrderRepository();
		Order order = orderRepository.getOrder(orderId);
		float totalPrice  = orderService.getOrderTotalPrice(orderId);
	%>
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">添加产品</li>
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
						<form id="product" name="product" class="form-horizontal" action="" method="post">
							<fieldset>
								<div class="form-group">
									<label class="col-md-2 control-label" for="model"><span style="color:red">* </span>型号</label>
									<div class="col-md-4">
										<input id="model" name="model" type="text" placeholder="" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="customerTel"><span style="color:red">* </span>名称</label>
									<div class="col-md-4">
										<input id="name" name="name" type="text" placeholder="" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="color">颜色</label>
									<div class="col-md-4">
										<input id="color" name="color" type="text" placeholder="" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="count"><span style="color:red">* </span>数量</label>
									<div class="col-md-4">
										<input id="count" name="count" type="text" placeholder="0" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="price"><span style="color:red">* </span>单价</label>
									<div class="col-md-4">
										<input id="price" name="price" type="text" placeholder="0.0" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="carton">件数</label>
									<div class="col-md-4">
										<input id="carton" name="carton" type="text" placeholder="0" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="cbm">立方数</label>
									<div class="col-md-4">
										<input id="cbm" name="cbm" type="text" placeholder="0.0" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="weight">重量（千克）</label>
									<div class="col-md-4">
										<input id="weight" name="weight" type="text" placeholder="0.0" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="commonts">备 注</label>
									<div class="col-md-10">
										<input id="commonts" name="commonts" type="text" placeholder="" class="form-control"></input>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="incidentals"></label>
									<div class="col-md-4">
										
									</div>
									<label class="col-md-2 control-label" for="kickback"></label>
									<div class="col-md-4">
										<button id="save_product" type="button" class="btn btn-primary" onclick="save_product()">提交</button>
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
		$("#save_product").click(function(){
			var theForm = document.product;
			var orderId = <%=orderId%>;
			var model = theForm.model.value;
			var name = theForm.name.value;
			var color = theForm.color.value;
			var count = theForm.count.value;
			var price = theForm.price.value;
			var carton = theForm.carton.value;
			var cbm = theForm.cbm.value;
			var weight = theForm.weight.value;
			var commonts = theForm.commonts.value;
			
			if(model == null || model == "")
			{
				alert("型号不能为空！");
				return;
			}
			
			if(name == null || name == "")
			{
				alert("名称不能为空！");
				return;
			}
			
			if(count == null || count == "")
			{
				alert("数量不能为空！");
				return;
			}
			
			if(price == null || price == "")
			{
				alert("单价不能为空！");
				return;
			}
			
			if(isNaN(count))
			{
				alert("数量必须是数字！");
				document.getElementById("count").focus();
				return;
			}
			
			if(isNaN(price))
			{
				alert("单价必须是数字！");
				document.getElementById("price").focus();
				return;
			}
			
			if(isNaN(cbm))
			{
				alert("立方数必须是数字！");
				document.getElementById("cbm").focus();
				return;
			}
			
			if(isNaN(weight))
			{
				alert("重量必须是数字！");
				document.getElementById("weight").focus();
				return;
			}
			 
			var textdata = {"orderId":orderId,"model":model,"name":name,"color":color,"count":count,"price":price
					,"carton":carton,"commonts":commonts,"cbm":cbm,"weight":weight};
			var data = JSON.stringify(textdata);
			$.ajax({
				url:"api/addOrderProduct",
				data:data,
			    dataType:"json",
				type:"post",
				scriptCharset: 'utf-8',
				success:function(returnMsg){
					var productId = returnMsg.productId;
					window.location.href='add_order_product_image.jsp?productId='+productId;
				},
                error: function(XMLHttpRequest, textStatus, errorThrown) { 
                }, 
                complete: function(XMLHttpRequest, textStatus) { 
                } 
			});
			  
		});
		
		
		!function ($) {			
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
