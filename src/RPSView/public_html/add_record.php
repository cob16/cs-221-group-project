<?php
/**
 * Created by PhpStorm.
 * User: cob16
 * Date: 23/01/2015
 * Time: 12:19
 *
 * this adds posts to the databace
 */

include 'connect.php';

 /* what the post should look like
{
  "user": {
    "name": "cormac brady",
    "email": "cob16@aber.ac.uk",        //check this
    "number": "0123456789"
  },
  "record_list": [
    {
      "record": {
        "species": "Abelia uniflora",           //check this
        "DAFOR": "D",
        "comments": "this is the post test record",
        "date_recorded": "2014-01-22",
        "reserve_name": "example name", //check this
        "location": "SN 584 819",
        "first_pic": "DELETE ME",
        "second_pic": "DELETE ME"
      }
    }
  ]
}

 new format

 {
    "user": {
        "name": "jimbob",
        "email": "jimbob@gmail.com",
        "number": "07123456789"
    },
    "record": {
        "species": "example",
        "DAFOR": "D",
        "comments": "example",
        "date_recorded": "2014-01-22",
        "Email": "example@example.com",
        "reserve_name": "example reserve name",
        "location": "example location",
        "first_pic": "example",
        "second_pic": "example"
    }
}
  */


$data = json_decode(file_get_contents('php://input'), true);
//var_dump($data);

//did we get post data?
if ( !$data ) {
    echo "there is no post data found";
}
else {
    echo "<p> data found: ";

    //try to find this user in the databace
    $email = $data['user']['email'];
    echo $email . " </p>";
    $sql = "SELECT Email FROM Users WHERE Email = '$email'";
    $result = $conn->query($sql);

    //did we get back a result
    if (!(mysqli_num_rows($result) > 0)) { //no we did not

        echo "<p> New user found. </p>";

        //add a new user
        $name = $data['user']['name'];
        $email = $data['user']['email'];
        $phone_number = $data['user']['number'];

        $sql = "INSERT INTO Users(Name, Email, phone_number)
                    VALUES ('$name','$email' ,'$phone_number' )";
        if ($conn->query($sql) === TRUE) {
            echo "<p> New user created successfully </p>";
        } else {
            //http_response_code(500);
            echo "Error: " . $sql . "<br />" . $conn->error;
        }

    } else {
        echo "<p> Existing user found.</p>";
    }

    $current_record = 0;// used in future to go though an array of records

    //valadate the reserve name provided
    $reserve_name = $data['record']['reserve_name'];
    echo "<p> reserve name: " . $reserve_name . "</p>";
    $sql = "SELECT * FROM Reserves WHERE reserve_name = '$reserve_name'";
    $result_reserves = $conn->query($sql) or die(mysqli_error($conn));
    if (!(mysqli_num_rows($result_reserves) > 0)) {
        echo("<p> reserve does not exsist </p>");
        die('<p> Invalid query: ' . mysqli_error($conn) . "</p>");
        //http_response_code(400);
    } else {

        echo "<p> Existing reserve found.</p>";

        //if thats ok valadate the spices name
        $species_name = $data['record']['species'];
        echo "<p> species_name: " . $species_name . "</p>";
        $sql = "SELECT * FROM Species WHERE Species = '$species_name'";
        $result_species = $conn->query($sql) or die(mysqli_error($conn));
        if (!(mysqli_num_rows($result_species) > 0)) {
            echo("<p> species does not exsist </p>");
            die('<p> Invalid query: ' . mysqli_error($conn) . "</p>");
            //http_response_code(400);
        } else {

            //get all the data we need to add to records
            echo "<p> Existing reserve found.</p>";

            $species_id = $result_species->fetch_assoc();
            $species_id = $species_id['species_id'];
                echo "<p> Id: " . $species_id . "</p>";
                echo "<p> Adding record ($current_record) </p>";


            $reserve_ID = $result_reserves->fetch_assoc();
            $reserve_ID = $reserve_ID['reserve_ID'];

            $DAFOR         = $data['record']["DAFOR"];
            $comments      = $data['record']["comments"];
            $date_recorded = $data['record']["date_recorded"];
            $location      = $data['record']["location"];
            $first_pic     = $data['record']["first_pic"];
            $second_pic    = $data['record']["second_pic"];

            if (!$location) {
                $location = $result_reserves['grid_reference'];
            }

            $sql = "INSERT INTO `Recordings`(`species`, `DAFOR`, `comments`, `date_recorded`, `photo_path_general`, `photo_path_species`, `Email`, `reserve_name`, `location`)
              VALUES ('$species_id', '$DAFOR', '$comments'  ,'$date_recorded', '$first_pic', '$second_pic', '$email' ,'$reserve_ID' ,'$location')";

            if ($conn->query($sql) === TRUE) {n
                //http_response_code(201);
                echo "<p> New record created successfully </p>";
            } else {
                //http_response_code(500);
                echo "Error: " . $sql . "<br>" . $conn->error;
            }
        }
    }
}
?>
