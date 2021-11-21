<jsp:include page="../common/headerl.jsp"></jsp:include>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action="?action=insert" method="post">

					<h2>Ajouter un livre</h2><hr>
	
					<fieldset class="form-group">
						<label>Num</label> 
						<input type="text" class="form-control" name="num" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>titre</label> 
						<input type="text" class="form-control" name="titre" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>stock</label> 
						<input type="text" class="form-control" name="stock" required="required">
					</fieldset>
	
					
					<button type="submit" class="btn btn-primary">Save</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
