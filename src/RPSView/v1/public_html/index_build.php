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

<?php
$host = 'localhost';
$user = 'tkek';
$pass = 'topkek3';
$database = 'cb-group-project';
$conn = new mysqli($host, $user, $pass, $database);

//ADD NEW RESERVE
if (isset($_POST['reserve_name']))
{
	$new_record = $_POST['reserve_name'];
	if(!$conn->connect_errno > 0){
		//$add = <<<SQL INSERT INTO `Reserves` (`reserve_name`) VALUES ($new_record) SQL;
		//$res = <<<SQL INSERT INTO `Reserves` VALUES ($new_record) SQL;
	$add = 'INSERT INTO Reserves (reserve_name) VALUES ("' . $new_record . '")';
		if(!$add = $conn->query($add)){
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
				echo '<td><a href="records.php?reserve=' . $a["reserve_ID"] . '">' . $a["reserve_name"] .'</a></td>';
				echo '</tr>';
		}
	echo '</table>';

	//echo '<button onclick="window.location.href=/add_record.php">Add Reserve</button>';
	//echo '<button onclick="window.open("/add_reserve.php", "_blank")>Add Reserve</button>';
	//echo '<button onclick="window.location.href=/edit_list.php">Edit List</button>';
	echo '<a href="add_reserve.php">Add Reserve</a>';
	}
else{
	echo 'CONNECTION ERROR';
}
?>


</div>
</body>
</html>