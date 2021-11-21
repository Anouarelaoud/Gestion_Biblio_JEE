<%@ page import="jakarta.servlet.http.*" %>
<%
	HttpSession ses = request.getSession();
	if (ses.getAttribute("user") != null)
		response.sendRedirect("/Etudiant");
%>
<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: #007bff">
		<div>
			<a href="" class="navbar-brand"> Biblio Ensa</a>
		</div>

		<ul class="navbar-nav navbar-collapse justify-content-end">
			<li><a href="<%= request.getContextPath() %>/login/login.jsp" class="nav-link">Login</a></li>
			<li><a href="<%= request.getContextPath() %>/signUp/signUp.jsp" class="nav-link">Signup</a></li>
		</ul>
	</nav>
</header>