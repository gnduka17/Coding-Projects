<?php
session_start();
if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {
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
	if($_GET['fav']=="true"){
		$sql = "SELECT recipes.id, title, imageName 
				FROM recipes
				JOIN favorites
				ON recipes.id = favorites.recipeID 
				WHERE favorites.users_id = '" . $_SESSION['id'] . "';";
	}
	else{
		$sql = "SELECT id, title, imageName FROM recipes WHERE users_id = '" . $_SESSION['id'] . "';";
	}
	$results = $mysqli->query($sql);
	if(!$results) {
		exit();
	}
	$mysqli->close();
	$results_array = [];

	while( $row = $results->fetch_assoc()) {
		array_push($results_array, $row);
	}

	echo json_encode($results_array);
	
}
else{
	header("Location: index.php");
}

?>