<!-- Author amit.dixit@impetus.co.in for Java/Servlet training -->
<%@page import="com.inbravo.ss.dto.EmployeeDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Error(s)</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>
	<div align="center">
		<table>
			<thead>
				<tr>
					<th>Server Error. Contact Customer Care</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><%=request.getAttribute("server_error")%></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>