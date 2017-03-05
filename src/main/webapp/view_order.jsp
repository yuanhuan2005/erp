<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>  
<%@ page contentType="text/html; charset=utf8"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>奥格家具 - 订单详情</title>

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
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">订单详情</li>
			</ol>
		</div><!--/.row-->
		
		<%@ page language="java" import="com.ogg.erp.service.*,com.ogg.erp.model.*,com.ogg.erp.repository.*"%> 
		<%
			String orderId = request.getParameter("orderId");
			OrderService orderService = new OrderService();
			OrderRepository orderRepository = new OrderRepository();
			Order order = orderRepository.getOrder(orderId);
			float totalPrice  = orderService.getOrderTotalPrice(orderId);
		%>
		
		
        <div class="row">
			<div class="col-lg-12">
				<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<br/>
					<div class="panel-heading">
						<h2 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								客户的订购信息
							</a>
						</h2>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">
					
						<form class="form-horizontal" action="" method="get">
							<fieldset>
								<div class="form-group">
									<label class="col-md-2 control-label" for="orderId">订单编号</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getOrderId()%></span>
									</div>
								
									<label class="col-md-2 control-label" for="status">订单状态</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getStatus()%></span>
									</div>
									
									
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="customerName">客户名称</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getCustomerName()%></span>
									</div>
									<label class="col-md-2 control-label" for="customerTel">客户座机</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getCustomerTel()%></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="customerPhone">客户手机</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getCustomerPhone()%></span>
									</div>
									<label class="col-md-2 control-label" for="orderDate">订货日期</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getOrderDate()%></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="planDeliveryDate">预计交货日期</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getPlanDeliveryDate()%></span>
									</div>
									<label class="col-md-2 control-label" for="actualDeliveryDate">实际交货日期</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getActualDeliveryDate()%></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="deposit">预付定金</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getDeposit()%></span>
									</div>
									<label class="col-md-2 control-label" for="carriage">运 费</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getCarriage()%></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="incidentals">杂 费</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getIncidentals()%></span>
									</div>
									<label class="col-md-2 control-label" for="kickback">返 点</label>
									<div class="col-md-4">
										<span class="form-control"><%=order.getKickback()%></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="shipAddress">送货地址</label>
									<div class="col-md-10">
										<span class="form-control"><%=order.getShipAddress()%></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="commonts">备 注</label>
									<div class="col-md-10">
										<span class="form-control"><%=order.getCommonts()%></span>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					</div>
				
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
								产品信息
							</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse in">
					<div class="panel-body">
 						<div class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<strong>总金额： <span style="color:red"><%=totalPrice%></span>元</strong>
								</div>
							</div>
						</div>
 						
						<table data-toggle="table" data-url="api/listOrderProducts?orderId=<%=request.getParameter("orderId")%>"  data-show-refresh="false" data-show-toggle="false" 
						    data-show-columns="false" data-search="false" data-select-item-name="orderId" data-pagination="true" 
						    data-sort-name="model" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="model" data-sortable="true" >型号</th>
						        <th data-field="name" data-sortable="true">产品名称</th>
						        <th data-field="picture" data-sortable="true" data-formatter="thumbnailFormatter">图片</th>
						        <th data-field="color" data-sortable="true">颜色</th>
						        <th data-field="count" data-sortable="true">数量</th>
						        <th data-field="price" data-sortable="true">单价</th>
						        <th data-field="carton" data-sortable="true">件数</th>
						        <th data-field="cbm" data-sortable="true">立方数</th>
						        <th data-field="weight" data-sortable="true">重量</th>
						        <th data-field="commonts" data-sortable="true">备注</th>
						    </tr>
						    </thead>
						</table>
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
	
		//表格图片缩略图
		function thumbnailFormatter(value, row, index) {
		    return '<a href="images/01.jpg" class="thumbnail"><img src="images/01.jpg" alt="php tutorial" width="40" height="40" /></a>';
		}
		
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
