<!DOCTYPE html>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="style221.css" />
  <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,300,600,700' rel='stylesheet' type='text/css'>
  <title>RPSRview</title>
  <meta charset="UTF-8" />
</head>
<body>
  <div class="header">
    <h1><a href="https://cob16.github.io/cs-221-group-project"><strong>RPSR</strong>view</a></h1>
    <p>database of reserves</p>
    <a class="gitlogo" href="https://github.com/cob16/cs-221-group-project"> <img src="https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png" height="45" width="45"></a>
  </div>


  <div class="container">
    <form action="edit_list.php" method="post">

    <?php

    //DELETE SELECTED ITEMS
    if (isset($_POST['deleteitems']))
    {
      $delete_list = $_POST['deletelist'];
      foreach($delete_list as $value){

        $host = 'localhost';
        $user = 'tkek';
        $pass = 'topkek3';
        $database = 'cb-group-project';
        $conn = new mysqli($host, $user, $pass, $database);
        if(!$conn->connect_errno > 0){
          $res = <<<SQL DELETE FROM 'Reserves' WHERE 'reserve_ID='.$value SQL;
          //$res = <<<SQL DELETE FROM 'Reserves' WHERE 'reserve_ID=$value' SQL;
          if(!$res = $conn->query($res)){
            die('There was an error running the query [' . $db->error . ']');
          }
        }
      }
    }
    //need similar code to update databse with edited details

    $host = 'localhost';
    $user = 'tkek';
    $pass = 'topkek3';
    $database = 'cb-group-project';
    $conn = new mysqli($host, $user, $pass, $database);
    if(!$conn->connect_errno > 0){
      $res = <<<SQL
      SELECT *
      FROM `Reserves`
      SQL;

      if(!$res = $conn->query($res)){
        die('There was an error running the query [' . $db->error . ']');
      }

    //text needs to be input fields, to be able to update details
    echo '<table cellpadding="25">';
    echo '<thead>';
    echo '<tr>';
    echo '<th>Reserves</th>';
    echo '</tr>';
    while ($a = $res->fetch_assoc()) {
        echo '<tr>';
        echo '<td><a href="records.php?reserve=' . $a["reserve_ID"] . '">' . $a["reserve_name"] .'</a></td>';
        echo '<td><input type="checkbox" name="deletelist[]" value="'.$a["reserve_ID"].'"></td>';
        echo '</tr>';
    }
    echo '</table>';

    echo '<input type="submit" name ="delete" value ="Delete Selected"/>';
    echo '<input type="submit" name ="cancel" value ="Cancel"/>'; //should just take user back to index
    echo '<input type="submit" name="apply" value ="Apply Changes"/>';

  }
?>

    </form>
    </div>
  </body>
</html>
