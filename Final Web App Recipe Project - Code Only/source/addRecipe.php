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
	<title>Foodzine | Add Recipe</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="stylesheet.css"/>
	<style>
		/*Default is phone size*/
		body,html{
			margin: 0;
			padding: 0;
		}
		.container{
			margin-top: 15px;
			background-color: #FFFFFF;
			box-shadow: 3px 3px 3px 3px #E9E9E9;
		}
		body{
			background-color: orange;
		}
		.logo{
			font-size: 40px;
			text-decoration: none !important;
		}
		.logo:hover{
			font-size: 40px;
		   text-decoration: none;
			color: #488286;
		}
		.custom-toggler .navbar-toggler-icon {
			background-image: url("data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 32 32' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='rgba(72,130,134, 0.5)' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 8h24M4 16h24M4 24h24'/%3E%3C/svg%3E");
		}
		.custom-toggler.navbar-toggler {
			border-color: rgb(72,130,134);
		} 
		.aLink{
			color: #488286;
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
		
		#servings, #preptime,#cooktime{
			width: 39%;
		}
		h3{
			font-family: "Roboto_Light";

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
		
		#hide{
			display: none;
		}
		#footer{
			padding-top: 3px;
			padding-bottom: 3px; 
			text-align: center;
		}
		
		
		/* Medium devices (tablets, 768px and up)*/
		@media (min-width: 768px){
			/*.nav-item{
				margin-right: 20px;
			}*/
			/*#collapsibleNavbar{
				margin-left: auto;
				max-width: 430px;
			}*/
			.container{
				width: 500px;
			}
			.logo{
				font-size: 40px;
				/*margin-right: 80px;*/
				text-decoration: none !important;
			}
		}

		/* Large devices (desktops, 992px and up)*/
		@media (min-width: 992px){
			/*body{
				background-color: purple;
			}*/
			.nav-item{
				margin-right: 20px;
			}
			#collapsibleNavbar{
				margin-left: auto;
				max-width: 430px;
			}
			.container{
				width: 600px;
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
	<!-- <div class="searchBox">
		<form action="" method="" class="" id="search-form">
	          <div class="box">
				<i class="fa fa-search" aria-hidden="true"></i>
				<input type="text" name="input" class="form-control" id="search-id" placeholder="Search...">
			  </div>
	    </form>	
	</div> -->
	<div class = "container shadow-lg p-3 mb-5 bg-white rounded">
		<form id = "addRecipeForm" enctype = "multipart/form-data" action="addConfirm.php" method="POST">
			<div class="clearfix"> 
			  	<a class= "float-left aLink" href="profile.php">Cancel</a>
			  	<button type="reset" class="btn btn-light float-right">Reset</button>
			</div>
			<h3 class="text-center">Submit your Recipe!</h3>
			<p class="text-center">Share with us a recipe you'd like for us to try out!</p>
			<div class="errorMess"></div>
		  <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="text" class="form-control" id="recipeTitle" name="recipeTitle" placeholder="Title of Recipe" required>
		    </div>
		  </div>
		   <div class="form-group row">
		    <div class = "col-sm-12">
		    	<select required id="cuisine" name="cuisine" class="custom-select form-control">
		    				<option value = "">-- Select Cuisine --</option>
							<option value="African">African</option>
							<option value="American">American</option>
							<option value="Australian">Australian</option>
							<option value="Cajun">Cajun</option>
							<option value="Caribbean">Caribbean</option>
							<option value="Chinese">Chinese</option>
							<option value="English">English</option>
							<option value="European">European</option>
							<option value="Filipino">Filipino</option>
							<option value="German">German</option>
							<option value="Greek">Greek</option>
							<option value="Hawaiian">Hawaiian</option>
							<option value="Indian">Indian</option>
							<option value="Italian">Italian</option>
							<option value="Jamaican">Jamaican</option>
							<option value="Jewish">Jewish</option>
							<option value="Korean">Korean</option>
							<option value="Mediterranean">Mediterranean</option>
							<option value="Mexican">Mexican</option>
							<option value="Middle Eastern">Middle Eastern</option>
							<option value="Native American">Native American</option>
							<option value="Russian">Russian</option>
							<option value="Soul">Soul Food</option>
							<option value="South American">South American</option>
							<option value="Spanish">Spanish</option>
							<option value="Thai">Thai</option>
							<option value="Turkish">Turkish</option>
							<option value="Ukrainian">Ukrainian</option>
							<option value="Vietnamese">Vietnamese</option>
						</select>
		    </div>
		  </div>
		  <!-- <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="text" class="form-control" id="otherCuisine" name="otherCuisine" placeholder="Other Cuisine">
		    </div>
		  </div> -->
		   <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="number" class="form-control" id="servings" name="servings" placeholder="# of Servings" min=0 required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="number" class="form-control" id="preptime" name="preptime" placeholder="Prep Time(minutes)" min=0 required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="number" class="form-control" id="cooktime" name="cooktime" placeholder="Cook Time(minutes)" min=0 required>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<div class="col-sm-12">
		  		<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input type="radio" name="difficulty" class="form-check-input mr-2" value="easy" required>
							Easy
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label ml-2">
							<input type="radio" name="difficulty" class="form-check-input mr-2" value="moderate">
							Moderate
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label ml-2">
							<input type="radio" name="difficulty" class="form-check-input mr-2" value="hard">
							Difficult
						</label>
					</div> 
		  		
		  	</div>
		    
		  </div>
		  <div class="form-group row">
		  	<div class=" col-sm-12">
		    	<textarea class="form-control" id="ingredients" name = "ingredients" rows="5" placeholder="Type in Ingredients&#10;&#10;Example:&#10;1 cup of sugar&#10;3 eggs..." required></textarea>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<div class=" col-sm-12">
		    	<textarea class="form-control" id="directions" name = "directions" rows="6" placeholder="Directions&#10;&#10;Example:&#10;Pour in flour and butter in a bowl&#10;Mix the eggs&#10;Pour sugar in eggs..." required></textarea>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<div class=" col-sm-12">
		    	<div class="custom-file">
				    <input type="file" class="custom-file-input" name = "image" id="customFile" required>
				    <label class="custom-file-label" for="customFile">Upload Photo of Dish: jpg, jpeg, png ONLY</label>
				  </div>
		    </div>
		  </div>
		  <div class = "form-group row">
		  	<button type="submit" name="submit" class="btn btn-block">Submit My Recipe!</button>
		  </div>
					  
		</form>
		
	</div>
	<div class="bg-warning" id="footer">
		Website created by Gloria Nduka | May 2021
	</div>
<script
  src="http://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script>
	$(".custom-file-input").on("change", function(event) {
		event.preventDefault();
	  var fileName = $(this).val().split("\\").pop();
	  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	});
	document.querySelector("#addRecipeForm").onsubmit = function(){
		let title = document.querySelector("#recipeTitle").value.trim();
		let servings = document.querySelector("#servings").value.trim();
		let preptime = document.querySelector("#preptime").value.trim();
		let cooktime = document.querySelector("#cooktime").value.trim();
		let ingredients = document.querySelector("#ingredients").value.trim();
		let directions = document.querySelector("#directions").value.trim();

		if(title.length > 0 && servings.length > 0 && preptime.length > 0 && cooktime.length > 0 && ingredients.length > 0 && directions.length > 0){
			document.querySelector(".errorMess").innerHTML = "";
		}
		else{
			document.querySelector(".errorMess").innerHTML = "Error: Please dont leave anything blank";
			return false;

		}
	}
	document.querySelector(".search-form").onsubmit = function(){
		let input = document.querySelector(".search-id").value.trim();
		if(input.length==0){
			return false;
		}
	}
</script>
</body>
</html>