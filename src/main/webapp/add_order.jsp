<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>  
<%@ page contentType="text/html; charset=utf8"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>奥格家具 - 创建订单</title>

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
				<li class="active">创建订单</li>
			</ol>
		</div><!--/.row-->
		
        <div class="row">
			<div class="col-lg-12">
				<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<br/>
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								第一步：填写订单信息（加<span style="color:red"> * </span>表示必填）
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">
					
						<form name="order" class="form-horizontal" action="" method="post">
							<fieldset>
								<div class="form-group">
									<label class="col-md-2 control-label" for="orderId"><span style="color:red">* </span>订单编号</label>
									<div class="col-md-4">
										<input id="orderId" name="orderId" type="text" placeholder="" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="status">订单状态</label>
									<div class="col-md-4">
										<select id="status" class="form-control">
											<option>完成</option>
											<option>异常</option>
											<option>进行中</option>
									</select>
									</div>
									
									
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="customerName"><span style="color:red">* </span>客户名称</label>
									<div class="col-md-4">
										<input id="customerName" name="customerName" type="text" placeholder="" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="customerTel">客户座机</label>
									<div class="col-md-4">
										<input id="customerTel" name="customerTel" type="text" placeholder="" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="customerPhone">客户手机</label>
									<div class="col-md-4">
										<input id="customerPhone" name="customerPhone" type="text" placeholder="" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="orderDate"><span style="color:red">* </span>订货日期</label>
									<div class="col-md-4">
										<input id="orderDate" name="orderDate" type="date" placeholder="" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="planDeliveryDate">预计交货日期</label>
									<div class="col-md-4">
										<input id="planDeliveryDate" name="planDeliveryDate" type="date" placeholder="" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="actualDeliveryDate"><span style="color:red">* </span>实际交货日期</label>
									<div class="col-md-4">
										<input id="actualDeliveryDate" name="actualDeliveryDate" type="date" placeholder="" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="deposit">预付定金</label>
									<div class="col-md-4">
										<input id="deposit" name="deposit" type="text" placeholder="0.0" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="carriage">运 费</label>
									<div class="col-md-4">
										<input id="carriage" name="carriage" type="text" placeholder="0.0" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="incidentals">杂 费</label>
									<div class="col-md-4">
										<input id="incidentals" name="incidentals" type="text" placeholder="0.0" class="form-control">
									</div>
									<label class="col-md-2 control-label" for="kickback">返 点</label>
									<div class="col-md-4">
										<input id="kickback" name="kickback" type="text" placeholder="0.0" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label" for="shipAddress"><span style="color:red">* </span>送货地址</label>
									<div class="col-md-10">
										<input id="shipAddress" name="shipAddress" type="text" placeholder="" class="form-control">
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
										<button id="save_order" type="button" class="btn btn-primary" onclick="save_order()">提交并进入下一步</button>
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
		$("#save_order").click(function(){
			var theForm = document.order;
			var orderId = theForm.orderId.value;
			var customerName = theForm.customerName.value;
			var customerTel = theForm.customerTel.value;
			var customerPhone = theForm.customerPhone.value;
			var orderDate = theForm.orderDate.value;
			var planDeliveryDate = theForm.planDeliveryDate.value;
			var actualDeliveryDate = theForm.actualDeliveryDate.value;
			var deposit = theForm.deposit.value;
			var carriage = theForm.carriage.value;
			var incidentals = theForm.incidentals.value;
			var shipAddress = theForm.shipAddress.value;
			var kickback = theForm.kickback.value;
			var commonts = theForm.commonts.value;
			var status = theForm.status.value;
			
			if(orderId == null || orderId == "")
			{
				alert("订单编号不能为空！");
				return;
			}
			
			if(customerName == null || customerName == "")
			{
				alert("客户名称不能为空！");
				return;
			}
			
			if(orderDate == null || orderDate == "")
			{
				alert("订货日期不能为空！");
				return;
			}
			
			if(actualDeliveryDate == null || actualDeliveryDate == "")
			{
				alert("实际交货日期不能为空！");
				return;
			}
			
			if(shipAddress == null || shipAddress == "")
			{
				alert("送货地址不能为空！");
				return;
			}
			
			if(isNaN(deposit))
			{
				alert("预付定金必须是数字！");
				document.getElementById("deposit").focus();
				return;
			}
			
			if(isNaN(carriage))
			{
				alert("运费必须是数字！");
				document.getElementById("carriage").focus();
				return;
			}
			
			if(isNaN(incidentals))
			{
				alert("杂费必须是数字！");
				document.getElementById("incidentals").focus();
				return;
			}
			
			if(isNaN(kickback))
			{
				alert("返点必须是数字！");
				document.getElementById("kickback").focus();
				return;
			}
			 
			var textdata = {"orderId":orderId,"customerName":customerName,"customerTel":customerTel,"customerName":customerName,"customerTel":customerTel
					,"customerPhone":customerPhone,"orderDate":orderDate,"planDeliveryDate":planDeliveryDate,"actualDeliveryDate":actualDeliveryDate,"deposit":deposit
					,"carriage":carriage,"carriage":carriage,"incidentals":incidentals,"shipAddress":shipAddress,"kickback":kickback,"commonts":commonts,"status":status};
			var data = JSON.stringify(textdata);
			$.ajax({
				url:"api/addOrder",
				data:data,
			    dataType:"json",
				type:"post",
				scriptCharset: 'utf-8',
				success:function(data){
					window.location.href='order_products.jsp?orderId='+orderId;
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
