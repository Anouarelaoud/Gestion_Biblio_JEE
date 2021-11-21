
<%@ page import="java.util.List" %>
<%List<Livre> livres = (List<Livre>) request.getAttribute("listLivre"); %>
<jsp:include page="../common/headerl.jsp"></jsp:include>

	<div class="row">

		<div class="container">
		<br>
			<h3 class="text-center">Liste des livres</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="text-center">Num</th>
						<th class="text-center">Titre</th>
						<th class="text-center">Date</th>
						<th class="text-center">Stock</th>
						<th class="text-center">Control</th>
					</tr>
				</thead>
				<tbody>
				<%@ page import="beans.Livre"%>
				<jsp:useBean id="Livre" class="beans.Livre" />
				<%
				for (Livre livre :  livres)
				{
				%>
						<tr>
							<td class="text-center"><%= livre.getNum()%></td>
							<td class="text-center"><%= livre.getTitre()%></td>
							<td class="text-center"><%= livre.getDate()%></td>
							<td class="text-center"><%= livre.getStock()%></td>
							<td class="text-center">
								<button  class="btn btn-primary">
									<a href="?action=edit&id=<%= livre.getId()%>" style="color :#ffffff">Edit</a>
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button  class="btn btn-danger">
									<a href="?action=delete&id=<%= livre.getId()%>" style="color :#ffffff">Delete</a>
								</button>
							</td>
						</tr>
				<%
				}
				%>
				</tbody>

			</table>
			<div class="container text-left center">

				<a href="<%=request.getContextPath()%>/Livre?action=new"
					class="btn btn-primary">Ajouter livre</a>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
