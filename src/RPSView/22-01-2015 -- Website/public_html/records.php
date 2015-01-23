<?php	
include "header.php";
include "connect.php";

		 $reserve_name = (int) $_GET["reserve"];


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
			$reserves = <<<SQL
			SELECT *
			FROM `Reserves`
SQL;

			

		if(!$reserves = $conn->query($reserves)){
			die('There was an error running the query :)');
		}
		if(!$res = $conn->query($res)){
			die('There was an error running the query :)');
		}
		if(!$fishsticks = $conn->query($fishsticks)) {
			die('there was an error running the query :(');
		}
		if(!$dopefish = $conn->query($dopefish)) {
			die('there was an error running the query :(');
		}
?>
<br /> <br />
<div class="sidebar-nav-fixed affix">
	<div class="well">
		<ul class="nav ">
			<li class="nav-header">Reserves</li>
			<?php  
			while ($a = $reserves->fetch_assoc()) {
				echo '<li>';
				echo '<a href="records.php?reserve=' . $a["reserve_ID"] . '">' . ucwords($a["reserve_name"]) .'</a>';
				echo '</li>';
			}
			?>
		</ul>
	</div>	
 </div>
<div class="container">


	<?php
		/*
		 * Takes the reserve id passed to it
		 * anf looks it up in order to display
		 * a table of the first 25 results
		 */		

		

		echo '<div class="container">';
		echo '<div class="panel panel-default">';
		echo '<!-- Default panel contents -->';
		while ($b = $fishsticks->fetch_assoc()) {
			if ($reserve_name == $b["reserve_ID"]) {
				echo '<div class="panel-heading">Reserve - ' . ucwords($b["reserve_name"]) . '</div>';
			}
		}		



		echo '<table class="table table-striped">';
		echo "<thead>";
		echo '<thead>';
		echo '<tr>';
			// echo '<th>Select</th>';
		echo '<th>Species</th>';
		echo '<th>DAFOR Scale</th>';
		echo '<th>Comments</th>';
		echo '<th>Date Recorded</th>';
		echo '<th>Location Photo</th>';
		echo '<th>Species Photo</th>';
		echo '<th>Email</th>';
		echo '</tr>';
		echo '</thead>';
			//print eatch row
		$i=0;
		while (($a = $res->fetch_assoc()) && ($i<=5)) {
			echo '<tr>';
				// echo "<td><input type='checkbox' name='check[]' value=" . $a["ID"]	 . "></td>";
			$specNumber = $a["species"];
			$speciesNames = <<<SQL
			SELECT Species
			FROM Species
			WHERE species_id = $specNumber
SQL;
			if(!$speciesNames = $conn->query($speciesNames)){
			die('There was an error running the query :)');
		}
			$hola = $speciesNames->fetch_assoc();			
			echo '<td>' . $hola["Species"] . '</td>';		
			echo '<td>' . $a["DAFOR"] . '</td>';
			echo '<td>' . $a["comments"] . '</td>';
			$fixedDate = date("d-m-Y", strtotime($a["date_recorded"]));
			echo '<td>' . $fixedDate . '</td>';
			echo '<td><img src="' . $a["photo_path_general"] . '" height="75" width="75"></td>';
			echo '<td><img src="' . $a["photo_path_species"] . '" height="75" width="75	"></td>';
			echo '<td>' . $a["Email"] . '</td>';
			echo '</tr>';
		}
		echo '</table>';


		//CHANGE PAGES
		echo '<div class ="panel-body">';
		echo '<ul class="pagination">';
		echo '<li>';
		echo '<a href="#" aria-label="Previous">';
		echo ' <span aria-hidden="true">&laquo;</span>';
		echo '</a>';
		echo '</li>';
		echo '<li><a href="#">1</a></li>';
		echo '<li><a href="#">2</a></li>';
		echo '<li><a href="#">3</a></li>';
		echo '<li><a href="#">4</a></li>';
		echo '<li><a href="#">5</a></li>';
		echo '<li>';
		echo '<a href="#" aria-label="Next">';
		echo '<span aria-hidden="true">&raquo;</span>';
		echo '</a>';
		echo '</li>';
		echo '</ul>';
		echo '</div>';
		echo '</div>';
	}
	else
	{
		echo "connection error";
	}
	?>
</form>

</div>	



</body>
</html>