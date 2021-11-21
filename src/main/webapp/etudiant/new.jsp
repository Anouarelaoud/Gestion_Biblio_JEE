<jsp:include page="../common/headerl.jsp"></jsp:include>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action="?action=insert" method="post">

					<h2>Ajouter un etudiant</h2><hr>
	
					<fieldset class="form-group">
						<label>CIN</label> 
						<input type="text" class="form-control" name="cin" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Nom</label> 
						<input type="text" class="form-control" name="nom" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Prenom</label> 
						<input type="text" class="form-control" name="prenom" required="required">
					</fieldset>
	
					<fieldset class="form-group">
						<label>Filiere</label> 
							<select class="form-control" name="filiere">
							  <option value="">choisir la filiere</option>
							  <option value="API1">API1</option>
							  <option value="API2">API2</option>
							  <option value="GPEE2">GPEE1</option>
							  <option value="TC">TC</option>
							  <option value="GRT2">GRT2</option>
							  <option value="GI2">GI2</option>
							  <option value="GE2">GE2</option>
							  <option value="GPEE2">GPEE2</option>
							  <option value="GRT3">GRT3</option>
							  <option value="GI3">GI3</option>
							  <option value="GE3">GE3</option>
							  <option value="GPEE3">GPEE3</option>
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
