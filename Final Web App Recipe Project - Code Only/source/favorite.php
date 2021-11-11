<?php
session_start();
if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {
	if ( !isset($_GET['recID']) || empty($_GET['recID']) ) {
		$error = "Invalid Recipe ID.";
	} 
	else{
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
		$statement_ = $mysqli->prepare("INSERT INTO favorites (userID, recipeID, users_id) VALUES (?, ?, ?)");
		$statement_->bind_param("iii", $_SESSION['id'], $_GET['recID'], $_SESSION['id']);

		$executed_ = $statement_->execute();
		// check for errors
		if(!$executed_) {
			echo $mysqli->error;
		}

		if($statement_->affected_rows == 1) {
			$isInserted = true;
		}
		$statement_->close();
		//when page is just displayed
			//figure out whether recipe is in favorites or not
			//if in favorites, dont display button
			//if not in favorites, display button

		//when clicking

		$mysqli->close();
		// $results_array = [];

		// while( $row = $results->fetch_assoc()) {
		// 	array_push($results_array, $row);
		// }

		// echo json_encode($results_array);
		echo "done";

	}
	
}
else{
	header("Location: index.php");
}

?>