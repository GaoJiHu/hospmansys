<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tabStoreAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/validate.js"></script>
	<script type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(function(){
			//药品种类
			$.ajax({
					type:"post",
					url:"servlet/GetTabKindsServlet",
					success:function(xml){
						$(xml).find("tab").each(function(){
							//alert($(this).children("t_k_id").text());
							//alert($(this).children("t_k_name").text())
							$("#tabKind").append("<option value="+$(this).children("t_k_id").text()+">"+$(this).children("t_k_name").text()+"</option>");
						});
					},
					dataType:"xml"
			});
			//厂家
			$.ajax({
					type:"post",
					url:"servlet/GetFactorysServlet",
					success:function(xml){
						$(xml).find("factory").each(function(){
							$("#factory").append("<option value="+$(this).children("f_id").text()+">"+$(this).children("f_name").text()+"</option>");
						});
					},
					dataType:"xml"
			});
			//focus事件
			$("#tabName").focus(function(){
				$("#span1").html("");
			});
			$("#proDate").focus(function(){
				$("#span1").html("");
			});
			$("#overDate").focus(function(){
				$("#span1").html("");
			});
			$("#count").focus(function(){
				$("#span1").html("");
			});
			$("#inprice").focus(function(){
				$("#span1").html("");
			});
			$("#outprice").focus(function(){
				$("#span1").html("");
			});
		});
		
		//判断是否能提交
		function validate(){
			if($("#tabName").attr("value")==null||$("#tabName").attr("value")==""){
				$("#span1").html("<font color='red' size='3px'>请填写药品名称！</font>");
				return false;
			}
			
			if($("#proDate").attr("value")==null||$("#proDate").attr("value")==""){
				$("#span1").html("<font color='red' size='3px'>请填写生产日期！</font>");
				return false;
			}
			if($("#overDate").attr("value")==null||$("#overDate").attr("value")==""){
				$("#span1").html("<font color='red' size='3px'>请填写过期日期！</font>");
				return false;
			}
			if($("#proDate").attr("value")>= $("#overDate").attr("value")){
				$("#span1").html("<font color='red' size='3px'>请正确填写过期日期！</font>");
				return false;
			}
			if($("#count").attr("value")==null||$("#count").attr("value")==""){
				$("#span1").html("<font color='red' size='3px'>请填写药品数量！</font>");
				return false;
			}
			if(!$("#count").attr("value").validateSignlessIntegral()){
				$("#span1").html("<font color='red' size='3px'>请正确填写药品数量！</font>");
				return false;
			}
			if($("#inprice").attr("value")==null||$("#inprice").attr("value")==""){
				$("#span1").html("<font color='red' size='3px'>请填写进价！</font>");
				return false;
			}
			if(!$("#inprice").attr("value").validateSignlessDecimal()){
				$("#span1").html("<font color='red' size='3px'>请正确填写进价！</font>");
				return false;
			}
			if($("#outprice").attr("value")==null||$("#outprice").attr("value")==""){
				$("#span1").html("<font color='red' size='3px'>请填写售价！</font>");
				return false;
			}
			if(!$("#outprice").attr("value").validateSignlessDecimal()){
				$("#span1").html("<font color='red' size='3px'>请正确填写售价！</font>");
				return false;
			}
			if($("#outprice").attr("value")<$("#inprice").attr("value")){
				$("#span1").html("<font color='red' size='3px'>请正确填写售价！</font>");
				return false;
			}
			return true;
		}
	</script>
	<style type="text/css">
		.td1 { text-align:center; border-color:#0000FF;}
		tr { height: 40px;}
	</style>
  </head>
  
  <body>
    	<center>
    		<form action="servlet/TabStoreAddServlet" method="post" onsubmit="return validate();">
            	<table width="60%" >
                	<caption><font size="5" color="#0000FF"><b>进药基本资料填写</b></font></caption>
                   <tr>
                   		<td width="350px" class="td1">药品种类：</td>
                        <td><select id="tabKind" name="tabKind"></select></td>
                   </tr>
                   <tr>
                   		<td class="td1">药品名称：</td>
                        <td><input type="text" id="tabName" name="tabName" /></td>
                   </tr>
                    <tr>
                   		<td class="td1">生产日期：</td>
                        <td><input id="proDate" name="proDate" class="Wdate" type="text" onclick="WdatePicker()"></td>
                   </tr>
                   <tr>
                   		<td class="td1">过期日期：</td>
                        <td><input id="overDate" name="overDate" class="Wdate" type="text" onclick="WdatePicker()"></td>
                   </tr>
                    <tr>
                   		<td class="td1">数&nbsp;&nbsp;&nbsp;&nbsp;量：</td>
                        <td><input type="text" id="count" name="count" /></td>
                   </tr>
                   <tr>
                   		<td class="td1">单&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
                        <td><select id="unit" name="unit"><option value="盒">&nbsp;&nbsp;&nbsp;&nbsp;盒&nbsp;&nbsp;&nbsp;&nbsp;</option><option value="克">&nbsp;&nbsp;&nbsp;&nbsp;克</option><option value="斤">&nbsp;&nbsp;&nbsp;&nbsp;斤</option></select></td>
                   </tr>
                    <tr>
                   		<td class="td1">进货单价：</td>
                        <td><input type="text" id="inprice" name="inprice" />元</td>
                   </tr>
                   <tr>
                   		<td class="td1">销售单价：</td>
                        <td><input type="text" id="outprice" name="outprice" />元</td>
                   </tr>
                    <tr>
                   		<td class="td1">厂&nbsp;&nbsp;&nbsp;&nbsp;家：</td>
                        <td><select id="factory" name="factory"></select></td>
                   </tr>
                   <tr>
                        <td class="td1"><span id="span1"></span></td>
                        <td><input type="submit" value="提交" /></td>
                   </tr>
                </table>
            </form>
    	</center>
    </body>
</html>
