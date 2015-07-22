<!-- Author amit.dixit@impetus.co.in for Java/Servlet training -->
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>
		<%
			final String validationError = (String) request
					.getAttribute("error");

			/* Check if error availables */
			if (validationError != null) {
				out.println("<font color='RED'> " + validationError + "</font>");
			}
		%>
	</h1>
	<div align="right" id="mystyle" style="border: none;">
		<h1>Employees created/updated/deleted in this user session</h1>
		<table>
			<thead>
				<tr>
					<th>Created</th>
					<th>Updated</th>
					<th>Deleted</th>
				</tr>
			</thead>
			<tbody>
				<%
					/* Get all created employees count*/
					final String employeesCreated = (String) request.getSession()
							.getAttribute("emp_created");

					/* Get all updated employees count*/
					final String employeesUpdated = (String) request.getSession()
							.getAttribute("emp_updated");

					/* Get all deleted employees count*/
					final String employeesDeleted = (String) request.getSession()
							.getAttribute("emp_deleted");
				%>
				<tr>
					<td>
						<%
							if (employeesCreated != null) {
								out.println(Integer.valueOf(employeesCreated));
							} else {
								out.println("0");
							}
						%>
					</td>
					<td>
						<%
							if (employeesUpdated != null) {
								out.println(Integer.valueOf(employeesUpdated));
							} else {
								out.println("0");
							}
						%>
					</td>
					<td>
						<%
							if (employeesDeleted != null) {
								out.println(Integer.valueOf(employeesDeleted));
							} else {
								out.println("0");
							}
						%>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<br>
	<br>
</body>
</html>