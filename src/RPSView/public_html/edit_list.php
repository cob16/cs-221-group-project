<?php
include 'header.php';
include 'connect.php';
//Include standard header and database connection information
?>

<!--Displays warning if user checks a reserve with recording data-->
<script type="text/javascript">
    function check_delete(obj){
        if(obj.checked){
            alert("WARNING: If you delete this it will delete all of the associated recordings!");
        }
    }
</script>

<?php
//Bootstrap panel
echo '<div class="container">';
echo '<div class="panel panel-primary filterable">';
       echo ' <div class="panel-heading">';
            echo '<h3 class="panel-title">Edit Reserves</h3>';
        echo '</div>';



if(!$conn->connect_errno > 0){ //If connection was successful

    $reserves = 'SELECT * FROM Reserves ORDER BY reserve_name';
    //SQL statement to get all the reserves ordered alphabetically
    $reserve_recordings = 'SELECT COUNT(*) FROM Records WHERE reserve_name = $a["reserve_ID"]';
    //SQL statement to get the number of records pertaining to a specific reserve

    if(!$reserves = $conn->query($reserves)){
        die('There was an error running the query [' . $db->error . ']');
    }

    //Set up table header
    echo '<form action="update_record.php" method="post">';
    echo '<table class="table table-striped">';
    echo '<thead>';
    echo '<tr>';
    echo '<th><input type="submit" name ="deleteitems" class="btn btn-danger" id="submitdelete" value ="Delete Selected" /></th>';
    echo '<th>Reserve Name</th>';
    echo '<th>Location</th>';
    echo '<th>Description</th>';
    echo '<th># of Records</th>';
    echo '<th><a href="index.php" class="btn btn-info">Cancel</a></th>';
    echo '</tr>';
    echo '</thead>';

    while ($a = $reserves->fetch_assoc()) { //Loop through queried data and output rows

        //Variables to change output if the field is too long
        $name = substr($a['reserve_name'],0,39);
        $description = substr($a['description'],0,39);
        $desclength = strlen($a['description']);
        $namelength = strlen($a['reserve_name']);

        echo '<tr>';
        //Get number of recordings at each reserve
        $reserve_recordings = 'SELECT COUNT(*) FROM Recordings WHERE reserve_name = ' . $a["reserve_ID"];
        if(!$reserve_recordings = $conn->query($reserve_recordings)){
            die('There was an error running the query');
        }
        $count_recordings = $reserve_recordings->fetch_assoc();

        //If the reserve has recordings, run script to warn user
        if($count_recordings["COUNT(*)"]>0){
            echo '<td><input type="checkbox" name="deletelist[]" value="'.$a["reserve_ID"].'" onclick="check_delete(this)"></td>';
        }
        else{
            echo '<td><input type="checkbox" name="deletelist[]" value="'.$a["reserve_ID"].'"></td>';
        }

        //Display abbreviated name if field would be too long
        if($namelength>40){
            echo '<td>'.ucwords($name).'...</td>';
        }
        else{
            echo '<td>'.ucwords($name).'</td>';
        }

        //Display fallback message if no grid reference
        if($a['grid_reference']=="0"){
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
                echo '<td>'.$a["description"].'</td>';
            }

        }

        //Display amount of reserve recordings
        echo '<td>' . $count_recordings["COUNT(*)"] . '</td>';

        //Link to Update page
        echo'<td>';
        echo'<button type="submit" value="'.$a["reserve_ID"].'" class="btn btn-default" name="update">Update</button>';
        echo '</td>';

        echo '</tr>';
    }
    echo '</table>';

  }

//Close rest of opened tags
echo '</form>';
echo '</div>';
echo '</div>';
echo '</body>';
echo '</html>';
?>