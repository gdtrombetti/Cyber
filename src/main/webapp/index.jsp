<!doctype html>
<html data-ng-app="MyApp">
<head>
	<title>Testing My Skills</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css" media="all" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0-rc.0/angular.min.js"></script>
	<script src="js/controllers.js"></script>
</head>
		<body>
			<h1>Test</h1>
				<div class="container">
						<div data-ng-controller="UserController">
							<div class="btn-group-vertical">
								<input type="button" class="btn btn-default btn-lg" value="List" data-ng-click="ShowList()" />
								<input type="button" class="btn btn-default btn-lg" value="Create" data-ng-click="ShowHide()" />
			       				 <br />
			       				 <br />
			       				</div>
			       				<div data-ng-show="ListVisible"> 
								<input type="button" class="btn btn-default btn-lg" value="List" data-ng-click="get_user()" />
								</div>
		       			 	<div data-ng-show="IsVisible">
		       			 		<div id="messages" class="alert alert-success" data-ng-show="messages" data-ng-bind="messages"></div>
		  						<div data-ng-show="showTheForm = true">
		  						<form name="user" data-toggle="validator" novalidate role="form" data-ng-controller="FormController">
				    				<div class="control-group" data-ng-class="{true: 'error'}[submitted && form.name.$invalid]">
								      <label for="name">Name</label>
								      <span class="label label-danger" data-ng-show="submitted && user.name.$error.required">Required!</span>
								      <input type="text" name="name" data-ng-model="name" class="form-control" required>
									</div>
								    <div class="form-group">
								      <label for="email">E-mail address</label>
								      <span class="label label-danger" data-ng-show="submitted && user.email.$error.required">Required!</span>
								      <span class="label label-danger" data-ng-show="submitted && user.$error.email">Invalid email!</span>
								      <input type="email" name="email" data-ng-model="email" class="form-control" required>  
								    </div>
							
									<div class="form-group">
								      <label for="telephone">Telephone</label>
								      <span class="label label-danger" data-ng-show="submitted && user.telephone.$error.required">Required!</span>
								      <input type="text" name="telephone" data-ng-model="telephone" class="form-control" required>
									</div>
							
									<div class="form-group">
								      <label for="street">Street</label>
								      <span class="label label-danger" data-ng-show="submitted && user.street.$error.required">Required!</span>
								      <input type="text" name="street" data-ng-model="street" class="form-control" required>
									</div>
									
									<div class="form-group">
									      <label for="city">City</label>
									      <span class="label label-danger" data-ng-show="submitted && user.city.$error.required">Required!</span>
									      <input type="text" name="city" data-ng-model="city" class="form-control" required>
									</div>
							
									<div class="form-group">
									      <label for="state">State</label>
									      <span class="label label-danger" data-ng-show="submitted && user.state.$error.required">Required!</span>
									      <input type="text" name="state" data-ng-model="state" class="form-control" required="true">
									</div>
									
									<div class="form-group">
									      <label for="zip">Zip</label>
									      <span class="label label-danger" data-ng-show="submitted && user.zip.$error.required">Required!</span>
									      <input type="text" name="zip" data-ng-model="zip" class="form-control" required>
									</div>
									 <div class="form-group">
		    							  <button data-ng-click="submit(user,form);" class="btn btn-default btn-lg">Submit</button>
		    							  <div data-ng-if="show == true">
			    							  <h2>User Added!</h2>
			    							  <h4>Name - {{rname}}</h4>
			    							  <h4>Email - {{remail}}</h4>
			    							  <h4>Telephone - {{rtelephone}}</h4>
			    							  <h4>Street - {{rstreet}}</h4>
			    							  <h4>City - {{rcity}}</h4>
			    							  <h4>State - {{rstate}}</h4>
			    							  <h4>Zip - {{rzip}}</h4>
		    							  </div>
		    						</div>
		  						</form>
		  					</div>
  						</div>
  					</div>
				</div>
			
		</div>
	</body>
</html>