<?php
session_start();
//SELECT RECIPES AND DISPLAY AND ADD DETAILS id
//do the SELECT to extract random recipe OF THE DAY - IF TIME
//and SELECT 
$host = "303.itpwebdev.com";
$user = "nduka_recipe_user";
$password = "uscItp2021";
$db = "nduka_recipe_db";
$mysqli = new mysqli($host, $user, $password, $db);
if ( $mysqli->connect_errno ) {
	echo $mysqli->connect_error;
	exit();
}
$sql = "SELECT id, title, imageName FROM recipes;";
$results_registered = $mysqli->query($sql);
if(!$results_registered) {
	exit();
}
$mysqli->close();
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Foodzine | Home</title>
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
		#headerImage{
			width: 100%;
		}
		.recipeTitle{
			text-decoration: none !important;
			color: black;
		}
		.recipeBox{
			margin-bottom: 30px;
		}
		.imageDivHolder{
			background-image: url('../images/jambalaya.jpeg');
			background-repeat: no-repeat;
			background-size: cover;
			padding-bottom: 90%;
			background-position: center;
			transition: all 1s;
		}
		.imageDivHolder:hover{
			transform: scale(1.06);
		}
		#outputs{
			margin-right: 5px;
			margin-left: 5px;
		}
		#headerWords{
			background-color: #488286;
			color: #EDEEF8;
			font-weight: bold;
			font-size: 17px;
			width: 100%;
			text-align: center;
			padding-right: 5px;
			padding-left: 5px;
		}
		#randFoodImage{
			width: 50%;
		}
		#randFoodTitle{
			color: #488286;
			font-weight: bold;
			font-size: 17px;
			text-align: center;
			width: 155px;
			position: relative;
		}
		.randFoodWords{
			background-color: #488286;
			color: #EDEEF8;
			font-weight: bold;
			font-size: 17px;
			text-align: center;
			width: 50%;
			/*position: relative;*/
			/*top:40px;*/
		}
		.imageDivHolder{
			background-image: url('../images/jambalaya.jpeg');
			background-repeat: no-repeat;
			background-size: cover;
			padding-bottom: 90%;
			background-position: center;
		}
		.logo{
			font-size: 40px;
			text-decoration: none !important;		}
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
		}
		#header{
			margin-top: 10px;
		}

		/* Medium devices (tablets, 768px and up)*/
		@media (min-width: 768px){
			#headerImage{
				width: 60%;

			}
			#headerWords{
				font-size: 30px;
				width: 40%;
				position: relative;
				right: 30px;
				top: 100px;
			}
			#randFoodImage{
				width: 40%;
				padding-right: 30px;
			}
			.randFoodWords{
				background-color: #488286;
				color: #EDEEF8;
				font-weight: bold;
				/*font-size: 25px;*/
				text-align: center;
				width: 60%;
				/*position: relative;*/
				/*top:70px;*/

			}
		}

		/* Large devices (desktops, 992px and up)*/
		@media (min-width: 992px){
			#headerImage{
				width: 50%;
			}
			#headerWords{
				font-size: 45px;
				width: 50%;
				position: relative;
				right: 50px;
				top: 110px;
			}
			#header{
				margin-top: 0px;
			}
			#randFoodImage{
				width: 35%;
				padding-right: 30px;
				margin-right: 20px;
			}
			.randFoodWords{
				background-color: #488286;
				color: #EDEEF8;
				font-weight: bold;
				font-size: 25px;
				text-align: center;
				width: 65%;
				/*position: relative;*/
				/*top:100px;*/
			}
			.box{
				width: 395px;
			}
			
			#hide{
				display: block;
			}
			.searchBox{
				display: none;
			}
			#collapsibleNavbar{
				margin-left: auto;
				max-width: 430px;
			}
			.nav-item{
				margin-right: 50px;
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
			        		<a class="nav-link aLink" href="login.php">Login</a>
			      		</li>
						<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="register.php">Sign Up</a>
			      		</li>
					<?php else: ?>
						<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="search.php">Search by Cuisine</a>
			      		</li>
						<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="profile.php">Profile</a>
			      		</li>
						<li class="nav-item ml-auto">
			        		<a class="nav-link aLink" href="logout.php">Logout</a>
			      		</li>
					<?php endif;?>
		    	</ul>
		    	<?php if( !isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] ): ?>
				<?php else: ?>
		    	<div class="searchBox">
					<form action="results.php" method="GET" class=" search-form">
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
	<div id = "header">
		<div class="container-fluid pl-0 pr-0 overflow-hidden">
			<div class="row">
				<div class="col-12 pr-0">
					<div class="clearfix">
						<div id="headerImage" class="float-left rounded">
							<img class= "img-fluid rounded" alt = "fillerImage" src='../images/globalCuisine.jpeg'>
						</div>
						<div id="headerWords" class="float-left">
							<p>World Cuisines and International Recipes For You!</p>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</div>
	<div id = "randFood" class = "mt-5 bg-warning">
		<div class="container-fluid pl-0 pr-0 overflow-hidden">
			<div class="row ">
				<div class="col-12 ">
					<div class="clearfix">
						<?php $row = $results_registered->fetch_assoc();
						$row = $results_registered->fetch_assoc();
						$row = $results_registered->fetch_assoc();?>
						<a href="details.php?id=<?php echo $row['id']; ?>">
							<div id="randFoodImage" class="float-right mr-3">
								<!-- <img class= "img-fluid" src='../images/globalCuisine.jpeg'> -->
								<div class="imageDivHolder rounded" style = "background-image: url('../images/<?=$row['imageName']?>');"></div>
							</div>
							<div class="randFoodWords float-right">
								<p><?php echo $row['title'];?></p>
							</div>
							<div class="randFoodWords">
								<p>Recipe of the Day:</p>
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>	
	</div>
	<div id="featuredItems" class="mt-3">
		<h1 class="ml-3">Featured Items</h1>
		<div class="resultsContainer">
			<div class="container-fluid">
				<div class="row" id="outputs">
					<?php 
						$row = $results_registered->fetch_assoc();
				        while ($row = mysqli_fetch_assoc($results_registered)) {  ?>
				        	<div class = "recipeBox col-6 col-md-4 col-lg-3">
								<a class="recipeTitle" href="details.php?id=<?php echo $row['id']; ?>">
								<div class="imageDivHolder rounded" style = "background-image: url('../images/<?=$row['imageName']?>');"> </div>								
								<div class="col-12 text-center font-weight-bold"><?php echo $row['title']; ?></div>
								</a>
							</div>
				    <?php } ?>

				</div>
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