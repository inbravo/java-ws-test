<!-- Author amit.dixit@impetus.co.in -->
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
			<h1>Case Study Data Manager</h1>
			<p>
				<%=new Date()%></br> </br> 
				<a href="<%=localContext%>/jsp/cs/AddCS.jsp">Add New Case Study</a> &NegativeThickSpace; | <a href="<%=localContext%>/csdmService.ser">View Case Studies</a>
			</p>
		</div>
	</center>
	<%@include file="/jsp/common/session.jsp"%>
</body>
</html>