<!-- Author amit.dixit@impetus.co.in -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Case Study Update Page</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>
	<div id="mystyle" class="myform">
		<form id="form" name="form" action="csdmService.ser" method="post">
			<h1 align="center">Update Case Study</h1>
			<p>Modify the following information to update Case Study ID:<%=request.getParameter("updateId")%></p>
			
			<label><input type="hidden" name="csId" id="csId" value="<%=request.getParameter("updateId")%>" /><span class="small"></span></label>
			
			<!-- Get case study information from system -->
			<label>Client Info<span class="small">Enter client info</span></label><input type="text" name="clientInfo" id="clientInfo" value="<%=request.getParameter("clientInfo")%>" />
			<label>Project Requirements<span class="small">Enter requirements</span></label><input type="text" name="requirements" id="requirements" value="<%=request.getParameter("requirements")%>" />
			<label>Solution Details<span class="small">Enter solution details</span></label><input type="text" name="solution" id="solution" value="<%=request.getParameter("solution")%>" /> 
			<label>Business Value Delivered<span class="small">Enter business value delivered</span></label><input type="text" name="businessValue" id="businessValue" value="<%=request.getParameter("businessValue")%>" />
			<label>Project Engineering Challenges<span class="small">Enter project engineering challenges</span></label> <input type="text" name="challenges" id="challenges" value="<%=request.getParameter("challenges")%>" />
			<label>How Project Engineering Challenges Handled<span class="small">Enter how project engineering challenges handled</span></label> <input type="text" name="howChallengesHandled" id="howChallengesHandled" value="<%=request.getParameter("howChallengesHandled")%>" />
			<label>Project NFR Considerations<span class="small">Enter project NFR considerations</span></label> <input type="text" name="nfr" id="nfr" value="<%=request.getParameter("nfr")%>" />
			<label>Project Technology Stack<span class="small">Enter project NFR considerations</span></label> <input type="text" name="stack" id="stack" value="<%=request.getParameter("stack")%>" />
			<label>Project Duration<span class="small">Enter project duration in days</span></label> <input type="text" name="duration" id="duration" value="<%=request.getParameter("duration")%>" />
			<label>Project Team Details<span class="small">Enter project team details</span></label> <input type="text" name="team" id="team" value="<%=request.getParameter("team")%>" />
			<label>Project Results<span class="small">Enter project results</span></label> <input type="text" name="results" id="results" value="<%=request.getParameter("results")%>" />
			
			<input type="hidden" id="operation" name="operation" value="update" />
			<button type="submit">Update</button>
			<div class="spacer"></div>
		</form>
	</div>
</body>
</html>