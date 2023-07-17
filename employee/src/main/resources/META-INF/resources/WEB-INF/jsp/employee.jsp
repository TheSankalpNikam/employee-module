<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class = "container">
		<div>
			<h3>Enter EMPLOYEE Details</h3>
		</div>
		
		<div>
			<form:form method="post" modelAttribute = "employee">
				First Name: <form:input type="text" path="firstname"/>
				Last Name: <form:input type="text" path="lastname"/>
				Mail Id: <form:input type="text" path="emailid"/>
				Mobile No: <form:input type="text" path="mobileno"/>
				Gender: <form:input type="text" path="gender"/>
				Country: <form:input type="text" path="country"/>
				City: <form:input type="text" path="city"/>
				
				<br>
				<br>
				
				<input type="submit" class = "btn btn-success">
			
			</form:form>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>