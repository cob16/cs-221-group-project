<?php	
include "header.php";
include "connect.php";
//Include standard header and database connection variables

$reserve_name = (int) $_GET["reserve"];
//Variable to store accessed reserve

	if(!$conn->connect_errno > 0){ //if connection is successful

		$recordings = "SELECT * FROM Recordings WHERE reserve_name='".$reserve_name."' ORDER BY date_recorded";
		//SQL statement to select all of the recordings pertaining to a certain reserve
		$reserve_data = "SELECT * FROM Reserves";
		//SQL statement to select all of the reserves

		if(!$recordings = $conn->query($recordings)){
			die('There was an error accessing the recordings data.');
		}
		if(!$reserve_data = $conn->query($reserve_data)) {
			die('there was an error accessing the reserve data.');
		}

		//Bootstrap panel
		echo '<div class="container">';
		echo '<div class="panel panel-primary">';
		echo '<!-- Default panel contents -->';
		while ($b = $reserve_data->fetch_assoc()) {
			if ($reserve_name == $b["reserve_ID"]) {
				//Output the header with the reserve data
				echo '<div class="panel-heading"><strong>Reserve</strong> - ' . ucwords($b["reserve_name"]);

				if((strlen($b['description']))==0){
					echo '<br /><strong>Description</strong> - <em>No description provided</em>';
				}
				else{
					echo '<br /><strong>Description</strong> - ' . $b["description"];
				}
				if($b['grid_reference']==0){
					echo '<br /><strong>Location</strong> - <em>No location provided</em></div>';
				}
				else{
					echo '<br /><strong>Location</strong> - ' . $b["grid_reference"] . '</div>';
				}

			}
		}		


		//Set up table header
		echo '<table class="table table-striped">';
		echo '<thead>';
		echo '<tr>';
		echo '<th>Species</th>';
		echo '<th>DAFOR Scale</th>';
		echo '<th>Comments</th>';
		echo '<th>Date Recorded</th>';
		echo '<th>Location Photo</th>';
		echo '<th>Species Photo</th>';
		echo '<th>Email</th>';
		echo '</tr>';
		echo '</thead>';

		
		while ($a = $recordings->fetch_assoc()) {
			echo '<tr>';

			$specNumber = $a["species"];
			$species_names = "SELECT Species FROM Species WHERE species_id='".$specNumber."'";
			//SQL statement to select the specific species
			if(!$species_names = $conn->query($species_names)){
				die('There was an error accessing the species data.');
			}
			$c = $species_names->fetch_assoc();
			echo '<td>' . $c["Species"] . '</td>';
			echo '<td>' . $a["DAFOR"] . '</td>';
			echo '<td>' . $a["comments"] . '</td>';
			$fixedDate = date("d-m-Y", strtotime($a["date_recorded"]));
			echo '<td>' . $fixedDate . '</td>';

			//If there is a linked photo, show that
			//If not, display the fallback error photo
			if ($a["photo_path_general"] == "") {
				echo '<td><img class="small" src="images/camerav2.jpg" alt=""></td>';
			}
			else {
				echo '<td><img class="small" src="' . $a["photo_path_general"] . '" alt=""></td>';
			}

			//If there is a linked photo, show that
			//If not, display the fallback error photo
			if ($a["photo_path_species"] == "") {
				echo '<td><img class="small" src="images/camerav2.jpg" alt=""></td>';
			}
			else {
				echo '<td><img class="small" src="' . $a["photo_path_species"] . '" alt=""></td>';
			}

			echo '<td>' . $a["Email"] . '</td>';
			echo '</tr>';
		}
		echo '</table>';		
	}
	else
	{
		echo "ERROR: Database connection error!";
	}

//Close the rest of the opened tags
echo '</div>';
echo'</div>';
echo'</body>';
echo'</html>';
?>