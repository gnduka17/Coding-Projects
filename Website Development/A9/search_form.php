<?php
$host = "303.itpwebdev.com";
$user = "nduka_dvd_user";
$password = "uscItp2021";
$db = "nduka_dvd_db";

$mysqli = new mysqli($host, $user, $password, $db);

if($mysqli->connect_errno){
	//there is error
	echo $mysqli->connect_error;
	exit();
}
$sql = "SELECT * FROM genres";
$sql_rate = "SELECT * FROM ratings";
$sql_label = "SELECT * FROM labels";
$sql_format = "SELECT * FROM formats";
$sql_sound = "SELECT * FROM sounds";
$results = $mysqli->query($sql);
if(!$results){
	echo $mysqli->error;
	exit();
}
$results_rate = $mysqli->query($sql_rate);
if(!$results_rate){
	echo $mysqli->error;
	exit();
}
$results_label = $mysqli->query($sql_label);
if(!$results_label){
	echo $mysqli->error;
	exit();
}
$results_format = $mysqli->query($sql_format);
if(!$results_format){
	echo $mysqli->error;
	exit();
}
$results_sound = $mysqli->query($sql_sound);
if(!$results_sound){
	echo $mysqli->error;
	exit();
}
$mysqli->close();




?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>DVD Search Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<style>
		.form-check-label {
			padding-top: calc(.5rem - 1px * 2);
			padding-bottom: calc(.5rem - 1px * 2);
			margin-bottom: 0;
		}
	</style>
</head>
<body>
	<ol class="breadcrumb">
		<li class="breadcrumb-item active">Search</li>
	</ol>
	<div class="container">
		<div class="row">
			<h1 class="col-12 mt-4 mb-4">DVD Search Form</h1>
		</div> <!-- .row -->
	</div> <!-- .container -->
	<div class="container">
		<form action="search_results.php" method="GET">
			<div class="form-group row">
				<label for="title-id" class="col-sm-3 col-form-label text-sm-right">DVD Title:</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="title-id" name="title">
				</div>
			</div> <!-- .form-group -->
			<div class="form-group row">
				<label for="genre-id" class="col-sm-3 col-form-label text-sm-right">Genre:</label>
				<div class="col-sm-9">
					<select name="genre" id="genre-id" class="form-control">
						<option value="" selected>-- All --</option>

						<!-- Genre dropdown options here -->
						<?php
						while($row = $results->fetch_assoc()) :?>
							<option value = "<?php echo $row['genre_id'];?>">
								<?php echo $row["genre"]; ?>
							</option>
						<?php endwhile;?>

					</select>
				</div>
			</div> <!-- .form-group -->
			<div class="form-group row">
				<label for="rating-id" class="col-sm-3 col-form-label text-sm-right">Rating:</label>
				<div class="col-sm-9">
					<select name="rating" id="rating-id" class="form-control">
						<option value="" selected>-- All --</option>

						<!-- Rating dropdown options here -->
						<?php
						while($row = $results_rate->fetch_assoc()) :?>
							<option value = "<?php echo $row['rating_id'];?>">
								<?php echo $row["rating"]; ?>
							</option>
						<?php endwhile;?>
					</select>
				</div>
			</div> <!-- .form-group -->
			<div class="form-group row">
				<label for="label-id" class="col-sm-3 col-form-label text-sm-right">Label:</label>
				<div class="col-sm-9">
					<select name="label" id="label-id" class="form-control">
						<option value="" selected>-- All --</option>

						<!-- Label dropdown options here -->
						<?php
						while($row = $results_label->fetch_assoc()) :?>
							<option value = "<?php echo $row['label_id'];?>">
								<?php echo $row["label"]; ?>
							</option>
						<?php endwhile;?>

					</select>
				</div>
			</div> <!-- .form-group -->
			<div class="form-group row">
				<label for="format-id" class="col-sm-3 col-form-label text-sm-right">Format:</label>
				<div class="col-sm-9">
					<select name="format" id="format-id" class="form-control">
						<option value="" selected>-- All --</option>

						<!-- Format dropdown options here -->
						<?php
						while($row = $results_format->fetch_assoc()) :?>
							<option value = "<?php echo $row['format_id'];?>">
								<?php echo $row["format"]; ?>
							</option>
						<?php endwhile;?>

					</select>
				</div>
			</div> <!-- .form-group -->
			<div class="form-group row">
				<label for="sound-id" class="col-sm-3 col-form-label text-sm-right">Sound:</label>
				<div class="col-sm-9">
					<select name="sound" id="sound-id" class="form-control">
						<option value="" selected>-- All --</option>

						<!-- Sound dropdown options here -->
						<?php
						while($row = $results_sound->fetch_assoc()) :?>
							<option value = "<?php echo $row['sound_id'];?>">
								<?php echo $row["sound"]; ?>
							</option>
						<?php endwhile;?>
						

					</select>
				</div>
			</div> <!-- .form-group -->
			<div class="form-group row">
				<label for="award-id" class="col-sm-3 col-form-label text-sm-right">Award:</label>
				<div class="col-sm-9">
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input class="form-check-input mr-2" type="radio" name="award" id="inlineCheckbox3" value="any" checked>Any
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input class="form-check-input mr-2" type="radio" name="award" id="inlineCheckbox1" value="yes">Yes
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input class="form-check-input mr-2" type="radio" name="award" id="inlineCheckbox2" value="no">No
						</label>
					</div>
				</div>
			</div> <!-- .form-group -->
			<div class="form-group row">
				<label for="date-id" class="col-sm-3 col-form-label text-sm-right">Release Date:</label>
				<div class="col-sm-9">
					<div class="row">
						<div class="col">
							<label class="form-check-label">
								From: 
								<input type="date" class="form-control mt-2" name="release_date_from">
							</label>
						</div> <!-- .col -->
						<div class="col">
							<label class="form-check-label">
								to: 
								<input type="date" class="form-control mt-2" name="release_date_to">
							</label>
						</div> <!-- .col -->
					</div> <!-- .row -->
				</div> <!-- .col -->
			</div> <!-- .form-group -->
			<div class="form-group row">
				<div class="col-sm-3"></div>
				<div class="col-sm-9 mt-2">
					<button type="submit" class="btn btn-primary">Search</button>
					<button type="reset" class="btn btn-light">Reset</button>
				</div>
			</div> <!-- .form-group -->
		</form>
	</div> <!-- .container -->
</body>
</html>