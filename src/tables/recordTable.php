<!DOCTYPE html>
<html lang="en">
<head>
	<title>Record Table</title>
</head>
<body>
	<div class="container">


		<?php
		// ================================================================
		// THIS GENERATES A TABLE FOR THE RECORDINGS GOT FROM THE DATABASE
		// =================================================================
		$conn = pg_connect("host=http://cormacbrady.info/owncloud/superSecret/adminer-4.1.0.php?db=cb-group-project
			dbname=Recordings user=tkek password=topkek3");
		$res = pg_query ($conn, "select * from Recordings");
		echo '<table>';
		echo '<thead>';
		echo '<tr>';
		echo '<th>ID</th>';
		echo '<th>Species</th>';
		echo '<th>DAFOR Scale</th>';
		echo '<th>Comments</th>';
		echo '<th>Date Recorded</th>';
		echo '<th>Location Photo</th>';
		echo '<th>Species Photo</th>';
		echo '<th>Email</th>';
		echo '<th>Reserve Name</th>';
		echo '</tr>';
		while ($a = pg_fetch_array ($res)) {
			echo '<tr>';
			echo '<td>' . $a["ID"] . '</td>';
			echo '<td>' . $a["species"] . '</td>';
			echo '<td>' . $a["DAFOR"] . '</td>';
			echo '<td>' . $a["comments"] . '</td>';
			echo '<td>' . $a["date_recorded"] . '</td>';
			echo '<td>' . $a["photo_path_general"] . '</td>';
			echo '<td>' . $a["photo_path_species"] . '</td>';
			echo '<td>' . $a["Email"] . '</td>';
			echo '<td>' . $a["reserve_name"] . '</td>';
			echo '</tr>'
		}
		echo '</table>'; 
		?>
	</div>
</body>
</html>
