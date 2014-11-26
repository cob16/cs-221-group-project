<?php

  interface Updater
  {
    public function addReserve();
    //Adds a new reserve to the Reserve table of the database

    public function addUser($user);
    //Adds a new user to the User table in the database

    public function addRecord($record);
    //Adds data to the Recordings table in the database

    public function addSpecies();
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
