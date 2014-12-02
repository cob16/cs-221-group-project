<?php
	interface Updater {
		public function addReserve($resName);
		//Adds a new reserve to the Reserve table of the database
	 
		 public function addUser($userID, $email);
		 //Adds a new user to the User table in the database
	 
		 public function addRecord($species, $comment, $date_recorded, $photo_path_general, $photo_path_species, $author_ID, $reserve_name);
		 //Adds data to the Recordings table in the database
	 
		 public function addSpecies($species);
		 //Adds data about the plant species in to the Species table in the database
	 
		 public function getReserves();
		 //fetches a list of all reservers
	 
		 public function getRecords($Reserve);
		 //gets the list of all records in a Reserve
		 
	}
?>
