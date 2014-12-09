<!DOCTYPE html>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="style221.css" />
	<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,300,600,700' rel='stylesheet' type='text/css'>
	<title>RPSRview</title>
	<meta charset="UTF-8" />
</head>
<body>
	<div class="header">
		<h1><a href="https://cormacbrady.info/~tkek"><strong>RPSR</strong>view</a></h1>
		<p>database of reserves</p>
		<a class="gitlogo" href="https://github.com/cob16/cs-221-group-project"> <img src="https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png" height="45" width="45"></a>
	</div>

	
	<div class="container">

		<?php
		$reserve_name = $_GET["reserve"];
		print($reserve_name);
		$host = 'localhost';
		$user = 'tkek';
		$pass = 'topkek3';
		$database = 'cb-group-project';
		$conn = new mysqli($host, $user, $pass, $database);
		if(!$conn->connect_errno > 0){
			$res = <<<SQL
			SELECT *
			FROM `Recordings`
SQL;
			$dopefish = <<<SQL
			SELECT *
			FROM `Species`
SQL;
	

			$fishsticks = <<<SQL
			SELECT *
			FROM `Reserves`
			
SQL;

		

			
			if(!$res = $conn->query($res)){
				die('There was an error running the query :)');
		}

			if(!$fishsticks = $conn->query($fishsticks)) {
				die('there was an error running the query :(');
			}

			if(!$dopefish = $conn->query($dopefish)) {
				die('there was an error running the query :(');
			}

		

		echo '<table cellpadding="25">';
		echo '<thead>';
		echo '<tr>';
		echo '<th>Species</th>';
		echo '<th>DAFOR Scale</th>';
		echo '<th>Comments</th>';
		echo '<th>Date Recorded</th>';
		echo '<th>Location Photo</th>';
		echo '<th>Species Photo</th>';
		echo '<th>Email</th>';
		echo '<th>Reserve Name</th>';
		echo '</tr>';
		while ($a = $res->fetch_assoc()) {
		echo '<tr>';
		$specNumber = $a["species"];
		while ($c = $dopefish->fetch_assoc()) {
			if ($specNumber == $c["species_id"]) {
				echo '<td>' . $c["Species"] . '</td>';

			}
		}
		echo '<td>' . $a["DAFOR"] . '</td>';
		echo '<td>' . $a["comments"] . '</td>';
		echo '<td>' . $a["date_recorded"] . '</td>';
		echo '<td><img src="' . $a["photo_path_general"] . '"></td>';
		echo '<td><img src="' . $a["photo_path_species"] . '"</td>';
		echo '<td>' . $a["Email"] . '</td>';
		$resNumber = $a["reserve_name"];
		while ($b = $fishsticks->fetch_assoc()) {
			if ($resNumber == $b["reserve_ID"]) {
				echo '<td>' . $b["reserve_name"] . '</td>';

			}
		}
		echo '</tr>';
	}
	echo '</table>';
} 
?>


</div>
</body>
</html>
