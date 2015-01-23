<?
/**
 * Created by PhpStorm.
 * User: cormac
 * Date: 1/22/2015
 * Time: 1:05 PM
 */

//include 'connect.php'; //gets us a connection in var 'conn'
include '../connect.php';

//get table
$sql = "SELECT Species FROM Species";
$result_species = $conn->query($sql) or die(mysql_error($conn));

//makes shure we got somthing from the databace
if (!$result_species) { //cheack the result_species
    die('Invalid query: ' . mysql_error());
}

//uncomnmnet if you get memory errors!
//ini_set('memory_limit', '256M');

//make the result_species into an array
$i=0;
while( $row=mysqli_fetch_array($result_species) ) {
    $response[$i]  = $row['Species'];
    $i=$i+1;
};

//encode into jason and wtire to file
$export_file_name = 'species.json';
file_put_contents($export_file_name, "{\"list\":" . json_encode($data) . "}");

echo "made file: $export_file_name <br>";

?>