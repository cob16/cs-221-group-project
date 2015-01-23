<?php include 'header.html';/* this adds the header.html file
to the beginning of the file, do not remove */
include 'connect.php';
?>


<div class="container">
<form action="edit_list.php" method="post">

<?php

//DELETE SELECTED ITEMS
if (isset($_POST['deleteitems']))
{
    $delete_list = $_POST['deletelist'];
    foreach($delete_list as $value){
        if(!$conn->connect_errno > 0){
            $res = 'DELETE FROM Reserves WHERE reserve_ID="'.$value.'"';
            if(!$res = $conn->query($res)){
              die('There was an error running the query [' . $db->error . ']');
            }
        }
    }
}

//UPDATE EDITED ITEM
if (isset($_POST['update']))
{
    $update_name = $_POST['updatename'];
    $update_id = $_POST['update'];

        if(!$conn->connect_errno > 0){
            $res = 'UPDATE Reserves SET reserve_name="'.$update_name .'" WHERE reserve_ID="'.$update_id.'"';
            if(!$res = $conn->query($res)){
                die('There was an error running the query [' . $db->error . ']');
            }
        }

}


if(!$conn->connect_errno > 0){ //if connection was successful
    $res = <<<SQL
			SELECT *
			FROM `Reserves`
SQL;


    if(!$res = $conn->query($res)){
        die('There was an error running the query [' . $db->error . ']');
    }

    echo '<table cellpadding="25">';
    echo '<thead>';
    echo '<tr>';
    echo '<th>Reserves</th>';
    echo '</tr>';
    while ($a = $res->fetch_assoc()) {
        echo '<tr>';
        echo '<td><input type="text" name="updatename" value="'.$a["reserve_name"].'" /></td>';
        echo '<td>Delete?<input type="checkbox" name="deletelist[]" value="'.$a["reserve_ID"].'"></td>';
        echo '<td><button type="submit" name="update" value="'.$a["reserve_ID"].'">Update</button></td>';
        echo '</tr>';
    }
    echo '</table>';
    echo '<input type="submit" name ="deleteitems" value ="Delete Selected" />';
    echo '<a href="index.php">Cancel</a>';


  }
?>

    </form>
    </div>
  </body>
</html>
