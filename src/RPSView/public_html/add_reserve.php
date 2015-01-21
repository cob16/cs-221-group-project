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

    echo 'Add a new reserve:';
    echo '<form action="index.php" method="post">';
    echo '<input type="text" name="reserve_name" placeholder="Reserve Name"></input>';
    echo '<input type="submit" value="Add"></input>';
    echo '</form>';
    }
?>

    </div>
  </body>
</html>
