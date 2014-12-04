<!DOCTYPE html>
<html lang="en">
<head>
	<title>Record Table</title>
</head>
<body>
	<div class="container">
		<?php

		$conn = pg_connect("host=http://cormacbrady.info/owncloud/superSecret/adminer-4.1.0.php?db=cb-group-project
			dbname=Reserves user=tkek password=topkek3");
		$res = pg_query ($conn, "select * from Reserves");
		echo '<table>';
		echo '<thead>';
		echo '<tr>';
		echo '<th>Reserve ID</th>';
		echo '<th>Reserve Name</th>';
		echo '</tr>';
		while ($a = pg_fetch_array ($res)) {
			echo '<tr>';
			echo '<td>' . $a["reserve_id"] . '</td>';
			echo '<td>' . $a["reserve_name"] . '</td>';
			echo '</tr>'
		}
		echo '</table>';
		?>
	</div>
</body>
</html>
