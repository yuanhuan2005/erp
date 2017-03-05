<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>  
<%@ page contentType="text/html; charset=utf8"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>奥格家具 - 订单中的产品</title>

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
				<li class="active">订单中的产品</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<br/>
					<div class="panel-heading">
						<h2 class="panel-title">
							<a>
								客户 <span style="color:red"><%=order.getCustomerName()%></span> 在  <span><%=order.getOrderDate()%></span> 订购的订单： <%=orderId%>
							</a>
						</h2>
					</div>
					<div class="panel-body">
						<div id="toolbar" class="btn-group">
 							<button id="btn_view" type="button" class="btn btn-default" onclick="javascript:window.location.href='add_order_product.jsp?orderId=<%=orderId%>'">
 								<span class="glyphicon glyphicon-plus"></span> 添加产品</button>
 						</div>
 						
 						<div class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									`
								</div>
							</div>
						</div>
 						
						<table data-toggle="table" data-url="api/listOrderProducts?orderId=<%=request.getParameter("orderId")%>"  data-show-refresh="false" data-show-toggle="false" 
						    data-show-columns="false" data-search="false" data-select-item-name="orderId" data-pagination="true" 
						    data-sort-name="model" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="index" data-formatter="indexFormatter" >序号</th>
						        <th data-field="model" data-sortable="true">型号</th>
						        <th data-field="name" data-sortable="true">产品名称</th>
						        <th data-field="picture" data-sortable="true" data-formatter="thumbnailFormatter">图片</th>
						        <th data-field="color" data-sortable="true">颜色</th>
						        <th data-field="count" data-sortable="true">数量</th>
						        <th data-field="price" data-sortable="true">单价</th>
						        <th data-field="carton" data-sortable="true">件数</th>
						        <th data-field="cbm" data-sortable="true">立方数</th>
						        <th data-field="weight" data-sortable="true">重量</th>
						        <th data-field="commonts" data-sortable="true">备注</th>
							    <th data-field="operation" data-formatter="actionFormatter" data-events="actionEvents">操作</th>
						    </tr>
						    </thead>
						</table>
					</div>
				</div>
			</div>
		</div><!--/.row-->	
		<div class="row">
		</div><!--/.row-->	
		
		
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
		    return '<a href="' + row.picture + '" class="thumbnail"><img src="' + row.picture + '" alt="php tutorial" width="40" height="40" /></a>';
		}
	 
		//表格操作
		function actionFormatter(value, row, index) {
		    return '<a class="mod" >修改</a> ' + '<a class="delete">删除</a>';
		}
		    
		//表格  - 操作 - 事件
		window.actionEvents = {
			'click .mod': function(e, value, row, index) {      
		     	//修改操作
				window.location.href='edit_order_product.jsp?orderId=' + <%=orderId%> + '&productId=' + row.productId;
		     },
		     'click .delete' : function(e, value, row, index) {
		     	 //删除操作 
		    	 if(window.confirm('你确定要删除这个产品吗？')){
	                 data_param = {"result":false};
	                 $.ajax({ 
		                 url:'api/deleteOrderProduct?orderId=' + <%=orderId%> + '&productId=' + row.productId,
		                 type: "GET", 
		                 data:JSON.stringify(data_param), 
		                 headers:{ 
		                 	"Content-Type":"application/json" 
		                 }, 
		                 contentType: 'text/html; charset=UTF-8', 
		                 dataType: "json", 
		                 success: function(data) {
		    		    	 window.location.href='order_products.jsp?orderId=' + <%=orderId%>;
		                 },
		                 error: function(XMLHttpRequest, textStatus, errorThrown) { 
			                 alert("删除失败"); 
		                 }, 
		                 complete: function(XMLHttpRequest, textStatus) { 
		                 } 
	                 });
	             }
		     }
		};
		
		//序号
		function indexFormatter(value, row, index) {  
	        return index+1;  
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
