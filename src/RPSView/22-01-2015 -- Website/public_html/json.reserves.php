<?php
/**
 * Created by PhpStorm.
 * User: cormac
 * Date: 1/22/2015
 * Time: 4:22 PM
 */

include '../connect.php';

$sql = "SELECT reserve_name FROM Reserves ";
$result_reserves = $conn->query($sql) or die(mysql_error($conn));

if (!$result_reserves) { //cheack the result_species
    die('Invalid query: ' . mysql_error());
}

ini_set('memory_limit', '256M');

$i=0;
while( $row=mysqli_fetch_array($result_reserves) ) {
    $response[$i]['Reserves'] = $row['reserve_name'];

    $data['list'][$i] = $response[$i];
    $i = $i + 1;
}

$export_file_name = 'reserves.json';
file_put_contents($export_file_name, json_encode($data));

echo "made file: $export_file_name <br>";