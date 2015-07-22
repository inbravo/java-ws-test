<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Employee</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>

	<!-- Action should point to Employee Service  -->
	<div id="mystyle" class="myform">
		<form id="form" name="form" action="employeeService.ser" method="post">
			<h1>Update Backend Service</h1>
			<p>Select the service of your choice</p>
			<label> Service type <span class="small">Select Service type </span>
			</label> <input type="text" name="empId" id="empId" /> <label> Name <span class="small">Enter name</span>
			</label> <input type="text" name="empName" id="empName" /> <label> Phone <span class="small">Enter phone number</span>
			</label> <input type="text" name="phone" id="phone" /> <label> Email <span class="small">Enter email address</span>
			</label> <input type="text" name="email" id="email" /> <label> Salary <span class="small">Enter salary</span>
			</label> <input type="text" name="salary" id="salary" /> <label> Designation <span class="small">Enter designation</span>
			</label> <input type="hidden" id="operation" name="operation" value="serviceSwitch" />
			<button type="submit">Switch</button>
			<div class="spacer"></div>
		</form>
	</div>
</body>
</html>