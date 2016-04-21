<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>Person</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/grid.css"/>">
</head>
<body style="padding:2% 10% 10% 10%">
	<form:form action="${pageContext.request.contextPath}/save" commandName="personDto" modelAttribute="personDto" method="post">
		<div class="row">
			<div class="column column-6"><span style="color:red">${error}</span></div>
		</div>
		<div class="row">
			<c:forEach var="error" items="${errors}">
				<div class="column column-6"><span style="color:red">${error}</span></div>
			</c:forEach>
		</div>
	<div>
		<fieldset>
		<legend>
			<c:choose>
				<c:when test="${personDto.id == 0}">Add Person</c:when>
				<c:otherwise>Update Person</c:otherwise>
			</c:choose>
		</legend>	
		<div style="padding:1%">
			<fieldset style="padding:1%">
				<legend>General Information</legend>
				<form:hidden path="id" value="${person.id}"/>
				<div class="row">
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">First Name<span class="required">*</span></div>
				            <div class="column column-8"><form:input path="firstName" value="${personDto.firstName}"/>
				            	<br><form:errors path="firstName" cssClass="required"/>
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Middle Name<span class="required">*</span></div>
				            <div class="column column-8">
				            	<form:input path="middleName" value="${personDto.middleName}"/>
			            		<br><form:errors path="middleName" cssClass="required"/>
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">BirthDate<span class="required">*</span></div>
				            <div class="column column-8">
				            	<fmt:formatDate pattern="MM-dd-yyy" value="${personDto.birthdate}" var="formatDate"/>
				            	<form:input path="birthdate" value="${formatDate}" placeholder="12-30-1900"/>
				            	<br><form:errors path="birthdate" cssClass="required"/>
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Gwa<span class="required">*</span></div>
				            <div class="column column-8">
				            	<form:input path="gwa" value="${personDto.gwa != '0.0' ? personDto.gwa : ''}"/>
				            	<br><form:errors path="gwa" cssClass="required"/>
				            </div>
				        </div>
				    </div>
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">Last Name<span class="required">*</span></div>
				            <div class="column column-8">
				            	<form:input path="lastName" value="${personDto.lastName}"/>
				            	<br><form:errors path="lastName" cssClass="required"/>
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Gender<span class="required">*</span></div>
				            <div class="column column-8">
								<form:radiobutton path="gender" value="MALE"/>Male 
								<form:radiobutton path="gender" value="FEMALE"/>Female
								<br><form:errors path="gender" cssClass="required"/> 
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Employed?<span class="required">*</span></div>
				            <div class="column column-8">
				            	<form:radiobutton path="employed" value="true"/>Yes
								<form:radiobutton path="employed" value="false"/>No
								<br><form:errors path="employed" cssClass="required"/>
				            </div>
				        </div>
				    </div>
				</div>
				<div class="row">
				    <div class="column column-6">
				    	<fieldset>
							<legend>Role<span class="required">*</span> <form:errors path="roleDtos" cssClass="required"/></legend>
							<form:checkboxes items="${roles}" path="roleDtos" delimiter="<br/>"/>
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
				            <div class="column column-4">Street<span class="required">*</span></div>
				            <div class="column column-8">
				            	<form:input path="addressDto.street" value="${personDto.addressDto.street}"/>
				            	<br><form:errors path="addressDto.street" cssClass="required"/>
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Barangay<span class="required">*</span></div>
				            <div class="column column-8">
				            	<form:input path="addressDto.barangay" value="${personDto.addressDto.barangay}"/>
				            	<br><form:errors path="addressDto.barangay" cssClass="required"/>
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">City<span class="required">*</span></div>
				            <div class="column column-8">
			            		<form:input path="addressDto.city" value="${personDto.addressDto.city}"/>
			            		<br><form:errors path="addressDto.city" cssClass="required"/>
				            </div>
				        </div>
				    </div>
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">House No.<span class="required">*</span></div>
				            <div class="column column-8">
				            	<form:input path="addressDto.houseNo" value="${personDto.addressDto.houseNo}"/>
				            	<br><form:errors path="addressDto.houseNo" cssClass="required"/>
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Subdivision</div>
				            <div class="column column-8">
				            	<form:input path="addressDto.subdivision" value="${personDto.addressDto.subdivision}"/>
				            	<br><form:errors path="addressDto.subdivision" cssClass="required"/>
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Zipcode<span class="required">*</span></div>
				            <div class="column column-8">
				            	<form:input path="addressDto.zipCode" value="${personDto.addressDto.zipCode}"/>
				            	<br><form:errors path="addressDto.zipCode" cssClass="required"/>
				            </div>
				        </div>
				    </div>
				</div>
			</fieldset>
		</div>
		
		<div style="padding:1%">
			<fieldset style="padding:1%">
				<legend>Contact</legend>
				<c:set var="contactCount" value="${0}"/>
				<c:forEach var="contact" items="${personDto.contactDtos}" varStatus="count">
					<div class="row">
						<div class="column column-2">Type</div>
						<div class="column column-4">
							<form:radiobutton path="contactDtos[${count.index}].type" value="MOBILE"/>Mobile
							<form:radiobutton path="contactDtos[${count.index}].type" value="PHONE"/>Phone
						</div>
					</div>
					<div class="row">
						<div class="column column-2">Number</div>
						<div class="column column-4">
							<form:input path="contactDtos[${count.index}].value"/>
						</div>
					</div>
					<form:hidden path="contactDtos[${count.index}].id" value="${personDto.contactDtos[$count.index].id}"/>
					<c:set var="contactCount" value="${count.index + 1}"/>
				</c:forEach>
				<c:if test="${not empty personDto.contactDtos}"><hr></c:if>
				<div class="row">
					<div class="column column-2">Type</div>
					<div class="column column-4">
						<form:radiobutton path="contactDtos[${contactCount}].type" value="MOBILE"/> Mobile
						<form:radiobutton path="contactDtos[${contactCount}].type" value="PHONE"/> Phone
					</div>
				</div>
				<div class="row">
					<div class="column column-2">Number</div>
					<div class="column column-4">
						<form:input path="contactDtos[${contactCount}].value" value="${personDto.contactDtos[contactCount].value}"/>
					</div>
				</div>
			</fieldset>
		</div>
		<div style="padding:1%">
			<button type="submit" name="save" value="save">Save</button>
		</div>
		</fieldset>	
	</div>
	</form:form>
</body>
</html>