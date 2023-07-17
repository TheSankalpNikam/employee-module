<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href = "webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel = "stylesheet">
<title>WESL Login</title>
</head>
<body>

	<div class="container">
		<div>
			<h2 style="color:#007C80">WEB ELEMENT SOLUTION LIMITED</h2>
			<h3><b>Sign in</b></h3>
			<h4>to continue to work</h4>
		</div>
		<div>
			<br>	
			<div>
				<pre>${error}</pre>
			</div>
			<form method="post">
				<div>
					<label>Username:</label>
					<div>
						<input type="text" name="name" required="required">	
					</div>
				</div>

				<div>
					<label>Password:</label>
					<div>
						<input type="password" name="password" required="required">	
					</div>
				</div>
				<div>
					<br> <br>
					<input type="submit" class="btn btn-success">
				</div>
				
			</form>
		</div>
	</div>
	<script src ="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src ="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>