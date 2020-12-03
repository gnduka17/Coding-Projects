<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Register page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" href="CSS/Login.css">
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-light">
		  <a class="navbar-brand text-white" href="#">USC CS310 Stock Portfolio Management</a>
		</nav>
		
		<div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
			<form class="justify-content-center" id="form" action="RegisterServlet" method="POST"> 
				<div class="form-group">
					<div class="banner">
						<p class="banner-text">Register</p>
					</div>
				</div>
				<div id="formerror" style="color:red;"> ${errorMessage}</div>
				<div class="form-group">
					<input class="bar" type="text" name="new_username" placeholder="Username"/>
				</div>
				<div class="form-group">
					<input class="bar" type="password" name="new_userpass1" placeholder="Password"/>
				</div>
				<div class="form-group">
					<input class="bar" type="password" name="new_userpass2" placeholder="Confirm Password"/>
				</div>
				<div class="form-group">
					<div class="bottomButtons">	
						<a class= "link" style="padding-top: 20px; "href="index.jsp">Cancel</a>
						<input id="registerButton" class="button" type="submit" name="status" value="Create User"/> 
					</div>
				</div>
			</form>  
		</div>
		
	  <!-- Bootstrap Scripts -->
	  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>