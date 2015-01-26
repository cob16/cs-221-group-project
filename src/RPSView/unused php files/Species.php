<!DOCTYPE html>
<html lang="en">
<head>
	<title>Record Table</title>
</head>
<body>
	<div class="container">
		<?php

		$conn = pg_connect("host=http://cormacbrady.info/owncloud/superSecret/adminer-4.1.0.php?db=cb-group-project
			dbname=Species user=tkek password=topkek3");
		$res = pg_query ($conn, "select * from Species");
		echo '<table>';
		echo '<thead>';
		echo '<tr>';
		echo '<th>Species ID</th>';
		echo '<th>Scientific Name</th>';
		echo '<th>Common Name</th>';
		echo '</tr>';
		while ($a = pg_fetch_array ($res)) {
			echo '<tr>';
			echo '<td>' . $a["species_id"] . '</td>';
			echo '<td>' . $a["Species"] . '</td>';
			echo '<td>' . $a["Common Name"] . '</td>';
			echo '</tr>'
		}
		echo '</table>';
		?>
	</div>
</body>
</html>
