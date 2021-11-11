<?php
$host = "303.itpwebdev.com";
$user = "nduka_recipe_user";
$password = "uscItp2021";
$db = "nduka_recipe_db";
session_start();

$isUpdated = false;
// var_dump($_POST);
if( isset($_SESSION["logged_in"]) || $_SESSION["logged_in"]) {
	if( !isset($_POST["id"]) || empty($_POST["id"]) ) {
		echo "Invalid recipe ID";
		exit();
	}
	if (isset($_POST['submit']) && isset($_FILES['image'])) {
		if ( !isset($_POST['recipeTitle']) || empty($_POST['recipeTitle']) 
			|| !isset($_POST['cuisine']) || empty($_POST['cuisine'])
			|| !isset($_POST['servings']) || empty($_POST['servings'])
			|| !isset($_POST['preptime']) || empty($_POST['preptime'])
			|| !isset($_POST['difficulty']) || empty($_POST['difficulty'])
			|| !isset($_POST['ingredients']) || empty($_POST['ingredients'])
			|| !isset($_POST['directions']) || empty($_POST['directions'])
			|| !isset($_POST['cooktime']) || empty($_POST['cooktime']) ) {

			$error = "Please fill out all required fields.";

		} else {
				// if (isset($_POST['submit']) && isset($_FILES['image'])) {
					$mysqli = new mysqli($host, $user, $password, $db);
					if($mysqli->connect_errno) {
						echo $mysqli->connect_error;
						exit();
					}

					// echo "<pre>";
					// print_r($_FILES['image']);
					// echo "</pre>";

					$img_name = $_FILES['image']['name'];
					$img_size = $_FILES['image']['size'];
					$tmp_name = $_FILES['image']['tmp_name'];
					$errorNum = $_FILES['image']['error'];

					if ($errorNum === 0) {
						if ($img_size >5242880) {
							$error = "Sorry, your file is too large.";
							// echo "Sorry, your file is too large.";
						    // header("Location: addRecipe.php?error=$em");
						}else {
							$img_ex = pathinfo($img_name, PATHINFO_EXTENSION);
							$img_ex_lc = strtolower($img_ex);

							$allowed_exs = array("jpg", "jpeg", "png"); 

							if (in_array($img_ex_lc, $allowed_exs)) {
								$new_img_name = uniqid("IMG-", true).'.'.$img_ex_lc;
								$img_upload_path = '../images/'.$new_img_name;
								move_uploaded_file($tmp_name, $img_upload_path);

								$image = "";
								$userID = $_SESSION["id"];
								$title = $_POST['recipeTitle'];
								$cuisine = $_POST['cuisine'];
								$servings = $_POST['servings'];
								$preptime = $_POST['preptime'];
								$cooktime = $_POST['cooktime']; 
								$difficulty = $_POST['difficulty'];
								$ingredients = $_POST['ingredients'];
								$directions = $_POST['directions'];
								// $image = file_get_contents($_FILES['image']['tmp_name']);

								$statement = $mysqli->prepare("UPDATE recipes SET title = ?, cuisine = ?, servings = ?, ingredients = ?, directions = ?, imageName = ?, prepTime = ?, cookTime = ?, difficulty = ? WHERE id = ?");
								$statement->bind_param("ssisssiisi", $title, $cuisine, $servings, $ingredients, $directions, $new_img_name, $preptime, $cooktime, $difficulty, $_POST["id"]);
								$executed = $statement->execute();
								if(!$executed) {
									echo $mysqli->error;
								}
								$statement->close();
								if($mysqli->affected_rows == 1) {
									$isUpdated = true;
								}

							}else {
								$error = "You can't upload files of this type. Only png, jpg, jpeg accepted.";
								// echo "You can't upload files of this type";
							}
						}
					}else if ($errorNum==4) {
						$userID = $_SESSION["id"];
						$title = $_POST['recipeTitle'];
						$cuisine = $_POST['cuisine'];
						$servings = $_POST['servings'];
						$preptime = $_POST['preptime'];
						$cooktime = $_POST['cooktime']; 
						$difficulty = $_POST['difficulty'];
						$ingredients = $_POST['ingredients'];
						$directions = $_POST['directions'];

						$statement = $mysqli->prepare("UPDATE recipes SET title = ?, cuisine = ?, servings = ?, ingredients = ?, directions = ?, prepTime = ?, cookTime = ?, difficulty = ? WHERE id = ?");
						$statement->bind_param("ssissiisi", $title, $cuisine, $servings, $ingredients, $directions, $preptime, $cooktime, $difficulty, $_POST["id"]);
						$executed = $statement->execute();
						if(!$executed) {
							echo $mysqli->error;
						}
						$statement->close();
						if($mysqli->affected_rows == 1) {
							$isUpdated = true;
						}
						
					}
					else{
						$error = "there has been an error";
					}

					$mysqli->close();

				// }else {
					// header("Location: addRecipe.php");
				// }
				
		}
	}
	else{
		header("Location: addRecipe.php");
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
			<h3 class="text-center text-success"><?php echo $_POST['recipeTitle']; ?> was successfully edited!</h3>
		<?php endif; ?>
			
		<div class="row mt-4 mb-4">
			<div class="col-12 text-center">
				<a href="profile.php" role="button" class="btn">Go back to Profile Page</a>
				<a href="<?php echo $_SERVER['HTTP_REFERER']; ?>" role="button" class="btn btn-light">Back</a>
			</div> <!-- .col -->
		</div> <!-- .row -->
	</div>
	
</body>
</html>