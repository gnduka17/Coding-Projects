<?php
session_start();

?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Foodzine | Register</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="stylesheet.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<style>
		/*Default is phone size*/
		.container{
			margin-top: 150px;
			background-color: #FFFFFF;
			box-shadow: 3px 3px 3px 3px #E9E9E9;
		}
		h3{
			font-family: "Roboto_Light";

		}
		.logo, .logo:hover{
		   text-decoration: none;
			color: #488286;
		}
		body{
			padding: 10px;
		}
		.btn{
			background-color: #488286;
			margin-left: 14px;
			margin-right: 14px;
		}
		.aLink{
			color: #488286;
		}
		.errorMess{
			color: red;
		}
		
		/* Medium devices (tablets, 768px and up)*/
		@media (min-width: 768px){
			/*body{
				background-color: yellow;
			}*/
			.container{
				width: 500px;
			}

		}

		/* Large devices (desktops, 992px and up)*/
		@media (min-width: 992px){
			.container{
				width: 600px;
			}

		}
	</style>
</head>
<body class="bg-warning">
	<a class = "logo" href="index.php">Foodzine</a>
	<div class = "container  shadow-lg p-3 mb-5 rounded">
		<form id="registerForm" action="registerConfirm.php" method="POST">
			<h3 class="text-center">Register</h3>
			<div class="errorMess"></div>
			  <div class="form-group row">
			    <div class = "col-sm-12">
			    	<input type="text" class="form-control" id="fnameID" name="fname" placeholder="First Name">
			    </div>
			  </div>
			  <div class="form-group row">
			    <div class = "col-sm-12">
			    	<input type="text" class="form-control" id="lnameID" name="lname" placeholder="Last Name">
			    </div>
			  </div>
		  <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="text" class="form-control" id="usernameID" name="username" placeholder="Username">
		    </div>
		  </div>
		  <div class="form-group row">
		  	<div class=" col-sm-12">
		    	<input type="password" class="form-control" id="passwordID" name = "password" placeholder="Password">
		    </div>
		  </div>
		  <div class = "form-group row">
		  	<button type="submit" class="btn btn-block">Create Profile</button>
		  </div>
		  <div class="clearfix"> 
		  	<!-- <a class= "float-right aLink" href="index.html">Redirect to Home Page</a> -->
		  	<!-- <a class = "aLink" href="index.html">Redirect to Home Page</a> -->
		  		Already have an account?
		  	<a class = "aLink" href="login.php">Login</a>
		  </div>
		</form>
		
	</div>
<script>
	document.querySelector("#registerForm").onsubmit = function(event){
		
		let fname = document.querySelector("#fnameID").value.trim();
		let lname = document.querySelector("#lnameID").value.trim();
		let username = document.querySelector("#usernameID").value.trim();
		let password = document.querySelector("#passwordID").value.trim();

		if(username.length > 0 && password.length > 0 && fname.length > 0 && lname.length > 0){
			document.querySelector(".errorMess").innerHTML = "";
		}
		else{
			// event.preventDefault();
			document.querySelector(".errorMess").innerHTML = "Error: Please dont leave anything blank";
			return false;
		}
	}
</script>
</body>
</html>