<?php
session_start();
if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {
	if( !isset($_GET["id"]) || empty($_GET["id"]) ) {
		echo "Invalid recipe ID";
		// exit();
	}
$host = "303.itpwebdev.com";
$user = "nduka_recipe_user";
$password = "uscItp2021";
$db = "nduka_recipe_db";
$mysqli = new mysqli($host, $user, $password, $db);
if($mysqli->connect_errno) {
	echo $mysqli->connect_error;
	exit();
}
$sql = "SELECT * FROM recipes WHERE id = '" . $_GET['id'] . "';";

$results = $mysqli->query($sql);
if(!$results) {
	echo $mysqli->error;
	exit();
}
$row = $results->fetch_assoc();







	
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
	<title>Foodzine | Edit Recipe</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="stylesheet.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
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
		.aLink{
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
		
		<form id = "editRecipeForm" enctype = "multipart/form-data" action="editConfirm.php" method="POST">
			<div class="clearfix"> 
			  	<a class= "float-left aLink" href="profile.php">Cancel</a>
			  	<button type="reset" class="btn btn-light float-right">Reset</button>
			 </div>
			<h3 class="text-center">Edit your Recipe</h3>
			<div class="errorMess"></div>
		  <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="text" class="form-control" id="recipeTitle" name="recipeTitle" placeholder="Title of Recipe" value="<?php echo $row['title']; ?>" required>
		    </div>
		  </div>
		   <div class="form-group row">
		    <div class = "col-sm-12">
		    	<select required id="cuisine" name="cuisine" class="custom-select form-control">
		    				<option value = "">-- Select Cuisine --</option>
							<option value="African" <?=$row['cuisine'] == 'African' ? ' selected="selected"' : '';?>>African</option>
							<option value="American" <?=$row['cuisine'] == 'American' ? ' selected="selected"' : '';?>>American</option>
							<option value="Australian" <?=$row['cuisine'] == 'Australian' ? ' selected="selected"' : '';?>>Australian</option>
							<option value="Cajun" <?=$row['cuisine'] == 'Cajun' ? ' selected="selected"' : '';?>>Cajun</option>
							<option value="Caribbean" <?=$row['cuisine'] == 'Caribbean' ? ' selected="selected"' : '';?>>Caribbean</option>
							<option value="Chinese" <?=$row['cuisine'] == 'Chinese' ? ' selected="selected"' : '';?>>Chinese</option>
							<option value="English" <?=$row['cuisine'] == 'English' ? ' selected="selected"' : '';?>>English</option>
							<option value="European" <?=$row['cuisine'] == 'European' ? ' selected="selected"' : '';?>>European</option>
							<option value="Filipino" <?=$row['cuisine'] == 'Filipino' ? ' selected="selected"' : '';?>>Filipino</option>
							<option value="German" <?=$row['cuisine'] == 'German' ? ' selected="selected"' : '';?>>German</option>
							<option value="Greek" <?=$row['cuisine'] == 'Greek' ? ' selected="selected"' : '';?>>Greek</option>
							<option value="Hawaiian" <?=$row['cuisine'] == 'Hawaiian' ? ' selected="selected"' : '';?>>Hawaiian</option>
							<option value="Indian" <?=$row['cuisine'] == 'Indian' ? ' selected="selected"' : '';?>>Indian</option>
							<option value="Italian" <?=$row['cuisine'] == 'Italian' ? ' selected="selected"' : '';?>>Italian</option>
							<option value="Jamaican" <?=$row['cuisine'] == 'Jamaican' ? ' selected="selected"' : '';?>>Jamaican</option>
							<option value="Jewish" <?=$row['cuisine'] == 'Jewish' ? ' selected="selected"' : '';?>>Jewish</option>
							<option value="Korean" <?=$row['cuisine'] == 'Korean' ? ' selected="selected"' : '';?>>Korean</option>
							<option value="Mediterranean" <?=$row['cuisine'] == 'Mediterranean' ? ' selected="selected"' : '';?>>Mediterranean</option>
							<option value="Mexican" <?=$row['cuisine'] == 'Mexican' ? ' selected="selected"' : '';?>>Mexican</option>
							<option value="Middle Eastern" <?=$row['cuisine'] == 'Middle Eastern' ? ' selected="selected"' : '';?>>Middle Eastern</option>
							<option value="Native American" <?=$row['cuisine'] == 'Native American' ? ' selected="selected"' : '';?>>Native American</option>
							<option value="Russian" <?=$row['cuisine'] == 'Russian' ? ' selected="selected"' : '';?>>Russian</option>
							<option value="Soul" <?=$row['cuisine'] == 'Soul' ? ' selected="selected"' : '';?>>Soul Food</option>
							<option value="South American" <?=$row['cuisine'] == 'South American' ? ' selected="selected"' : '';?>>South American</option>
							<option value="Spanish" <?=$row['cuisine'] == 'Spanish' ? ' selected="selected"' : '';?>>Spanish</option>
							<option value="Thai" <?=$row['cuisine'] == 'Thai' ? ' selected="selected"' : '';?>>Thai</option>
							<option value="Turkish" <?=$row['cuisine'] == 'Turkish' ? ' selected="selected"' : '';?>>Turkish</option>
							<option value="Ukrainian" <?=$row['cuisine'] == 'Ukrainian' ? ' selected="selected"' : '';?>>Ukrainian</option>
							<option value="Vietnamese"<?=$row['cuisine'] == 'Vietnamese' ? ' selected="selected"' : '';?>>Vietnamese</option>
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
		    	<input type="number" class="form-control" id="servings" name="servings" value="<?php echo $row['servings']; ?>" placeholder="# of Servings" min=0 required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="number" class="form-control" id="preptime" name="preptime" value="<?php echo $row['prepTime']; ?>" placeholder="Prep Time(minutes)" min=0 required>
		    </div>
		  </div>
		  <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="number" class="form-control" id="cooktime" name="cooktime" value="<?php echo $row['cookTime']; ?>" placeholder="Cook Time(minutes)" min=0 required>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<div class="col-sm-12">
		  		<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input type="radio" name="difficulty" class="form-check-input mr-2" value="easy"  <?php echo ($row['difficulty']=='easy')?'checked':'' ?> required>
							Easy
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label ml-2">
							<input type="radio" name="difficulty" class="form-check-input mr-2" value="moderate" <?php echo ($row['difficulty']=='moderate')?'checked':'' ?>>
							Moderate
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label ml-2">
							<input type="radio" name="difficulty" class="form-check-input mr-2" value="hard" <?php echo ($row['difficulty']=='hard')?'checked':'' ?>>
							Difficult
						</label>
					</div> 
		  		
		  		</div>
		  </div>
		  <div class="form-group row">
		  	<div class=" col-sm-12">
		    	<textarea class="form-control" id="ingredients" name = "ingredients" rows="5" placeholder="Type in Ingredients&#10;&#10;Example:&#10;1 cup of sugar&#10;3 eggs..." required><?php echo $row['ingredients']; ?></textarea>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<div class=" col-sm-12">
		    	<textarea class="form-control" id="directions" name = "directions" rows="6" placeholder="Directions&#10;&#10;Example:&#10;Pour in flour and butter in a bowl. Mix the eggs.Pour sugar in eggs..." required><?php echo $row['directions']; ?></textarea>
		    </div>
		  </div>
		  <div class="form-group row">
		  	<div class=" col-sm-12">
		    	<div class="custom-file">
				    <input type="file" class="custom-file-input" name = "image" id="customFile">
				    <label class="custom-file-label" for="customFile">Upload New Photo of Dish: jpg, jpeg, png ONLY</label>
				 </div>
		    </div>
		  </div>
		  <input type="hidden" name="id" value="<?php echo $_GET['id'];?>" />
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
	document.querySelector("#editRecipeForm").onsubmit = function(){
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