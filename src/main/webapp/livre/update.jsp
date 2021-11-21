<%@ page import="beans.Livre"%>
<jsp:useBean id="Livre" class="beans.Livre" />
<%Livre livre =  (Livre)request.getAttribute("livre");%>
<jsp:include page="../common/headerl.jsp"></jsp:include>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action="?action=update&id=<%=livre.getId() %>" method="post">
					<h2> Modifier un livre </h2><hr>
	
					<fieldset class="form-group">
						<label>Num</label> 
						<input type="text" class="form-control" name="num" value="<%=livre.getNum() %>" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Titre</label> 
						<input type="text" class="form-control" name="titre" value="<%=livre.getTitre() %>" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Stock</label> 
						<input type="text" class="form-control" name="stock" value="<%=livre.getStock() %>" required="required">
					</fieldset>
	
				<button type="submit" class="btn btn-primary">Save</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
