<!-- Author amit.dixit@impetus.co.in -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Case Study</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>
	<!-- Action should point to Case Study Data Manager  -->
	<div id="mystyle" class="myform">
		<form id="form" name="form" action="csdmService.ser" method="post">
			<h1>Case Study</h1>
			<p>To add new Case Study enter following information</p>
			
			<!-- Get case study information from user -->
			<label>Case Study ID<span class="small">Case study id</span></label><input type="text" name="csId" id="csId" />
			<label>Client Info<span class="small">Client info</span></label><input type="text" name="clientInfo" id="clientInfo" />
			<label>Requirements<span class="small">Project requirements</span></label><input type="text" name="requirements" id="requirements" />
			<label>Solution<span class="small">Solution details</span></label><input type="text" name="solution" id="solution" /> 
			<label>Business Value<span class="small">Business value delivered</span></label><input type="text" name="businessValue" id="businessValue" />
			<label>Challenges<span class="small">Project implementation challenges</span></label><input type="text" name="challenges" id="challenges" />
			<label>Challenges Handled<span class="small">How challenges handled</span></label><input type="text" name="howChallengesHandled" id="howChallengesHandled" />
			<label>NFR Considerations<span class="small">Project NFR considerations</span></label><input type="text" name="nfr" id="nfr" />
			<label>Technology Stack<span class="small">Project technology stack</span></label><input type="text" name="stack" id="stack" />
			<label>Duration<span class="small">Project duration in days</span></label><input type="text" name="duration" id="duration" />
			<label>Team Details<span class="small">Project team details</span></label><input type="text" name="team" id="team" />
			<label>Results<span class="small">Project results</span></label><input type="text" name="results" id="results" />
			
			<input type="hidden" id="operation" name="operation" value="create" />
			<button type="submit">Add</button>
			<div class="spacer"></div>
		</form>
	</div>
</body>
</html>