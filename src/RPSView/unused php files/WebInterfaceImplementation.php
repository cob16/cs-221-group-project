<?php
	interface Updater {
		public function addReserve($resName);
		//Adds a new reserve to the Reserve table of the database
	 
		 public function addUser($userID, $auth, $email);
		 //Adds a new user to the User table in the database
	 
		 public function addRecord($spe, $comment, $reserveName);
		 //Adds data to the Recordings table in the database
	 
		 public function addSpecies($Spec, $comName){
		 //Adds data about the plant species in to the Species table in the database
	 
		 public function removeReserve();
		 //Removes a selected reserve from the Reserve table in the database
	 
		 public function removeRecord();
		 /*Removes the selected record or set of records
		  in the Recordings table of the database*/
	 
		 public function removeUser();
		 //Removes a selected user from the User table in the database
		 
		 public function findRecord();
		 /*Search through the Recordings table in the database
		 to find a specific recording or set of recordings*/
   }
?>


<?php
	public function addReserve($resName){
		//The new ID number is equal to the last ID value added + 1.
		$ID = SELECT COUNT(*) FROM Reserves;
		$ID = $ID + 1;
		
		INSERT INTO `Reserves` (`reserve_ID`, `reserve_name`) VALUES ('$resID', '$resName');

		//==============================EXAMPLE TEST DATA====================================
		//INSERT INTO `Reserves` (`reserve_ID`, `reserve_name`) VALUES (1, 'example reserve');
		//===================================================================================
	}
	
	public function addUser($auth, $email){
		//Simply adds the input author and their email into the database
		INSERT INTO `Users` (`author` , `Email`) VALUES ('$auth', '$email');
	}
	
	public function addRecord($spe, $comment, $reserveName){
	
		//The new ID number is equal to the last ID value added + 1.
		$ID = SELECT COUNT(*) FROM Recordings;
		$ID = $ID + 1;
		
		//Creates variable for todays date.
		$date = date("Y-m-d");
		
		//Whatever the path name is for the general
		$photoPathGen = "C:// ...";
		
		//Whatever the path name is for the species
		$photoPathSpe = "C:// ..."
		
		$author_ID = "";
		
		INSERT INTO `Recordings` (`ID`, `species`, `comments`, `date_recorded`, `photo_path_general`, `photo_path_species`, `author_ID`, `reserve_name`) 
		VALUES ('$ID', '$spe', '$comment', '$date', '$photoPathGen', '$photoPathSpe' , '$author_ID', '$reserveName');
		
		//==================================================EXAMPLE TEST DATA ==================================================
		//INSERT INTO `Recordings` (`ID`, `species`, `comments`, `date_recorded`, `photo_path_general`, `photo_path_species`, `author_ID`, `reserve_name`) VALUES
		//(1, 'Abelia uniflora', 'this is the firts test record', '2014-11-16', 'foo/foo.jpeg', 'foo/foo1.jpeg', 1, '1');
		//=======================================================================================================================
	}
	
	
	public function addSpecies($Spec, $comName){
		
		//The new ID number is equal to the last ID value added + 1.
		$ID = SELECT COUNT(*) FROM Species;
		$ID = $ID + 1;
		
		INSERT INTO `Species` (`species_id`, `Species`, `Common Name`) VALUES ('$ID', '$Spec', $comName);
		
		//========================================EXAMPLE TEST DATA =======================================
		//INSERT INTO `Species` (`species_id`, `Species`, `Common Name`) VALUES(1, 'Abelia uniflora', NULL)
		//=================================================================================================
	}
	
	public function removeReserve($reserveName){

		//Deletes the reserve where the user inputs the reserves name.
		DELETE FROM `Reserves` WHERE reserve_name='$reserveName';

	}
	
	public function removeRecord($species, $reserveName){

		//Deletes the record where the user input the reserve name and the species to remove. 
		DELETE FROM `Recordings` WHERE reserve_name='$reserveName' AND species='$species';

	}
	
	public function removeUser($Email){
		//Deletes a user where the input email is the users email. 
		DELETE FROM `users` WHERE Email = '$Email';
	}
	
	public function findRecord($species, $reserveName){
		$SQLSearch = SELECT * FROM `Recordings` WHERE reserve_name='$reserveName' AND species='$species';
		return $SQLSearch;
	}
?> 