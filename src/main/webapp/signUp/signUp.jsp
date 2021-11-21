<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Sign up</title>
	
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">

		<h1>Sign up</h1>
		<%System.out.println(request.getContextPath()); %>
			
				<form action="<%=request.getContextPath()%>/signUp" method="post">

					<div class="form-group">
						<label for="uname">Nom</label>
						<input type="text" class="form-control" name="nom" required>
					</div>

					<div class="form-group">
						<label for="uname">Prenom</label>
						<input type="text" class="form-control" name="prenom" required>
					</div>

					<div class="form-group">
						<label for="uname">Username:</label>
						<input type="text" class="form-control" name="username" required>
					</div>

					<div class="form-group">
						<label for="uname">Password:</label>
						<input type="password" class="form-control" name="password" required>
					</div>

					<button type="submit" class="btn btn-primary">Submit</button>

				</form>
		</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>