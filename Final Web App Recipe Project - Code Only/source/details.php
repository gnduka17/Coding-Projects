<?php
session_start();
// if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {
	if ( !isset($_GET['id']) || empty($_GET['id']) ) {
		$error = "Invalid Recipe ID.";
	} else {
		$host = "303.itpwebdev.com";
		$user = "nduka_recipe_user";
		$password = "uscItp2021";
		$db = "nduka_recipe_db";
		$mysqli = new mysqli($host, $user, $password, $db);
		if ( $mysqli->connect_errno ) {
			echo $mysqli->connect_error;
			exit();
		}
		$sql = "SELECT title, cuisine, servings, ingredients, directions, imageName, prepTime, cookTime, difficulty FROM recipes WHERE id = '" . $_GET['id'] . "';";
		$results = $mysqli->query($sql);
		if ( !$results ) {
			echo $mysqli->error;
			exit();
		}
		$row = $results->fetch_assoc();
		$ingreArr = explode("\n", $row['ingredients']);
		$mysqli->close();
	
	}
// }
// else{
	// header("Location: index.php");
// }

?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Foodzine | Details</title>
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
		#title{
			font-size: 50px;
			font-family: "Roboto_Light";
			text-align: center;
			margin-bottom: 10px;
		}
		#prepInfo{
			text-align: center;
		}
		.card-columns {
		   column-count: 2;
		}
		#bottomSection{
			display: none;
		}
		#hide{
			display: none;
		}
		#footer{
			padding-top: 3px;
			padding-bottom: 3px; 
			text-align: center;
			
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
		
		/* Medium devices (tablets, 768px and up)*/
		@media (min-width: 768px){
			#title{
				font-size: 60px;
				font-family: "Roboto_Light";
				text-align: center;
				margin-bottom: 30px;
			}
			.card-columns {
			   column-count: 3;
			}
			#bottomSection{
				display: none;
			}
		}
		/* Large devices (desktops, 992px and up)*/
		@media (min-width: 992px){
			#title{
				font-size: 70px;
				font-family: "Roboto_Light";
				text-align: center;
				margin-bottom: 40px;
			}
			.card-columns {
			   column-count: 3;
			}
			.hiddenSec{
				display: none;
			}
			#bottomSection{
				display: block;
			}
			#hide{
				display: block;
			}
			.box{
				width: 390px;
			}
			.searchBox{
				display: none;
			}
			#collapsibleNavbar{
				margin-left: auto;
				max-width: 430px;
			}
			.nav-item{
				/*padding-right: 5px;*/
			}
		}
	</style>
</head>
<body>
	
	<div class="clearfix">
		<nav class="navbar navbar-expand-lg pt-0 mt-0 bg-warning">
			<a class = "logo ml-2" href="index.php">Foodzine</a>
			<?php if( !isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] ): ?>
				<?php else: ?>
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
			<?php endif;?>
			<button class="navbar-toggler custom-toggler ml-auto" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
		    	<ul class="navbar-nav">
		    		<?php if( !isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] ): ?>
		    			<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="index.php">Home</a>
			      		</li>
		      			<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="login.php">Login</a>
			      		</li>
						<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="register.php">Sign Up</a>
			      		</li>
					<?php else: ?>
						<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="index.php">Home</a>
			      		</li>
			      		<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="search.php">Search by Cuisine</a>
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
				<?php endif;?>
		  	</div>
		</nav>
	</div>
	<?php if ( isset($error) && !empty($error) ) : ?>

		<div class="text-danger">
			<?php echo $error; ?>
		</div>

	<?php else : ?>

	<div id="topSection">
		<div id="title">
			<?php echo $row['title']; ?>
		</div>
		
	</div>	
	<div id="middleSection">
		<div class="container">
			<div class="row">
				<div class="col-12 col-md-12 col-lg-7">
					<img class = "img-fluid rounded" alt = "fillerImage" src='../images/<?=$row['imageName']?>'>
					<p id="prepInfo">Prep <?php echo $row['prepTime']; ?> Minutes | Cook <?php echo $row['cookTime']; ?> Minutes | <?php echo $row['difficulty']; ?> | Serves <?php echo $row['servings']; ?></p>
				</div>
				<div class="hiddenSec">
					<div class="container">
						<h2>Ingredients:</h2>
						<div class="col">
								<ul class="card-columns">
									<?php 
								          	for($i = 0; $i < sizeof($ingreArr); $i++) {  ?>
								          		<li><?php echo $ingreArr[$i]; ?></li>
								    <?php }?>
								</ul>	
						</div> 
					</div>
				</div>

				<div class="col-12 col-md-12 col-lg-5">
					<h2>Instructions:</h2>
					<p><?php echo $row['directions']; ?></p>
					
				</div>
			</div>
		</div>
		
	</div>
	<div id = "bottomSection">
		<div class="container">
			<h2>Ingredients:</h2>
			<div class="col">
				<ul class="card-columns">
					<?php 
				          	for($i = 0; $i < sizeof($ingreArr); $i++) {  ?>
				          		<li><?php echo $ingreArr[$i]; ?></li>
				    <?php }?>
				</ul>	
			</div>
		</div>
	</div>
	<?php endif; ?>
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