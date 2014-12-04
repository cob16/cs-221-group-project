<!DOCTYPE html>
<html lang="en">
<head>
	<title>Record Table</title>
</head>
<body>
	<div class="container">
		<?php

		$conn = pg_connect("host=http://cormacbrady.info/owncloud/superSecret/adminer-4.1.0.php?db=cb-group-project
			dbname=Users user=root password=Universe94!");
		$res = pg_query ($conn, "select * from Users");
		echo '<table>';
		echo '<thead>';
		echo '<tr>';
		echo '<th>Name</th>';
		echo '<th>Email</th>';
		echo '</tr>';
		while ($a = pg_fetch_array ($res)) {
			echo '<tr>';
			echo '<td>' . $a["Name"] . '</td>';
			echo '<td>' . $a["Email"] . '</td>';
			echo '</tr>'
		}
		echo '</table>';
		?>
	</div>
</body>
</html>