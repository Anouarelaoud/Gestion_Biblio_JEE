<%@ page import="java.util.List" %>
<%@ page import="beans.Emprunt"%>
<jsp:useBean id="Emprunt" class="beans.Emprunt" />
<%@ page import="beans.Etudiant"%>
<jsp:useBean id="Etudiant" class="beans.Etudiant" />
<%@ page import="beans.Livre"%>
<jsp:useBean id="Livre" class="beans.Livre" />
<%List<Emprunt> emprunts = (List<Emprunt>) request.getAttribute("listEmprunt");
%>
<jsp:include page="../common/headerl.jsp"></jsp:include>

	<div class="row">

		<div class="container">
		<br>
			<h3 class="text-center">Liste des emprunts</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="text-center">Etudiant</th>
						<th class="text-center">Livre</th>
						<th class="text-center">Remis ?</th>
						<th class="text-center">Control</th>
					</tr>
				</thead>
				<tbody>
				<%
				for (Emprunt emprunt :  emprunts)
				{
					int etudiantId = emprunt.getEtudiantId();
					int livreId = emprunt.getLivreId();
					Etudiant etudiant = emprunt.selectEtudiant(etudiantId);
					Livre livre = emprunt.selectLivre(livreId);
				%>
						<tr>
							<td class="text-center">
								<%= etudiant.getNom()%> 
								<%= etudiant.getPrenom()%>
							</td>
							<td class="text-center">
								<%= livre.getTitre()%>
							</td>
							<td class="text-center">
								<%= emprunt.getRemis()%>
							</td>
							<td class="text-center">
								<button  class="btn btn-primary">
									<a href="Emprunt?action=edit&id=<%= emprunt.getId()%>" style="color :#ffffff">Edit</a>
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button  class="btn btn-danger">
									<a href="Emprunt?action=delete&id=<%= emprunt.getId()%>" style="color :#ffffff">Delete</a>
								</button>
							</td>
						</tr>
				<%
				} 
				%>
				</tbody>

			</table>
			<div class="container text-left center">

				<a href="<%=request.getContextPath()%>/Emprunt?action=new"
					class="btn btn-primary">Ajouter emprunt</a>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
