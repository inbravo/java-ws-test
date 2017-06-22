<!-- Author amit.dixit@impetus.co.in -->
<%@page import="com.inbravo.csdm.dto.CSDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Case Study(s)</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>
	<div align="center">
		<table>
			<thead>
				<tr>
					<th>Case Study ID</th> 
					<th>Client InfoInfo</th>
					<th>Project Requirements</th>
					<th>Solution Details</th> 
					<th>Business Value Delivered</th>
					<th>Project Engineering Challenges</th>
					<th>How Project Engineering Challenges Handled</th>
					<th>Project NFR Considerations</th>
					<th>Project Technology Stack</th>
					<th>Project Duration</th>
					<th>Project Team Details</th>
					<th>Project Results</th>
				</tr>
			</thead>
			<tbody>
				<%
							/* Get all case studies */
							final List<CSDTO> caseStudies = (List<CSDTO>) request.getAttribute("csl");

							/* Iterate over all case studies */
							for (final CSDTO dto : caseStudies) {
				%>
				<tr>
					<td><%=String.valueOf(dto.getCsId())%></td>
					<td><%=dto.getClientInfo()%></td>
					<td><%=dto.getRequirements()%></td>
					<td><%=dto.getSolution()%></td>
					<td><%=dto.getBusinessValue()%></td>
					<td><%=dto.getChallenges()%></td>
					<td><%=dto.getHowChallengesHandled()%></td>
					<td><%=dto.getNfr()%></td>
					<td><%=dto.getStack()%></td>
					<td><%=dto.getDuration()%></td>
					<td><%=dto.getTeam()%></td>
					<td><%=dto.getResults()%></td>

					<td style="border: none;">
						<div>
							<form method="post" action="<%=localContext%>/jsp/cs/UpdateCS.jsp">
								<input type="hidden" id="updateId" name="updateId" value="<%=String.valueOf(dto.getCsId())%>" />
								<input type="hidden" id="clientInfo" name="clientInfo" value="<%=String.valueOf(dto.getClientInfo())%>" />
								<input type="hidden" id="requirements" name="requirements" value="<%=String.valueOf(dto.getRequirements())%>" />
								<input type="hidden" id="solution" name="solution" value="<%=String.valueOf(dto.getSolution())%>" />
								<input type="hidden" id="businessValue" name="businessValue" value="<%=String.valueOf(dto.getBusinessValue())%>" />
								<input type="hidden" id="challenges" name="challenges" value="<%=String.valueOf(dto.getChallenges())%>" />
								<input type="hidden" id="howChallengesHandled" name="howChallengesHandled" value="<%=String.valueOf(dto.getHowChallengesHandled())%>" />
								<input type="hidden" id="nfr" name="nfr" value="<%=String.valueOf(dto.getNfr())%>" />
								<input type="hidden" id="stack" name="stack" value="<%=String.valueOf(dto.getStack())%>" />
								<input type="hidden" id="duration" name="duration" value="<%=String.valueOf(dto.getDuration())%>" />
								<input type="hidden" id="team" name="team" value="<%=String.valueOf(dto.getTeam())%>" />
								<input type="hidden" id="results" name="results" value="<%=String.valueOf(dto.getResults())%>" />
								<input type="submit" value="Modify..." />
							</form>
						</div>
					</td>
					<td style="border: none;">
						<div>
							<form method="post" action="employeeService.ser">
								<input type="hidden" id="empId" name="empId" value="<%=String.valueOf(dto.getCsId())%>" />
								<input type="hidden" id="operation" name="operation" value="delete" />
								<input type="submit" value="Delete..." />
							</form>
						</div>
					</td>
					<td style="border: none;">
						<div>
							<form method="post" action="employeeService.ser">
								<input type="submit" value="Export..." />
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