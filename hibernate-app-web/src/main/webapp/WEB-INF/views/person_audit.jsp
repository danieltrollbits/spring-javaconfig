<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<title>index</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/grid.css"/>">
	</head>
	<body style="padding:2% 10% 10% 10%">
		<div>
			<div class="row">
				<div class="column column-6">
					<span style="color:blue">${param.message}</span>
				</div>
			</div>
			<fieldset>
				<legend>Audit</legend>
				<div style="padding:1%">
					<fieldset style="padding:1%">
						<legend>Person Audit Table</legend>
							<table class="table" border="1">
								<thead>
								<tr>
									<th>Id</th>
									<th>Person Id</th>
									<th>Status</th>
									<th>Date Audited</th>
								</tr>
								</thead>
								<tbody>
									<c:if test="${personAudits.isEmpty()}">
										<td colspan="4" align="center">NO DETAILS</td>
									</c:if>
									<c:forEach var="personAudit" items="${personAudits}">
										<tr>
											<td>${personAudit.id}</td>
											<td>${personAudit.personId}</td>
											<td>${personAudit.status}</td>
											<td>${personAudit.currentDate}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</fieldset>	
				</div>
			</fieldset>
		</div>
	</body>
</html>