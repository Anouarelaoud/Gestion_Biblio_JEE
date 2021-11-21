<%@ page import="java.util.List" %>
<%@ page import="beans.Etudiant"%>
<jsp:useBean id="Etudiant" class="beans.Etudiant" />
<%@ page import="beans.Livre"%>
<jsp:useBean id="Livre" class="beans.Livre" />
<%List<Livre> livres = (List<Livre>) request.getAttribute("livres");
List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
%>
<jsp:include page="../common/headerl.jsp"></jsp:include>
	
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action="?action=insert" method="post">

					<h2>Ajouter un emprunt</h2><hr>
	
					<fieldset class="form-group">
						<label>Etudiant</label> 
							<select class="form-control" name="etudiantId">
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
								
								<%
								System.out.println("tttttttttttttttttttttt");
								for (Livre livre :  livres)
								{
								%>
						  		<option value="<%=livre.getId()%>"><%=livre.getTitre()%> (<%=livre.getNum()%>)</option>
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
