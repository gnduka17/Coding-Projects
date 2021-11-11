<?php
session_start();
if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {
// 	$host = "303.itpwebdev.com";
// 	$user = "nduka_recipe_user";
// 	$password = "uscItp2021";
// 	$db = "nduka_recipe_db";

// // DB Connection
// 	$mysqli = new mysqli($host, $user, $password, $db);
// 	if ( $mysqli->connect_errno ) {
// 		echo $mysqli->connect_error;
// 		exit();
// 	}
// 	$sql = "SELECT id, title, imageName FROM recipes WHERE users_id = '" . $_SESSION['id'] . "';";
// 	$results = $mysqli->query($sql);
// 	if(!$results) {
// 		exit();
// 	}
// 	$mysqli->close();
// 	$results_array = [];

// 	while( $row = $results->fetch_assoc()) {
// 		array_push($results_array, $row);
// 	}

// 	echo json_encode($results_array);
	
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
	<title>Foodzine | Profile</title>
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
		.removeBut{
			width: 30px;

		}
		.editBut{
			width: 30px;

		}
		/*.editBut{*/
			/*display: inline-block;*/
		/*}*/
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
		.recipeTitle{
			text-decoration: none !important;
			color: black;
		}
		.imageDivHolder{
			background-repeat: no-repeat;
			background-size: cover;
			padding-bottom: 90%;
			background-position: center;
		}
		.recipeBox{
			margin-bottom: 30px;
		}
		#outputs{
			margin-right: 5px;
			margin-left: 5px;
		}
		.active{
			color:black;
		}
		#profPage{
			background-color: white;
			width: 80%;
			margin-right: auto;
			margin-left: auto;
			border-radius: 15px 50px;
		}
		#name{
			font-size: 30px;
			font-weight: bold;
			text-align: center;
		}
		#bottomSec{
			background-color: orange;
			width: 200px;
			margin-left: auto;
			margin-right: auto;
		}
		#addSymbol a{
			font-size: 50px;
			width: 30px;
			position: relative;
			bottom: 80px;
			right: 50px;
			color: #488286;
			text-decoration: none;
		}
		#addSymbol a:hover{
			text-decoration: none;
			color: white;
		}

		#symbolWords{
			font-size: 15px;
			position: relative;
			bottom: 70px;
			left: 23px;
			/*background-color: red;*/
			/*color: */
			display: none;
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
		.box{
			width: 410px;
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
		#profPage{
			margin-top: 25px;
			margin-bottom: 25px;
		}
		/* Medium devices (tablets, 768px and up)*/
		@media (min-width: 768px){
			#profPage{
				background-color: white;
				width: 80%;
				margin-right: auto;
				margin-left: auto;
			}
			#name{
			font-size: 50px;
			font-weight: bold;
			text-align: center;
			}
		
		}

		/* Large devices (desktops, 992px and up)*/
		@media (min-width: 992px){
			#profPage{
				background-color: white;
				width: 80%;
				margin-right: auto;
				margin-left: auto;
			}
			.nav-item{
				margin-right: 20px;
			}
			#name{
				font-size: 60px;
				font-weight: bold;
				text-align: center;
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
		}
	</style>
</head>
<body>
	<div class="clearfix">
		<nav class="navbar navbar-expand-lg pt-0 mt-0 bg-warning">
			<a class = "logo ml-2" href="index.php">Foodzine</a>
			<div id="hide" class="searchBox ml-4">
				<form action="results.php" method="GET" class=" search-form">
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
					<form action="results.php" method="GET" class=" search-form">
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
	
	<div id= "profPage" class="bg-warning overflow-hidden">
		<div id="name">
			<?php if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"] ): ?>
      			<?php
	      			echo $_SESSION["fname"] . " " . $_SESSION["lname"];
      			?>
			<?php endif;?>
		</div>
		<p class="text-center">
			<?php if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"] ): ?>
      			<?php
	      			echo "@" . $_SESSION["username"];
      			?>
			<?php endif;?>
		</p>

		<!-- <p class="text-center">email</p> -->
		<div class = "float-right" id="addSymbol" title=""><a href="addRecipe.php">+</a></div>
		<div class = "float-right" id="symbolWords">Add recipe</div>
		<div id="bottomSec" class="bg-warning">
			<div id = "switch" class="btn-group ml-auto bg-warning" role="group" aria-label="Basic example">
			  <button id="favButton" type="button" class="btn btn-secondary">Favorites</button>
			  <button id = "myReciBut" type="button" class="btn btn-secondary">My Recipes</button>
			</div>
		</div>
		<div class="mt-4" id="gallery">
			<div class="container-fluid">
			<div class="row" id="outputs">
			</div>
		</div>
			
		</div>
		
	</div>
	<div class="bg-warning" id="footer">
		Website created by Gloria Nduka | May 2021
	</div>
	<!-- This is the profile Page
	<br>
	<a href="addRecipe.php">Add a recipe page</a> -->
