<?php
session_start();
if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {


}
else{
	header("Location: index.php");
}

?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Foodzine | Search</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="stylesheet.css">
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<style>
		body,html{
			margin: 0;
			padding: 0;
		}
		body{
			background-color: orange;
		}
		.logo{
			font-size: 40px;
			text-decoration: none !important;
		}
		.logo, .logo:hover{
		   	text-decoration: none;
			color: #488286;
		}
		.custom-toggler .navbar-toggler-icon {
			background-image: url("data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 32 32' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='rgba(72,130,134, 0.5)' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 8h24M4 16h24M4 24h24'/%3E%3C/svg%3E");
		}
		.custom-toggler.navbar-toggler {
			border-color: rgb(72,130,134);
		} 
		.active{
			color:black;
		}
		.card-columns {
		   column-count: 2;
		   font-size: 15px;
		}
		.card-columns li {
		   margin-bottom: 15px;
		}
		.cuisine{
			text-decoration: none;
			color: #488286;
		}
		.cuisine:hover{
			text-decoration: none;
			color: black;
		}
		.box{
			width: 320px;
			height: 50px;
			background-color: white;
			border-radius: 30px;
			display: flex;
			align-items: center;
			padding: 20px;
			margin-top: 15px;
			float: right;
		}
		.box > i {
			font-size: 20px;
			color: #488286;
		}
		.box > input{
			flex: 1;
			height: 40px;
			border: none;
			outline: none;
			font-size: 18px;
			padding-left: 10px;
		}
		#hide{
			display: none;
		}
		#footer{
			padding-top: 3px;
			padding-bottom: 3px; 
			text-align: center;
			position: fixed;
		  	left: 0;
		  	bottom: 0;
		  	width: 100%;
		}

		/* Medium devices (tablets, 768px and up)*/
		@media (min-width: 768px){
			#footer{
				padding-top: 3px;
				padding-bottom: 3px; 
				text-align: center;
				/*position: fixed;
			  	left: 0;
			  	bottom: 0;
			  	width: 100%;*/
			}
			
			.card-columns {
			   column-count: 2;
			   font-size: 16px;
			}
		
		}

		/* Large devices (desktops, 992px and up)*/
		@media (min-width: 992px){
			.card-columns {
			   column-count: 3;
			   font-size: 20px;
			}
			#hide{
				display: block;
			}
			.box{
				width: 395px;
			}
			.searchBox{
				display: none;
			}
			.nav-item{
				margin-right: 20px;
			}
			#collapsibleNavbar{
				margin-left: auto;
				max-width: 430px;
			}
		}
	</style>
</head>
<body>
	<div class="clearfix">
		<nav class="navbar navbar-expand-lg pt-0 mt-0 bg-warning">
			<a class = "logo ml-2" href="index.php">Foodzine</a>
			<div id="hide" class="searchBox ml-4">
				<form action="results.php" method="GET" class="search-form">
			       <!--  <div class="form-row"> -->
			          <div class="box">
						<i class="fa fa-search" aria-hidden="true"></i>
						<input type="hidden" name="bar" value="true"/>
						<input type="text" name="input" class="form-control search-id" placeholder="Search...">
					  </div>
			        <!-- </div>  -->
			    </form>	
			</div>
			<button class="navbar-toggler custom-toggler ml-auto" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
		    	<ul class="navbar-nav">
		    		<li class="nav-item ml-auto">
		        		<a class="nav-link aLink" href="index.php">Home</a>
		      		</li>
		    		<li class="nav-item ml-auto">
		        		<a class="nav-link aLink active" href="search.php">Search by Cuisine</a>
		      		</li>
		    		<li class="nav-item ml-auto">
		        		<a class="nav-link aLink" href="profile.php">Profile</a>
		      		</li>
		      		<li class="nav-item ml-auto">
		        		<a class="nav-link aLink" href="logout.php">Logout</a>
		      		</li>
		    	</ul>
		    	<div class="searchBox">
					<form action="results.php" method="GET" class="search-form">
				          <div class="box">
							<i class="fa fa-search" aria-hidden="true"></i>
							<input type="hidden" name="bar" value="true"/>
							<input type="text" name="input" class="form-control search-id" placeholder="Search...">
						  </div>
				    </form>	
				</div>
		  	</div>
		</nav>
	</div>
	<div id="cuisineList">
		<div class="container mt-5">
			<div class="col text-center">
					<ul class="list-unstyled card-columns">
						<li><a class = "cuisine" href="results.php?bar=false&cuisine=All">See All Recipes</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=African">African</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=American">American</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Australian">Australian</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Cajun">Cajun</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Caribbean">Caribbean</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Chinese">Chinese</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=English">English</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=European">European</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Filipino">Filipino</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=German">German</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Greek">Greek</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Hawaiian">Hawaiian</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Indian">Indian</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Italian">Italian</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Jamaican">Jamaican</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Jewish">Jewish</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Korean">Korean</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Mediterranean">Mediterranean</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Mexican">Mexican</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Middle%20Eastern">Middle Eastern</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Native%20American">Native American</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Russian">Russian</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Soul%20Food">Soul Food</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=South%20American">South American</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Spanish">Spanish</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Thai">Thai</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Turkish">Turkish</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Ukrainian">Ukrainian</a></li>
					    <li><a class = "cuisine" href="results.php?bar=false&cuisine=Vietnamese">Vietnamese</a></li>
					</ul>	
			</div> 
		</div>
	</div>
	<div class="bg-warning" id="footer">
		Website created by Gloria Nduka | May 2021
	</div>
<script>
	document.querySelector(".search-form").onsubmit = function(){
		let input = document.querySelector(".search-id").value.trim();
		if(input.length==0){
			return false;
		}
	}
</script>
</body>
</html>