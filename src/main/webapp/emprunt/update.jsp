<%@ page import="java.util.List" %>
<%@ page import="beans.Etudiant"%>
<jsp:useBean id="Etudiant" class="beans.Etudiant" />
<%@ page import="beans.Livre"%>
<jsp:useBean id="Livre" class="beans.Livre" />
<%@ page import="beans.Emprunt"%>
<jsp:useBean id="Emprunt" class="beans.Emprunt" />
<%
Emprunt emprunt =  (Emprunt)request.getAttribute("emprunt");
List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
List<Livre> livres = (List<Livre>) request.getAttribute("livres");
%>
<jsp:include page="../common/headerl.jsp"></jsp:include>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action="?action=update&id=<%=emprunt.getId() %>" method="post">
					<h2> Modifier un emprunt </h2><hr>
	
					<fieldset class="form-group">
						<label>Etudiant</label> 
							<select class="form-control" name="etudiantId">
								
								<option value="<%=emprunt.selectEtudiant(emprunt.getEtudiantId()).getId()%>">
									<%=emprunt.selectEtudiant(emprunt.getEtudiantId()).getNom()%> 
									<%=emprunt.selectEtudiant(emprunt.getEtudiantId()).getPrenom()%>
									(<%=emprunt.selectEtudiant(emprunt.getEtudiantId()).getCin()%>)
								</option>
								<%
								for (Etudiant etudiant :  etudiants)
								{
								%>
						  		<option value="<%=etudiant.getId()%>">
							  		<%=etudiant.getNom()%>
							  		<%=etudiant.getPrenom()%> 
							  		(<%=etudiant.getCin()%>)
						  		</option>
						  		<%} %>
							</select>
					</fieldset>
					
					<fieldset class="form-group">
						<label>Livre</label> 
							<select class="form-control" name="livreId">
								
								<option value="<%=emprunt.selectLivre(emprunt.getLivreId()).getId()%>">
									<%=emprunt.selectLivre(emprunt.getLivreId()).getTitre()%> 
									(<%=emprunt.selectLivre(emprunt.getLivreId()).getNum()%>)
								</option>
								<%
								for (Livre livre :  livres)
								{
								%>
						  		<option value="<%=livre.getId()%>">
						  			<%=livre.getTitre()%> (<%=livre.getNum()%>)
						  		</option>
						  		<%} %>
							</select>
					</fieldset>
	
				<button type="submit" class="btn btn-primary">Save</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
