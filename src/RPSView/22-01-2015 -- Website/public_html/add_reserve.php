<?php include 'header.html';/* this adds the header.html file
to the beginning of the file, do not remove */
include 'connect.php';
?>

<div class="container">
    <?php

    echo '<form action="index.php" method="post">';
    echo '<table cellpadding="25">';
    echo '<thead>';
    echo '<tr>';
    echo '<th>Add New Reserve:</th>';
    echo '</tr>';
    echo '<table cellpadding="25">';
    echo '<thead>';
    echo '<tr><td>';
    echo '<input type="text" name="reserve_name" placeholder="Reserve Name" />';
    echo '<input type="submit" value="Add" />';
    echo '</td></tr>';
    echo '</table>';
    echo '</form>';
    ?>

</div>
</body>
</html>