<script>
	document.querySelector("#addSymbol").onmouseover = function() {	
		document.querySelector("#symbolWords").style.display = "block";
	}
	document.querySelector("#addSymbol").onmouseleave = function() {	
		document.querySelector("#symbolWords").style.display = "none";
	}
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
	document.querySelector("#favButton").onclick = function(){
		ajaxGet("profileAjaxBackend.php?fav=true", function(results){
			console.log("info:");
			console.log(results);
			let jsResults = JSON.parse(results);
			let resultList = document.querySelector("#outputs");
			while(resultList.hasChildNodes()) {
				resultList.removeChild(resultList.lastChild);
			}
			for(let i = 0; i < jsResults.length; i++) {
				let outerDiv = document.createElement("div");
				outerDiv.classList.add("recipeBox");
				outerDiv.classList.add("col-12");
				outerDiv.classList.add("col-md-4");
				outerDiv.classList.add("col-lg-3");
				let anch = document.createElement("a");
				anch.classList.add("recipeTitle");
				anch.href = "details.php?id=" + jsResults[i].id;
				let innerDiv = document.createElement("div");
				innerDiv.classList.add("imageDivHolder");
				innerDiv.classList.add("rounded");
				innerDiv.style.backgroundImage = "url('../images/" + jsResults[i].imageName + "')";
				let remAnch = document.createElement("a");
				remAnch.href = "delete.php?fav=true&id=" + jsResults[i].id + "&title="+jsResults[i].title;
				let remImg = document.createElement("img");
				remImg.classList.add("removeBut");
				remImg.classList.add("float-right");
				remImg.alt = "remove Button";
				remImg.title = "remove";
				remImg.src = "../images/removeIcon.png";
				remImg.onclick = function(){
					return confirm('Are you sure you want to remove this recipe from your favorites?');
				};
				let divTitle = document.createElement("div");
				divTitle.classList.add("col-12");
				divTitle.classList.add("text-center");
				divTitle.classList.add("font-weight-bold");
				divTitle.innerHTML = jsResults[i].title;
				remAnch.appendChild(remImg);
				innerDiv.appendChild(remAnch);
				anch.appendChild(innerDiv);
				anch.appendChild(divTitle);
				outerDiv.appendChild(anch);
				document.querySelector("#outputs").appendChild(outerDiv);
			}
		});
	}
	document.querySelector("#myReciBut").onclick = function(){
		ajaxGet("profileAjaxBackend.php?fav=false", function(results){
			console.log("info reci:");
			console.log(results);
			let jsResults = JSON.parse(results);
			let resultList = document.querySelector("#outputs");
			while(resultList.hasChildNodes()) {
				resultList.removeChild(resultList.lastChild);
			}

			for(let i = 0; i < jsResults.length; i++) {
				let outerDiv = document.createElement("div");
				outerDiv.classList.add("recipeBox");
				outerDiv.classList.add("col-12");
				outerDiv.classList.add("col-md-4");
				outerDiv.classList.add("col-lg-3");
				let anch = document.createElement("a");
				anch.classList.add("recipeTitle");
				anch.href = "details.php?id=" + jsResults[i].id;
				let innerDiv = document.createElement("div");
				innerDiv.classList.add("imageDivHolder");
				innerDiv.classList.add("rounded");
				innerDiv.style.backgroundImage = "url('../images/" + jsResults[i].imageName + "')";
				let remAnch = document.createElement("a");
				remAnch.href = "delete.php?fav=false&id=" + jsResults[i].id + "&title="+jsResults[i].title;
				let remImg = document.createElement("img");
				remImg.classList.add("removeBut");
				remImg.alt = "remove Button";
				remImg.title = "remove";
				remImg.src = "../images/removeIcon.png";
				remImg.onclick = function(){
					return confirm('Are you sure you want to delete this recipe');

				};
				let editAnch = document.createElement("a");
				editAnch.href = "editRecipe.php?id=" + jsResults[i].id;
				let editImg = document.createElement("img");
				editImg.classList.add("editBut");
				editImg.classList.add("float-right");
				editImg.alt = "edit Button";
				editImg.title = "edit";
				editImg.src = "../images/editIcon.png";
				// editImg.onclick = function(){
				// 	return confirm('Are you sure you want to delete this recipe');

				// };
				let divTitle = document.createElement("div");
				divTitle.classList.add("col-12");
				divTitle.classList.add("text-center");
				divTitle.classList.add("font-weight-bold");
				divTitle.innerHTML = jsResults[i].title;
				editAnch.appendChild(editImg);
				remAnch.appendChild(remImg);
				innerDiv.appendChild(remAnch);
				innerDiv.appendChild(editAnch);
				anch.appendChild(innerDiv);
				anch.appendChild(divTitle);
				outerDiv.appendChild(anch);
				document.querySelector("#outputs").appendChild(outerDiv);
			}
		});

	}
</script>
</body>
</html>