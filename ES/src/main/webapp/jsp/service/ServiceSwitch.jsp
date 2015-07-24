<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Service Switch</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>

	<!-- Action should point to Employee Service  -->
	<div id="mystyle" class="myform">
		<form id="form" name="form" action="employeeService.ser" method="post">
			<h1 align="center">Select Service Type</h1>
			<table align="center">
				<tr>
					<td><input type="radio" name="serviceType" value="EmployeeInfoAtMongoDB" checked /></td><td>JDBC</td>
				</tr>
				<tr>
					<td><input type="radio" name="serviceType" value="EmployeeInfoAtESWS" /></td><td>SOAP-WS</td>
				</tr>
				<tr>
					<td><input type="radio" name="serviceType" value="EmployeeInfoAtERWS" /></td><td>REST-WS</td>
				</tr>
			</table>
			<button type="submit">Switch</button>
			<div class="spacer"></div>
			<input type="hidden" id="operation" name="operation" value="serviceSwitch" />
		</form>
	</div>
</body>
</html>
