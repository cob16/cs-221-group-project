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
		<h1><a href="https://cob16.github.io/cs-221-group-project"><strong>RPSR</strong>view</a></h1>
		<p>database of reserves</p>
		<a class="gitlogo" href="https://github.com/cob16/cs-221-group-project"> <img src="https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png" height="45" width="45"></a>
	</div>


	<div class="container">

		<?php
		$host = 'localhost';
		$user = 'tkek';
		$pass = 'topkek3';
		$database = 'cb-group-project';
		$conn = new mysqli($host, $user, $pass, $database);
		if(!$conn->connect_errno > 0){
			$res = <<<SQL
			SELECT *
			FROM `Reserves`
			SQL;



			if(!$res = $conn->query($res)){
				die('There was an error running the query [' . $db->error . ']');
			}


		// echo '<table cellpadding="25">';
		// echo '<thead>';
		// echo '<tr>';
		// echo '<th>testing</th>';
		// while ($b = $fishsticks->fetch_assoc()) {
		// 	echo '<td>' . $a["reserve_name"] . '</td>';
		// 	echo '</tr>';
		// }
		// echo'</table>';

		echo '<table cellpadding="25">';
		echo '<thead>';
		echo '<tr>';
		echo '<th>Reserves</th>';
		echo '</tr>';
		while ($a = $res->fetch_assoc()) {
				echo '<tr>';
				echo '<td><a href="records.php?reserve=' . $a["reserve_ID"] . '">' . $a["reserve_name"] .'</a></td>';
				echo '</tr>';
		}
	echo '</table>';
	
	echo '<button onclick="location.href='/add_record.php'">Add Record</button>';
	echo '<button onclick="location.href='/edit_list.php'">Edit List</button>';
	}
?>


</div>
</body>
</html>
