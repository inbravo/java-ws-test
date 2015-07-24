<!-- Author amit.dixit@impetus.co.in for Java/Servlet training -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Update Page</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>
	<div id="mystyle" class="myform">
		<form id="form" name="form" action="employeeService.ser" method="post">
			<h1 align="center">Update Employee</h1>
			<p>
				Modify the following information to update employee ID:<%=request.getParameter("updateId")%></p>
			<label>
				<input type="hidden" name="empId" id="empId" value="<%=request.getParameter("updateId")%>" />
				<span class="small"></span>
			</label>
			<label>
				Name
				<span class="small">Enter name</span>
			</label>
			<input type="text" name="empName" id="empName" value="<%=request.getParameter("empName")%>" />
			<label>
				Phone
				<span class="small">Enter phone number</span>
			</label>
			<input type="text" name="phone" id="phone" value="<%=request.getParameter("phone")%>" />
			<label>
				Email
				<span class="small">Enter email address</span>
			</label>
			<input type="text" name="email" id="email" value="<%=request.getParameter("email")%>" />
			<label>
				Salary
				<span class="small">Enter salary</span>
			</label>
			<input type="text" name="salary" id="salary" value="<%=request.getParameter("salary")%>" />
			<label>
				Designation
				<span class="small">Enter designation</span>
			</label>
			<input type="text" name="designation" id="designation" value="<%=request.getParameter("designation")%>" />
			<input type="hidden" id="operation" name="operation" value="update" />
			<button type="submit">Update</button>
			<div class="spacer"></div>
		</form>
	</div>
</body>
</html>