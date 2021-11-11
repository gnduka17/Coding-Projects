<?php
$host = "303.itpwebdev.com";
$user = "nduka_recipe_user";
$password = "uscItp2021";
$db = "nduka_recipe_db";

session_start();
if( !isset($_SESSION["logged_in"]) || !$_SESSION["logged_in"] ) {
	if ( isset($_POST['username']) && isset($_POST['password']) ) {
		if ( empty($_POST['username']) || empty($_POST['password']) ) {
			$error = "Please enter username and password.";
		}
		else {
			$mysqli = new mysqli($host, $user, $password, $db);

			if($mysqli->connect_errno) {
				echo $mysqli->connect_error;
				exit();
			}
			$username = $_POST['username'];
			$password = $_POST['password'];
			$passwordInput = hash("sha256", $password);
			// echo $passwordInput;
			$sql_registered = "SELECT firstName, lastName, id FROM users WHERE username = '" . $username . "' AND password = '" . $passwordInput . "';";


			$results_registered = $mysqli->query($sql_registered);
			if(!$results_registered) {
				exit();
			}

			

			if($results_registered->num_rows > 0) {
				$_SESSION["username"] = $username; 
				$_SESSION["logged_in"] = true;
				while($row = $results_registered->fetch_assoc()){
					$_SESSION["fname"] = $row['firstName'];
					$_SESSION["lname"] = $row['lastName'];
					$_SESSION["id"] = $row['id'];
				}
				// Redirect logged in user to the home page (using relative path)
				header("Location: index.php");
			}
			else {
				$error = "Invalid username or password.";
			}

			// $statement = $mysqli->prepare("SELECT * FROM users WHERE username = ? AND password = ?");
			// $statement->bind_param("ss", $username, $passwordInput);
			// $executed = $statement->execute();
			// if(!$executed) {
			// 	echo $mysqli->error;
			// 	echo "is there an errorr%%%%%%%%%%%%%%%";
			// 	exit();
			// }
			// // $results = $statement->get_results();
			// echo "Affected rows: " . $mysqli->affected_rows;
			
			// // var_dump($results);
			// echo "hiiiiiiiiiii";
			// // Check that only one row was affected
			// if($mysqli->affected_rows >=0) {
			// 	$isDeleted = true;
			// 	echo "YOU LOGGED INNNNNNN";
			// 	$_SESSION["username"] = $username;
			// 	$_SESSION["logged_in"] = true;

			// 	// Redirect logged in user to the home page (using relative path)
			// 	header("Location: index.php");
			// }
			// else{
			// 	$error = "Invalid username or password.";
			// }

			// $statement->close();

			$mysqli->close();
		} 
	}
}
else {
	header("Location: index.php");
}

?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Foodzine | Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="stylesheet.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<style>
		/*Default is phone size*/
		.container{
			margin-top: 200px;
			background-color: #FFFFFF;
			box-shadow: 3px 3px 3px 3px #E9E9E9;
		}
		body{
			/*background-color: #EDEEF8;*/
			padding: 10px;
		}
		h3{
			font-family: "Roboto_Light";

		}
		.logo, .logo:hover{
		   text-decoration: none;
			color: #488286;
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
	<a class = "logo" href="index.php">Foodzine</a>
	<div class = "container shadow-lg p-3 mb-5 bg-white rounded">
		<form id="loginForm" action="login.php" method="POST">
			<h3 class="text-center">Login</h3>
			<div class="errorMess">
				<?php
						if ( isset($error) && !empty($error) ) {
							echo $error;
						}
					?>
			</div>
		  <div class="form-group row">
		    <div class = "col-sm-12">
		    	<input type="text" class="form-control" id="usernameID" name="username" placeholder="Username">
		    </div>
		  </div>
		  <div class="form-group row">
		  	<div class=" col-sm-12">
		    	<input type="password" class="form-control" id="passwordID" name = "password" placeholder="Password">
		    </div>
		  </div>
		<!--   <input type="hidden" name="fname" value="<?php echo $_GET['track_id'];?>" />
		  <input type="hidden" name="lname" value="<?php echo $_GET['track_id'];?>" /> -->
		  <div class = "form-group row">
		  	<button type="submit" class="btn btn-block">Login</button>
		  </div>
		  <div class="clearfix"> 
		  	<a class= "float-right aLink" href="register.php">Create an Account</a>
		  </div>
					  
		</form>
		
	</div>
<script>
	document.querySelector("#loginForm").onsubmit = function(){
		let username = document.querySelector("#usernameID").value.trim();
		let password = document.querySelector("#passwordID").value.trim();

		if(username.length > 0 && password.length > 0){
			document.querySelector(".errorMess").innerHTML = "";
		}
		else{
			document.querySelector(".errorMess").innerHTML = "Error: Please dont leave anything blank";
			return false;
		}
	}
</script>	

</body>
</html>