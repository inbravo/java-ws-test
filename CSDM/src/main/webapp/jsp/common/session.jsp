<!-- Author amit.dixit@impetus.co.in -->
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
		<h1>Case studies created/updated/deleted in this user session</h1>
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
					final String csCreated = (String) request.getSession()
							.getAttribute("cs_created");

					/* Get all updated employees count*/
					final String csUpdated = (String) request.getSession()
							.getAttribute("cs_updated");

					/* Get all deleted employees count*/
					final String csDeleted = (String) request.getSession()
							.getAttribute("cs_deleted");
				%>
				<tr>
					<td>
						<%
							if (csCreated != null) {
								out.println(Integer.valueOf(csCreated));
							} else {
								out.println("0");
							}
						%>
					</td>
					<td>
						<%
							if (csUpdated != null) {
								out.println(Integer.valueOf(csUpdated));
							} else {
								out.println("0");
							}
						%>
					</td>
					<td>
						<%
							if (csDeleted != null) {
								out.println(Integer.valueOf(csDeleted));
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