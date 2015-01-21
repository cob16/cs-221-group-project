<!DOCTYPE html>

<html>
<head>
		<link rel="stylesheet" type="text/css" href="style221.css" />
	<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,300,600,700' rel='stylesheet' type='text/css'>
	<title>RPSRview</title>
	<meta charset="UTF-8" />
</head>
<body>
		<?php
		/*
		 * Takes the reserve id passed to it
		 * anf looks it up in order to display
		 * a table of the first 25 results
		 */

		$reserve_name = (int) $_GET["reserve"];
		$host = 'localhost';
		$user = 'tkek';
		$pass = 'topkek3';
		$database = 'cb-group-project';
		$conn = new mysqli($host, $user, $pass, $database);


		if(!$conn->connect_errno > 0){
			$res = <<<SQL
			SELECT *
			FROM `Recordings`
			WHERE $reserve_name = reserve_name
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

		echo '<div class="header">';
			echo '<h1><a href="https://cormacbrady.info/~tkek"><strong>RPSR</strong>view</a></h1>';
			while ($b = $fishsticks->fetch_assoc()) {
						if ($reserve_name == $b["reserve_ID"]) {
							echo '<p>Reserve - ' . ucwords($b["reserve_name"]) . '</p>';
						}
					}		
			echo '<a class="gitlogo" href="https://github.com/cob16/cs-221-group-project"> <img src="https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png" height="45" width="45"></a>';
		echo '</div>';

	
		echo '<div class="container">';

			
			echo '<table cellpadding="25">';
			echo '<thead>';
			echo '<tr>';
			echo '<th>Select</th>';
			echo '<th>Species</th>';
			echo '<th>DAFOR Scale</th>';
			echo '<th>Comments</th>';
			echo '<th>Date Recorded</th>';
			echo '<th>Location Photo</th>';
			echo '<th>Species Photo</th>';
			echo '<th>Email</th>';
			echo '</tr>';
			//print eatch row
			while ($a = $res->fetch_assoc()) {
				echo '<tr>';
				echo '<td><input type="checkbox"></td>';
				 $specNumber = $a["species"];
				 while ($c = $dopefish->fetch_assoc()) {
				 	if ($specNumber == $c["species_id"]) {
				 		echo '<td>' . $c["Species"] . '</td>';
				 		break;
				 	}
				 }
				echo '<td>' . $a["DAFOR"] . '</td>';
				echo '<td>' . $a["comments"] . '</td>';
				$fixedDate = date("d-m-Y", strtotime($a["date_recorded"]));
				echo '<td>' . $fixedDate . '</td>';
				echo '<td><img src="' . $a["photo_path_general"] . '"></td>';
				echo '<td><img src="' . $a["photo_path_species"] . '"</td>';
				echo '<td>' . $a["Email"] . '</td>';
				echo '</tr>';
			}
			echo '</table>';
		}
		else
		{
			echo "connection error";
		}
		?>


</div>
</body>
</html>
