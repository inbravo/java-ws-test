<!-- Author amit.dixit@impetus.co.in for Java/Servlet training -->
<%@page import="com.inbravo.ss.dto.CSDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Employee(s)</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>
	<div align="center">
		<table>
			<thead>
				<tr>
					<th>Emp ID</th>
					<th>Name</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Salary</th>
					<th>Designation</th>
				</tr>
			</thead>
			<tbody>
				<%
					/* Get all employees */
							final List<CSDTO> employees = (List<CSDTO>) request.getAttribute("employees");

							/* Iterate over all employees */
							for (final CSDTO dto : employees) {
				%>
				<tr>
					<td><%=String.valueOf(dto.getEmpId())%></td>
					<td><%=dto.getEmpName()%></td>
					<td><%=dto.getPhone()%></td>
					<td><a href="mailto:<%=dto.getEmail()%>"><%=dto.getEmail()%></a></td>
					<td><%=String.valueOf(dto.getSalary())%></td>
					<td><%=dto.getDesignation()%></td>
					<td style="border: none;">
						<div>
							<form method="post" action="<%=localContext%>/jsp/employee/UpdateEmployee.jsp">
								<input type="hidden" id="updateId" name="updateId" value="<%=String.valueOf(dto.getEmpId())%>" />
								<input type="hidden" id="empName" name="empName" value="<%=String.valueOf(dto.getEmpName())%>" />
								<input type="hidden" id="phone" name="phone" value="<%=String.valueOf(dto.getPhone())%>" />
								<input type="hidden" id="email" name="email" value="<%=String.valueOf(dto.getEmail())%>" />
								<input type="hidden" id="salary" name="salary" value="<%=String.valueOf(dto.getSalary())%>" />
								<input type="hidden" id="designation" name="designation" value="<%=String.valueOf(dto.getDesignation())%>" />
								<input type="submit" value="Modify..." />
							</form>
						</div>
					</td>
					<td style="border: none;">
						<div>
							<form method="post" action="employeeService.ser">
								<input type="hidden" id="empId" name="empId" value="<%=String.valueOf(dto.getEmpId())%>" />
								<input type="hidden" id="operation" name="operation" value="delete" />
								<input type="submit" value="Delete..." />
							</form>
						</div>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>