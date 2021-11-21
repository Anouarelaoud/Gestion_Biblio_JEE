<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.*" %>
<%
	HttpSession ses = request.getSession();
	if (ses.getAttribute("user") == null)
		response.sendRedirect("login/login.jsp");
%>

<html>
<head>
	<title>Biblio</title>
	
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #007bff">
			<div>
				<a href="<%=request.getContextPath()%>/Etudiant" class="navbar-brand"> Biblio Ensa</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/Etudiant"
					class="nav-link">Etudiants</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/Livre"
					class="nav-link">Livres</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/Emprunt"
					class="nav-link">Emprunts</a></li>
			</ul>

			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="<%=request.getContextPath()%>/login"
					class="nav-link">Se deconnecter</a></li>
			</ul>
		</nav>
	</header>