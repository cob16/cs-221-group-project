<?php 
// include 'header.html';/* this adds the header.html file
// to the beginning of the file, do not remove */
include 'connect.php';
include "header.php";
 ?>

	<div class="container">

		<h1>
			<a href="add_reserve.php"><button type="button" id="addButton" class="btn btn-success" >Add Reserve</button></a>
			<a href="edit_list.php"><button type="button" id="editButton" class="btn btn-primary">Edit List</button></a>
		</h1>
			<div id="cp_widget_4c2ef66a-c1f6-46dc-b411-fc25563139f8">...</div><script type="text/javascript">
var cpo = []; cpo["_object"] ="cp_widget_4c2ef66a-c1f6-46dc-b411-fc25563139f8"; cpo["_fid"] = "AACAzTcZg8fc";
var _cpmp = _cpmp || []; _cpmp.push(cpo);
(function() { var cp = document.createElement("script"); cp.type = "text/javascript";
cp.async = true; cp.src = "//www.cincopa.com/media-platform/runtime/libasync.js";
var c = document.getElementsByTagName("script")[0];
c.parentNode.insertBefore(cp, c); })(); </script><noscript>Powered by Cincopa <a href='http://www.cincopa.com/video-hosting'>Video Hosting for Business</a> solution.<span>New Gallery 2015/1/22</span><span>flash</span><span> 16</span><span>cameramake</span><span> Panasonic</span><span>height</span><span> 1372</span><span>orientation</span><span> 1</span><span>camerasoftware</span><span> Ver.1.0  </span><span>originaldate</span><span> 7/16/2011 11:03:45 AM</span><span>width</span><span> 2232</span><span>cameramodel</span><span> DMC-LX5</span><span>originaldate</span><span> 1/1/0001 6:00:00 AM</span><span>width</span><span> 1160</span><span>height</span><span> 770</span><span>originaldate</span><span> 1/1/0001 6:00:00 AM</span><span>width</span><span> 1600</span><span>height</span><span> 1067</span><span>flash</span><span> 16</span><span>cameramake</span><span> SAMSUNG</span><span>height</span><span> 1536</span><span>orientation</span><span> 1</span><span>camerasoftware</span><span> 1009141</span><span>originaldate</span><span> 9/17/2012 5:15:51 PM</span><span>width</span><span> 2048</span><span>cameramodel</span><span> SAMSUNG WB650 / VLUU</span><span>cameramake</span><span> Canon</span><span>height</span><span> 2568</span><span>orientation</span><span> 1</span><span>flash</span><span> 16</span><span>originaldate</span><span> 8/8/2012 7:12:14 PM</span><span>width</span><span> 3872</span><span>cameramodel</span><span> Canon EOS 400D DIGIT</span></noscript>

<hr>
		<?php

		//ADD NEW RESERVE
		if (isset($_POST['reserve_name']))
		{
			$new_record = $_POST['reserve_name'];
			if(!$conn->connect_errno > 0){
				$add = 'INSERT INTO Reserves (reserve_name) VALUES ("' . $new_record . '")';
				if(!$add = $conn->query($add)){
					die('There was an error running the query [' . $db->error . ']');
				}
			}
		}

		if(!$conn->connect_errno > 0){ //if connection was successful
			$res = 'SELECT * FROM Reserves';
			if(!$res = $conn->query($res)){
				die('There was an error running the query [' . $db->error . ']');
			}


		//BUILDS TABLE
		echo '<table cellpadding="25">';
		echo '<thead>';
		echo '<tr>';
		echo '<th>Reserves</th>';
		echo '</tr>';
		//PRINTS RESERVES
		while ($a = $res->fetch_assoc()) {
			echo '<tr>';
			echo '<td><a href="records.php?reserve=' . $a["reserve_ID"] . '">' . ucwords($a["reserve_name"]) .'</a></td>';
			echo '</tr>';
		}
		echo '</table>';

		}
		else
		{
			echo "connection error";
		}
?>


</div>
</body>
</html>
