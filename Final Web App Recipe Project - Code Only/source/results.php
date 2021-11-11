<?php
session_start();
if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {
	if(isset($_GET['bar']) || !empty($_GET['bar'])){
		$host = "303.itpwebdev.com";
		$user = "nduka_recipe_user";
		$password = "uscItp2021";
		$db = "nduka_recipe_db";

	// DB Connection
		$mysqli = new mysqli($host, $user, $password, $db);
		if ( $mysqli->connect_errno ) {
			echo $mysqli->connect_error;
			exit();
		}
		if($_GET['bar']== "true"){ //if true
			//server validate search bar
			if ( !isset($_GET['input']) || empty($_GET['input']) ) {
				$error = "Invalid Input";
				header("Location: index.php");
			} else {

				$arr = explode(" ",$_GET['input']);
				// for($i = 0; $i < sizeof($arr); $i++) {
				// 	echo $arr[$i] . ", ";
				// }

				$sql = "SELECT id, cuisine, title, imageName 
						FROM recipes 
						WHERE title LIKE '%" . $_GET['input'] . "%'";

				$sql = $sql . " OR cuisine LIKE '%" . $_GET['input']. "%'";
				$arr = explode(" ",$_GET['input']);
				for($i = 0; $i < sizeof($arr); $i++) {
					$sql = $sql . " OR title LIKE '%" . $arr[$i]. "%'";
					$sql = $sql . " OR cuisine LIKE '%" . $arr[$i]. "%'";
					// echo $arr[$i] . ", ";
				}
			}

		}
		else{ //if false
			if ( !isset($_GET['cuisine']) || empty($_GET['cuisine']) ) {
				$error = "Invalid Cuisine Name.";
			} else {
				if($_GET['cuisine']=="All"){
					$sql = "SELECT id, title, imageName FROM recipes;";


				}
				else{
					$sql = "SELECT id, title, imageName FROM recipes WHERE cuisine = '" . $_GET['cuisine'] . "';";
				}

			}
		}
		$results_registered = $mysqli->query($sql);
		if(!$results_registered) {
			exit();
		}


		//extract users favorites and put into array
		$sql_ = "SELECT recipeID FROM favorites WHERE users_id = '" . $_SESSION["id"] . "';";
		$results= $mysqli->query($sql_);
		if(!$results) {
			exit();
		}
		$storeArray = Array();
		if($results->num_rows > 0){
			while ($row = mysqli_fetch_assoc($results)) {
			    $storeArray[] =  $row['recipeID'];  
			}
		}
		$mysqli->close();
	}else{
		// echo "seems to be an error";
		header("Location: search.php");

	}
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
	<title>Foodzine | Results</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="stylesheet.css">
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<style>
		html,body{
			margin: 0;
			padding: 0;
		}
		body{
			background-color: orange;
		}
		#favoriteBut{
			width: 30px;
			margin-top: 5px;
			margin-right:5px;  
		}
		.logo{
			font-size: 40px;
			text-decoration: none !important;
		}
		.logo, .logo:hover{
		   text-decoration: none;
			color: #488286;
		}
		.recipeTitle{
			text-decoration: none !important;
			color: black;
		}
		.imageDivHolder{
			/*background-image: url('../images/jambalaya.jpeg');*/
			background-repeat: no-repeat;
			background-size: cover;
			padding-bottom: 90%;
			background-position: center;
			transition: all 1s;
		}
		.imageDivHolder:hover{
			transform: scale(1.06);
		}
		.custom-toggler .navbar-toggler-icon {
			background-image: url("data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 32 32' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='rgba(72,130,134, 0.5)' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 8h24M4 16h24M4 24h24'/%3E%3C/svg%3E");
		}
		.custom-toggler.navbar-toggler {
			border-color: rgb(72,130,134);
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
		.recipeBox{
			margin-bottom: 30px;
		}
		.recipeImg{
			background-color: orange;
			max-width: 100%;
			padding-bottom: 30%;
    		display: block;
		}
		#outputs{
			margin-right: 5px;
			margin-left: 5px;
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
			
			.recipeImg{
				background-color: orange;
				max-width: 100%;
				padding-bottom: 30%;
    			display: block;
			}
		
		}

		/* Large devices (desktops, 992px and up)*/
		@media (min-width: 992px){
			.logo{
				font-size: 40px;
				text-decoration: none !important;
			}
			.box{
				width: 430px;
			}
			.recipeImg{
				background-color: orange;
				max-width: 100%;
				padding-bottom: 30%;
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
		        		<a class="nav-link aLink" href="search.php">Search by Cuisine</a>
		      		</li>
		    		<li class="nav-item ml-auto">
		        		<a class="nav-link aLink active" href="profile.php">Profile</a>
		      		</li>
		      		<li class="nav-item ml-auto">
		        		<a class="nav-link aLink" href="logout.php">Logout</a>
		      		</li>
		    	</ul>
		    	<div class="searchBox">
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
		  	</div>
		</nav>
	</div>

	<div class="resultsContainer">
		<h6 class="col-12 mt-4">
			Showing <?php echo $results_registered->num_rows; ?> result(s). 
		</h6>
		<div class="container-fluid">
			<div class="row" id="outputs">
				<?php 
			          if (mysqli_num_rows($results_registered) > 0) {
			          	while ($row = mysqli_fetch_assoc($results_registered)) {  ?>
			          		<div class = "recipeBox col-6 col-md-4 col-lg-3">
								<a class="recipeTitle" href="details.php?id=<?php echo $row['id']; ?>">
									<div class="imageDivHolder rounded" style = "background-image: url('../images/<?=$row['imageName']?>');"> 
											<!-- if this recipe is not in favorites, display recipe -->
								<!-- </a> -->
											



												<?php if(in_array($row['id'],$storeArray)): ?>
													<input id = "recID" type="hidden" name="track_id" value="<?php echo $row['id'];?>" />

												<?php else: ?>
													<input id = "recID" type="hidden" name="track_id" value="<?php echo $row['id'];?>" />
													<!-- <a id="anchFav" onclick = "return favAjax();" href="favorite.php?recID=<?php echo $row['id']; ?>"> -->
														<img id = "favoriteBut" data-id = "<?php echo $row['id'];?>" class = "float-right imgClass" alt="favoriteBut" src="../images/favoriteButton.png">
														<!-- <img onclick = "this.style.display='none';return favAjax(<?php echo $row['id'];?>); " id = "favoriteBut" class = "float-right imgClass" alt="favoriteBut" src="../images/favoriteButton.png"> -->
													<!-- </a> -->
										
												<?php endif;?>
												
												
											
											<!-- <img id = "favoriteBut" class = "float-right" alt="favoriteBut" src="../images/favoriteButton.png"> -->
										<!-- </a> -->
									</div>
								<!-- <a class="recipeTitle" href="details.php?id=<?php echo $row['id']; ?>"> -->
									<div class="col-12 text-center font-weight-bold"><?php echo $row['title']?></div>
								</a>
							</div>
			    <?php } }?>
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
	function ajaxGet(endpointUrl, returnFunction){
		var xhr = new XMLHttpRequest();
		xhr.open('GET', endpointUrl, true);
		xhr.onreadystatechange = function(){
			if (xhr.readyState == XMLHttpRequest.DONE) {
				if (xhr.status == 200) {
					returnFunction( xhr.responseText );
				} else {
					alert('AJAX Error.');
					console.log(xhr.status);
				}
			}
		}
		xhr.send();
	};
	let cards = document.querySelectorAll(".imgClass");
	for( let i = 0; i < cards.length; i++ ) {
		cards[i].addEventListener("click", function(e) {
			e.preventDefault();
			this.style.display = "none";
			return favAjax(this.getAttribute("data-id"));
			// alert("Hey Don't click me " + this.getAttribute("data-id"));
			// return false;
		});
	}
	function favAjax(num){
		// alert("Hey Don't click me");
		
		// let recID = document.querySelector("#recID").value;
		// this.style.display="none";
		
		ajaxGet("favorite.php?recID="+ num, function(results){
			console.log("info:");
			console.log(results);
		// 	let jsResults = JSON.parse(results);
		return false;
		});
		return false;

	}
	// document.querySelector("#favoriteBut").onclick = function(){
	// 	let recID = document.querySelector("#recID").value;
	// 	console.log("I clicked heart: "+ recID);
	// 	ajaxGet("favorite.php?recID="+ recID, function(results){
		
	// 	});
	// }
</script>
</body>
</html>