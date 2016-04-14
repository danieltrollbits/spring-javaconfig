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
				<legend>Persons</legend>
				<div style="padding:1%">
					<fieldset style="padding:1%">
						<legend>Search Filter</legend>
						<form action="${pageContext.request.contextPath}/search" method="get">
							<div class="row">
							    <div class="column column-6">
							        <div class="row">
							            <div class="column column-4">First Name</div>
							            <div class="column column-8">
							            	<input type="text" name="firstName" value="${param.firstName}">
							            </div>
							        </div>
							        <div class="row">
							            <div class="column column-4">Middle Name</div>
							            <div class="column column-8">
							            	<input type="text" name="middleName" value="${param.middleName}">
							            </div>
							        </div>
							    </div>
							    <div class="column column-6">
							        <div class="row">
							            <div class="column column-4">Last Name</div>
							            <div class="column column-8">
							            	<input type="text" name="lastName" value="${param.lastName}">
							            </div>
							        </div>
							        <div class="row">
							            <div class="column column-4">Role</div>
							            <div class="column column-8">
							            	<select name="roles">
												<option value="">Select role...</option>
												<c:forEach var="role" items="${roles}">
													<option ${role.role == param.roles ? 'selected="selected"' : ''} 
													value="${role.role}">${role.role}</option>
												</c:forEach>
											</select>
							            </div>
							        </div>
							    </div>
							</div>
							<div style="padding:1%" align="right">
								<button type="submit" name="search" value="search">search</button>
							</div>
						</form>
					</fieldset>
				</div>
				<div style="padding:1%">
					<fieldset style="padding:1%">
						<legend>Person Table</legend>
						<form id="personForm">
							<div align="right" style="padding:1%">
								<input type="button" value="Add" name="add" id="btnAdd">
								<input type="button" value="Update" name="update" id="btnUpdate">
								<input type="button" value="Delete" name="delete" id="btnDelete">
							</div>
							<table class="table" border="1">
								<thead>
								<tr>
									<th></th>
									<th>Id</th>
									<th>First Name</th>
									<th>Middle Name</th>
									<th>Last Name</th>
									<th>Gender</th>
									<th>Birthdate</th>
									<th>Employed</th>
									<th>Gwa</th>
									<th>Role</th>
								</tr>
								</thead>
								<tbody>
									<c:if test="${!persons.isEmpty()}">
									<c:forEach var="person" items="${persons}">
										<tr>
											<td><input type="checkbox" name="personId" value="${person.id}"></td>
											<td>${person.id}</td>
											<td>${person.firstName}</td>
											<td>${person.middleName}</td>
											<td>${person.lastName}</td>
											<td>${person.gender}</td>
											<td><fmt:formatDate dateStyle="long" value="${person.birthdate}"/></td>
											<td>${person.employed}</td>
											<td>${person.gwa}</td>
											<td>${person.rolesToString()}</td>
										</tr>
									</c:forEach>
									</c:if>
									<c:if test="${persons.isEmpty()}">
										<td colspan="10" align="center">NO DETAILS</td>
									</c:if>
								</tbody>
							</table>
						</form>
					</fieldset>	
				</div>
			</fieldset>
		</div>

	<script src="<c:url value="/resources/js/jquery-2.2.3.min.js"/>"></script>
	<script>
		$("#btnAdd").on("click",function(){
		   $("#personForm").attr("action", "${pageContext.request.contextPath}/add")
		   .attr("method","get").submit();
		});
		$("#btnUpdate").on("click",function(){
		   $("#personForm").attr("action", "${pageContext.request.contextPath}/update")
		   .attr("method","get").submit();
		});
		$("#btnDelete").on("click",function(){
		   $("#personForm").attr("action", "${pageContext.request.contextPath}/delete")
		   .attr("method","post").submit();
		});
	</script>
	</body>
</html>