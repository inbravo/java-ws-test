<!-- Author amit.dixit@impetus.co.in for Java/Servlet training -->
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	final String localContext = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=localContext%>/style/css/style.css" rel="stylesheet" type="text/css" />
<title>JSP Page</title>
</head>
<body>
	<center>
		<div id="mystyle" style="border: none;">
			<h1>Servlet/JSP/JDBC/SOAP/REST tutorial</h1>
			<p>
				<%=new Date()%></br> </br> 
				<a href="<%=localContext%>/jsp/employee/AddEmployee.jsp">Add New Employee</a> &NegativeThickSpace; | <a href="<%=localContext%>/employeeService.ser">View Employee</a>&NegativeThickSpace; | <a href="<%=localContext%>/jsp/service/ServiceSwitch.jsp">Service Switch</a>
			</p>
		</div>
	</center>
	<%@include file="/jsp/common/session.jsp"%>
</body>
</html>