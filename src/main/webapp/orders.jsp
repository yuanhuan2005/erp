<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>  
<%@ page contentType="text/html; charset=utf8"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>奥格家具 - 订单管理</title>

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
				<li class="active">订单管理</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<div id="toolbar" class="btn-group">
 							<button id="btn_view" type="button" class="btn btn-default" onclick="javascript:window.location.href='add_order.jsp'">
 								<span class="glyphicon glyphicon-plus"></span> 新增订单</button>
 						</div>
 						
						<table id="orders_list" data-toggle="table" data-url="api/listOrders"  data-show-refresh="true" data-show-toggle="false" 
						    data-show-columns="false" data-search="true" data-select-item-name="orderId" data-pagination="true" 
						    data-sort-name="name" data-sort-order="desc">
						    <thead>
						    <tr>
						        <!-- <th data-field="orderId" data-checkbox="true" data-formatter="LinkFormatter">订单编号</th> -->
						        <th data-field="index" data-formatter="indexFormatter" >序号</th>
						        <th data-field="orderId" data-sortable="true" data-formatter="LinkFormatter">订单编号</th>
						        <th data-field="customerName" data-sortable="true">客户名称</th>
						        <th data-field="customerTel" data-sortable="true">客户座机</th>
						        <th data-field="customerPhone" data-sortable="true">客户手机</th>
						        <th data-field="orderDate" data-sortable="true">订货日期</th>
						        <th data-field="actualDeliveryDate" data-sortable="true">交货日期</th>
						        <th data-field="status" data-sortable="true">状态</th>
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
		//订单编号超链接
		function LinkFormatter(value, row, index) {
			return "<a target='_blank' href='view_order.jsp?orderId=" + value + "'>"+value+"</a>";
		}
		
		
		//表格操作
		function actionFormatter(value, row, index) {
		    return '<a class="mod" >修改</a> ' + '<a class="delete">删除</a>';
		}
		    
		//表格  - 操作 - 事件
		window.actionEvents = {
			'click .mod': function(e, value, row, index) {      
		     	//修改操作
				window.location.href='edit_order.jsp?orderId=' + row.orderId;
		     },
		     'click .delete' : function(e, value, row, index) {
		     	 //删除操作 
		    	 if(window.confirm('你确定要删除这个订单吗？')){
	                 data_param = {"result":false};
	                 $.ajax({ 
		                 url:'api/deleteOrder?orderId=' + row.orderId, 
		                 type: "GET", 
		                 data:JSON.stringify(data_param), 
		                 headers:{ 
		                 	"Content-Type":"application/json" 
		                 }, 
		                 contentType: 'text/html; charset=UTF-8', 
		                 dataType: "json", 
		                 success: function(data) {
		    		    	 window.location.href='orders.jsp';
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
