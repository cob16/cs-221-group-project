<?php
//Include database connection, header and reserve addition code
include 'connect.php';
include "header.php";
include "add_reserve.php";

//Bootstrap panel
echo'<div class="container">';
	echo'<div class="panel panel-primary filterable">';
		echo'<div class="panel-heading">';
			echo'<h3 class="panel-title">Reserves</h3>';
			echo'<div class="pull-right">';

				//Bootstrap modal - hidden form that appears when button is clicked
				echo'<a data-toggle="modal" href="#myModal1" class="btn btn-success">Add Reserve</a>';
				echo'<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
					echo'<div class="modal-dialog">';
						echo'<div class="modal-content">';
							echo'<div class="modal-header">';
								echo'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>';
								echo'<h4 class="modal-title">Add Reserve</h4>';
							echo'</div>';
							echo'<form name="details" onsubmit="return check_input()" role="form">';

								echo'<div class="modal-body">';

								//Displays previously entered data if an error in validation was found
								if (isset($_GET['reserve_name'])){

									echo'<label for="reserve_name">Reserve Name</label>';
									echo'<input type="text" class="form-control" name="reserve_name" id="email" placeholder="Enter reserve name..." aria-describedby="basic-addon1" required value="'.$_GET["reserve_name"].'">';

									echo'<label for="grid_reference">Location</label>';
									echo'<input type="text" class="form-control" name="grid_reference" placeholder="Enter location..." aria-describedby="basic-addon1"	value="'.$_GET["grid_reference"].'">';

									echo'<label for="description">Description</label>';
									echo'<input type="text" pattern="{255}" class="form-control" name="description" placeholder="Enter description..." aria-describedby="basic-addon1"value="'.$_GET["description"].'">';
								}
								else{

									echo'<label for="reserve_name">Reserve Name</label>';
									echo'<input type="text" class="form-control" name="reserve_name" id="email" placeholder="Enter reserve name..." aria-describedby="basic-addon1" required>';

									echo'<label for="grid_reference">Location</label>';
									echo'<input type="text" class="form-control" name="grid_reference" placeholder="Enter location..." aria-describedby="basic-addon1"	>';

									echo'<label for="description">Description</label>';
									echo'<input type="text" pattern="{255}" class="form-control" name="description" placeholder="Enter description..." aria-describedby="basic-addon1">';
								}


									echo'</div>';

								echo'<div class="modal-footer">';
								echo'<button type="submit" name="add_reserve" class="btn btn-primary btn-lg">Add Reserve</button>';
						echo'</div>';
						echo'</form>';
						echo'</div>';
					echo'</div>';
				echo'</div>';

//Link to Edit List page
echo'<a href="edit_list.php"><button type="button" id="editButton" class="btn btn-primary">Edit List</button></a>';
echo'</div>';
echo'</div>';

		 if(!$conn->connect_errno > 0){ //if connection was successful
		 	$reserves = 'SELECT * FROM Reserves ORDER BY reserve_name';
			 //SQL statement to select all of the reserves sorted alphabetically

		 	if(!$reserves = $conn->query($reserves)){
		 		die('There was an error running the query [' . $db->error . ']');
		 	}

		//Sets up table header
		 	echo '<table class="table table-striped">';
		 	echo '<thead>';
		 	echo '<tr>';
		 	echo '<th>Reserve Name</th>';
		 	echo '<th>Location</th>';
		 	echo '<th>Description</th>';
		 	echo '</tr>';
		 	echo '</thead>';

		 	while ($a = $reserves->fetch_assoc()) { //Loop through queried data and output rows

				//Variables to change output if the field is too long
		 		$name = substr($a['reserve_name'],0,39);
		 		$description = substr($a['description'],0,39);
		 		$desclength = strlen($a['description']);
		 		$namelength = strlen($a['reserve_name']);

		 		echo '<tr>';

				//Display abbreviated name if field would be too long
		 		if($namelength>40){
		 			echo '<td><a href="records.php?reserve=' . $a["reserve_ID"] . '">' . ucwords($name) .'...</a></td>';
		 		}
		 		else{
		 			echo '<td><a href="records.php?reserve=' . $a["reserve_ID"] . '">' . ucwords($name) .'</a></td>';
		 		}

				//Display fallback message if no grid reference
		 		if($a['grid_reference'] == "0"){
		 			echo '<td><em>No location provided</em></td>';
		 		}
		 		else{
		 			echo '<td>'.strtoupper($a["grid_reference"]).'</td>';
		 		}

				//Display abbareviated description if field would be too long
		 		if($desclength>40){
		 			echo '<td>'.$description.'...</td>';
		 		}
		 		else{
					//Display fallback message if no description
		 			if($desclength==0){
		 				echo '<td><em>No description provided</em></td>';
		 			}
		 			else{
		 				echo '<div class="description">';
		 				echo '<td>'.$a["description"].'</td>';
		 				echo '</div';
		 			}

		 		}

		 		echo '</tr>';
		 	}
		 	echo '</table>';

		 }
		 else
		 {
		 	echo "connection error";
		 }

	//Close rest of opened tags
	echo'</div>';
	echo'</div>';
	echo'</div>';
	echo'</div>';
	echo'</body>';
	echo'</html>';
?>
