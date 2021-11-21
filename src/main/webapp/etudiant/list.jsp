<%@ page import="java.util.List" %>
<%@ page import="beans.Etudiant"%>
<jsp:useBean id="Etudiant" class="beans.Etudiant" />
<%List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("listEtudiant"); %>
<jsp:include page="../common/headerl.jsp"></jsp:include>

	<div class="row">

		<div class="container">
		<br>
			<h3 class="text-center">Liste des etudiants</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="text-center">CIN</th>
						<th class="text-center">Nom</th>
						<th class="text-center">Prenom</th>
						<th class="text-center">Filiere</th>
						<th class="text-center">Nombre d'emprunts</th>
						<th class="text-center">Control</th>
					</tr>
				</thead>
				<tbody>
				<%
				/* System.out.println(session.getAttribute("user")); */
				for (Etudiant etudiant :  etudiants)
				{
				%>
						<tr>
							<td class="text-center"><%= etudiant.getCin()%></td>
							<td class="text-center"><%= etudiant.getNom()%></td>
							<td class="text-center"><%= etudiant.getPrenom()%></td>
							<td class="text-center"><%= etudiant.getFiliere()%></td>
							<td class="text-center"><%= etudiant.getEmprunt()%></td>
							<td class="text-center">
								<button  class="btn btn-primary">
									<a href="?action=edit&id=<%= etudiant.getId()%>" style="color :#ffffff">Edit</a>
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button  class="btn btn-danger">
									<a href="?action=delete&id=<%= etudiant.getId()%>" style="color :#ffffff">Delete</a>
								</button>
							</td>
						</tr>
				<%
				} 
				%>
				</tbody>

			</table>
			<div class="container text-left center">

				<a href="<%=request.getContextPath()%>/Etudiant?action=new"
					class="btn btn-primary">Ajouter etudiant</a>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
