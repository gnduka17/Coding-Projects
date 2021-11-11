<?php
$host = "303.itpwebdev.com";
$user = "nduka_recipe_user";
$password = "uscItp2021";
$db = "nduka_recipe_db";
session_start();

$isUpdated = false;
$isInserted = false;


// server side validation
if ( !isset($_POST['fname']) || empty($_POST['fname'])
	|| !isset($_POST['lname']) || empty($_POST['lname'])
	|| !isset($_POST['username']) || empty($_POST['username'])
	|| !isset($_POST['password']) || empty($_POST['password']) ) {
	$error = "Please fill out all required fields.";
}
else {
	// Connect to the database and insert a new user to the users table
	$mysqli = new mysqli($host, $user, $password, $db);
	if($mysqli->connect_errno) {
		echo $mysqli->connect_error;
		exit();
	}
	$username = $_POST['username'];
	$fname = $_POST['fname'];
	$lname = $_POST['lname'];
	$password = $_POST['password'];

	$sql_registered = "SELECT * FROM users
	WHERE username = '" . $username."';";


	$results_registered = $mysqli->query($sql_registered);
	if(!$results_registered) {
		exit();
	}

	if($results_registered->num_rows > 0) {
		$error = "Username has already been taken. Please choose another one.";
	}
	else {
		$password = hash("sha256", $password);
		$statement_ = $mysqli->prepare("INSERT INTO users(firstName, lastName, username, password) VALUES (?, ?, ?, ?)");
		$statement_->bind_param("ssss", $fname, $lname, $username, $password);
		
		$executed_ = $statement_->execute();
		// check for errors
		if(!$executed_) {
			echo $mysqli->error;
		}

		if($statement_->affected_rows == 1) {
			$isInserted = true;
		}
		else if($statement_->affected_rows > 1) {
			
		}
		$statement_->close();
		$mysqli->close();
	}

}
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Foodzine | Register</title>
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
			<h3 class="text-center text-success"><?php echo $_POST['username']; ?> was successfully registered.</h3>
		<?php endif; ?>
		<div class="row mt-4 mb-4">
			<div class="col-12 text-center">
				<a href="login.php" role="button" class="btn">Login</a>
				<a href="<?php echo $_SERVER['HTTP_REFERER']; ?>" role="button" class="btn btn-light">Back</a>
			</div> <!-- .col -->
		</div> <!-- .row -->
	</div>
</body>
</html>