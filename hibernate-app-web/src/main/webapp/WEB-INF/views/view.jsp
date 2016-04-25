<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Person</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/grid.css"/>">
</head>
<body style="padding:2% 10% 10% 10%">
	<div>
		<fieldset>
		<legend>View Person Details</legend>	
		<div style="padding:1%">
			<fieldset style="padding:1%">
				<legend>General Information</legend>
				<div class="row">
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">First Name</div>
				            <div class="column column-8">${personDto.firstName}</div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Middle Name</div>
				            <div class="column column-8">${personDto.middleName}</div>
				        </div>
				        <div class="row">
				            <div class="column column-4">BirthDate</div>
				            <div class="column column-8">
				            	<fmt:formatDate pattern="MM-dd-yyy" value="${personDto.birthdate}" var="formatDate"/>
				            	${formatDate}
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Gwa</div>
				            <div class="column column-8">${personDto.gwa}</div>
				        </div>
				    </div>
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">Last Name</div>
				            <div class="column column-8">${personDto.lastName}</div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Gender</div>
				            <div class="column column-8">${personDto.gender}</div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Employed?</div>
				            <div class="column column-8">${personDto.employed ? 'yes' : 'no'}</div>
				        </div>
				    </div>
				</div>
				<div class="row">
				    <div class="column column-6">
				    	<fieldset>
							<legend>Role<span class="required">*</span> <form:errors path="roleDtos" cssClass="required"/></legend>
							<c:if test="${empty personDto.roleDtos}">
								<div class="row">No details</div>
							</c:if>
							<c:forEach var="role" items="${personDto.roleDtos}">
								${role}<br>
							</c:forEach>
						</fieldset>
				    </div>
				</div>        
				
			</fieldset>
		</div>
		<div style="padding:1%">
			<fieldset style="padding:1%">
				<legend>Address</legend>
				<div class="row">
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">Street</div>
				            <div class="column column-8">${personDto.addressDto.street}</div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Barangay</div>
				            <div class="column column-8">${personDto.addressDto.barangay}</div>
				        </div>
				        <div class="row">
				            <div class="column column-4">City</div>
				            <div class="column column-8">${personDto.addressDto.city}</div>
				        </div>
				    </div>
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">House No.</div>
				            <div class="column column-8">${personDto.addressDto.houseNo}</div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Subdivision</div>
				            <div class="column column-8">${personDto.addressDto.subdivision}</div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Zipcode</div>
				            <div class="column column-8">${personDto.addressDto.zipCode}</div>
				        </div>
				    </div>
				</div>
			</fieldset>
		</div>
		
		<div style="padding:1%">
			<fieldset style="padding:1%">
				<legend>Contact</legend>
				<c:if test="${empty personDto.contactDtos}">
					<div class="row">No details</div>
				</c:if>
				<c:forEach var="contact" items="${personDto.contactDtos}">
					<div class="row">
						<div class="column column-4">${contact.type}</div>
				        <div class="column column-8">${contact.value}</div>
					</div>
				</c:forEach>
			</fieldset>
		</div>
		</fieldset>	
	</div>
</body>
</html>