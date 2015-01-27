<?
/**
 * Created by PhpStorm.
 * User: cormac
 * Date: 1/22/2015
 * Time: 1:05 PM
 */

//include 'connect.php'; //gets us a connection in var 'conn'
include '../connect.php';

$sql = "SELECT Species FROM Species";
$result_Species = $conn->query($sql) or die(mysql_error($conn));

if (!$result_Species) { //cheack the result_species
    die('Invalid query: ' . mysql_error());
}

//ini_set('memory_limit', '2056M');

$i=0;
while( $row=mysqli_fetch_array($result_Species) ) {
    $data[$i] = $row['Species'];
    //echo "</p>"  . $data[$i] . "</p>";
    $i = $i + 1;
}

$export_file_name = 'species.json';
file_put_contents($export_file_name, "{\"list\":" . json_encode($data) . "}");

echo "made file: $export_file_name <br>";

?>

