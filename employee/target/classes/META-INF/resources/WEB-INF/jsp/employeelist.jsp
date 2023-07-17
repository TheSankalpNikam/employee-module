<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<div>
			<h3>List of Employees</h3>
		</div>
		<div>
			<table class="table table-striped table-light">
				<thead>
					<tr>
						<th scope="col">First Name</th>
						<th scope="col">Last Name</th>
						<th scope="col">Email Id</th>
						<th scope="col">Mobile No</th>
						<th scope="col">Gender</th>
						<th scope="col">Country</th>
						<th scope="col">City</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items = "${employees}" var = "employee">
						<tr>
							<td>${employee.firstname}</td>
							<td>${employee.lastname}</td>
							<td>${employee.emailid}</td>
							<td>${employee.mobileno}</td>
							<td>${employee.gender}</td>
							<td>${employee.country}</td>
							<td>${employee.city}</td>
							<td><a href="update-employee?id=${employee.id}" class="btn btn-success">Update</a></td>
							<td><a href="delete-employee?id=${employee.id}" class="btn btn-warning">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<a href="add-employee" class="btn btn-success">Add Employee</a>
			</div>
		</div>

	</div>
<%@ include file="common/footer.jspf" %>