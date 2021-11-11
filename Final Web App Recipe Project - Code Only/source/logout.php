<?php
	session_start();
	session_destroy();
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Foodzine | Log Out</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="stylesheet.css">
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<style>
		/*Default is phone size*/
		.container{
			margin-top: 200px;
			background-color: #FFFFFF;
			box-shadow: 3px 3px 3px 3px #E9E9E9;
		}
		body{
			/*background-color: #EDEEF8;*/
			padding: 10px;
		}
		h3{
			font-family: "Roboto_Light";
		}
		.logo, .logo:hover{
			font-size: 50px;
		   text-decoration: none;
			color: #488286;
		}
		.btn{
			background-color: #488286;
			margin-left: 14px;
			margin-right: 14px;
			color: #EDEEF8;
			font-weight: bold;
		}
		.clearfix{
			color: #488286;
		}
		.custom-toggler .navbar-toggler-icon {
			background-image: url("data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 32 32' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='rgba(72,130,134, 0.5)' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 8h24M4 16h24M4 24h24'/%3E%3C/svg%3E");
		}
		.custom-toggler.navbar-toggler {
			border-color: rgb(72,130,134);
			position: relative;
			bottom: 65px;
		} 
		#collapsibleNavbar{
			position: relative;
			bottom: 65px;
		}
		.active{
			color:black;
		}
		.aLink{
			color: #488286;
		}
		
		/* Medium devices (tablets, 768px and up)*/
		@media (min-width: 768px){
			.container{
				width: 500px;
			}
			.nav-item{
				margin-right: 50px;
			}
			#collapsibleNavbar{
				margin-left: auto;
				max-width: 430px;
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
	<a class = "logo" href="">Foodzine</a>
	<div class = "container shadow-lg p-3 mb-5 bg-white rounded">
		<form>
			<h3 class="text-center">You have successfully been logged out.</h3>
		  <div class="clearfix"> 
		  	<a class= "float-right aLink" href="index.php">Redirect to Home Page</a>
		  </div>
					  
		</form>
		
	</div>

</body>
</html>