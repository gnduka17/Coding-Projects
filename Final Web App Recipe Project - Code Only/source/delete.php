<?php
$host = "303.itpwebdev.com";
$user = "nduka_recipe_user";
$password = "uscItp2021";
$db = "nduka_recipe_db";
session_start();
$isDeleted = false;
if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {
	if(!isset($_GET["id"]) || empty($_GET["id"]) ) {
		// echo "Invalid recipe ID";
		$error = "Invalid recipe ID";
	}
	else if(!isset($_GET["fav"]) || empty($_GET["fav"]) ) {
		// echo "Invalid recipe ID";
		$error = "Invalid Favorite ID";
	}
	else{
		$mysqli = new mysqli($host, $user, $password, $db);
		if($mysqli->connect_errno) {
			// echo $mysqli->connect_error;
			exit();
		}
		if($_GET["fav"]=="true"){
			$statement = $mysqli->prepare("DELETE FROM favorites WHERE recipeID = ? AND users_id = ? ");
			$statement->bind_param("ii", $_GET["id"],$_SESSION["id"]);

		}else{
			$statement = $mysqli->prepare("DELETE FROM favorites WHERE recipeID = ?");
			$statement->bind_param("i", $_GET["id"]);
			$executed = $statement->execute();
			if(!$executed) {
				// echo $mysqli->error;
				exit();
			}
			$statement = $mysqli->prepare("DELETE FROM recipes WHERE id = ?");
			$statement->bind_param("i", $_GET["id"]);
		}

		$executed = $statement->execute();
		if(!$executed) {
			// echo $mysqli->error;
			exit();
		}
		// Check that only one row was affected
		if($statement->affected_rows == 1) {
			$isDeleted = true;
		}

		$statement->close();

		$mysqli->close();

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
	<title>Confirmation Page</title>
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
			color: white;
		}
		.aLink{
			color: #488286;
		}
		.errorMess{
			color: red;
		}
		.alb{
			width: 400px;
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
			/*body{
				background-color: purple;
			}*/
			.container{
				width: 600px;
			}

		}
	</style>
</head>
<body class="bg-warning">
	<a class = "logo" href="">Foodzine</a>
	<div class = "container  shadow-lg p-3 mb-5 rounded">
		<?php if ( isset($error) && !empty($error) ) : ?>
			<h3 class="text-center text-danger"><?php echo $error; ?></h3>
		<?php else : ?>
			<?php if($_GET["fav"]=="true"): ?>
				<h4 class="text-center text-success"><?php echo $_GET['title']; ?> was successfully removed from your Favorites!</h4>
			<?php else : ?>
				<h3 class="text-center text-success"><?php echo $_GET['title']; ?> was successfully deleted!</h3>
			<?php endif; ?>
		<?php endif; ?>
			
		<div class="row mt-4 mb-4">
			<div class="col-12 text-center">
				<a href="profile.php" role="button" class="btn">Go back to Profile Page</a>
			</div> <!-- .col -->
		</div> <!-- .row -->
	</div>
	
</body>
</html>