<?php
//Include standard header and database connection variables
include 'header.php';
include 'connect.php';

//DELETE SELECTED ITEMS
if (isset($_POST['deleteitems']))
{
    $delete_list = $_POST['deletelist'];

    //Loop through the array of checked checkboxes
    foreach($delete_list as $value){
        if(!$conn->connect_errno > 0){

            $delete_recordings = "DELETE FROM Recordings WHERE reserve_name='" . $value . "'";
            //SQL statement to delete the recordings at a specific reserve
            $reserve = 'DELETE FROM Reserves WHERE reserve_ID="' . $value . '"';
            //SQL statement to delete the reserve

            //Delete a reserve's recordings first, to ensure reserve can be deleted
            if(!$delete_recordings = $conn->query($delete_recordings)){
                die('There was an error deleting the recordings');
            }
            if(!$reserve = $conn->query($reserve)){
                die('There was an error deleting the reserve');
            }

        }
    }
    header('Location: edit_list.php'); //Redirect back to edit page after deleting data
    exit;
}

//UPDATE EDITED ITEM
if (isset($_POST['update_reserve']))
{
    //Variables to update the data posted from the edit_list page
    $update_name = $_POST['updatename'];
    $update_grid = $_POST['updategrid'];
    $update_desc = $_POST['updatedesc'];
    $update_id = $_POST['update_reserve'];

    if(!$conn->connect_errno > 0){
        $reserve = 'UPDATE Reserves SET reserve_name="'.$update_name .'", grid_reference="'.$update_grid.'", description="'.$update_desc.'" WHERE reserve_ID="'.$update_id.'"';
        //SQL statement to update the reserve with the user's data
        if(!$reserve = $conn->query($reserve)){
            die('There was an error running the query [' . $db->error . ']');
        }
    }

    header('Location: edit_list.php'); //Redirect back to edit page after updating data
    exit;
}

?>

<!--Script to validate updated reserve details-->
<script type="text/javascript">

    function check_input(){
        var gridRegExp = /([a-zA-Z]{2})+([0-9]{6})/; //Two letters followed by 6 numbers
        var textRegExp = /[a-zA-Z 0-9]/; //Letters and numbers

        if(textRegExp.test(document.details.updatename.value)
            && gridRegExp.test(document.details.updategrid.value)
            && textRegExp.test(document.details.updatedesc.value))
        {
            document.details.setAttribute("method", "post");
            document.details.setAttribute("action", "update_record.php");
        }
        else if(!textRegExp.test(document.details.updatename.value)){
            alert("Invalid Reserve Name details entered");
            return;

        }
        else if(!gridRegExp.test(document.details.updategrid.value)){
            alert("Invalid grid reference entered, use a 6 figure OS grid reference" +
                " (ie, two letters then 6 numbers");
                return;
            }
        else if(!textRegExp.test(document.details.updatedesc.value)){
            alert("Invalid description entered");
            return;
        }
        //Warns user if invalid data was entered

        }

</script>

<?php
    echo'<div class="container">';
    echo'<form name="details" onsubmit="check_input()">';

    //Sets reserve_id variable to HTTP Get copy if there was an issue with validation
    if (isset($_GET['update_reserve'])){
        $reserve_id= $_GET['update_reserve'];
    }
    else{
        $reserve_id = $_POST['update'];
    }

        if(!$conn->connect_errno > 0) { //if connection was successful
            $reserve = "SELECT * FROM Reserves WHERE reserve_ID='" . $reserve_id . "'";


            if (!$reserve = $conn->query($reserve)) {
                die('There was an error running the query [' . $db->error . ']');
            }
            while ($a = $reserve->fetch_assoc()) {

                if (isset($_GET['update_reserve'])){
                    //Sets data to previously entered value if there was an issue with validation
                    $reserve_name=$_GET['updatename'];
                    $reserve_ref=$_GET['updategrid'];
                    $reserve_desc=$_GET['updatedesc'];
                }
                else{
                    $reserve_name = $a['reserve_name'];
                    $reserve_ref = $a['grid_reference'];
                    $reserve_desc = $a['description'];
                }

                //Print out table
                echo '<div class="panel panel-primary">';
                echo '<table cellpadding="25">';
                echo '<thead>';
                echo '<tr>';
                echo '<th>Reserve Name</th>';
                echo '<th>Location</th>';
                echo '<th>Description</th>';
                echo '</tr>';
                echo '</thead>';
                echo '<td class="no_border"><input class="center" type="text" name="updatename" value="' . $reserve_name . '" size="30" /></td>';
                echo '<td class="no_border"><input class="center" type="text" name="updategrid" value="' . $reserve_ref . '" size="10"maxlength="8" /></td>';
                echo '<td class="no_border"><input class="center" type="text" name="updatedesc" value="' . $reserve_desc . '" size="30" maxlength="255"/></td>';

                echo '</table>';
                echo '<br /><p style="text-align: center;"><button type="submit" name="update_reserve" class="btn btn-success" value="'.$a["reserve_ID"].'">Update Reserve</button></p>';
            }
        }

//Closed rest of opened tags
echo'</form>';
echo '</div>';
echo'</div>';
echo'</body>';
echo'</html>';
?>