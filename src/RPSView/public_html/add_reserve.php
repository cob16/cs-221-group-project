<?phpinclude 'connect.php'; //standard database connection variables//ADD NEW RESERVEif (isset($_POST['add_reserve'])){    //Get data from HTTP post    $new_record = $_POST['reserve_name'];    $new_reference = strtoupper($_POST['grid_reference']); //Enforces the grid reference is entered in uppercase    $new_description = $_POST['description'];    if(!$conn->connect_errno > 0){        $add = 'INSERT INTO Reserves (reserve_name, grid_reference, description) VALUES ("' . $new_record . '", "' . $new_reference . '", "' . $new_description . '")';        //SQL statement to add a new reserve with the user's data        if(!$add = $conn->query($add)){            die('There was an error running the query [' . $db->error . ']');        }    }    header('Location: index.php'); //Redirects back to index after adding data    exit;}?><!--//Validates the user input, alerting to any errors and posts if no errors--><script>        function check_input(){        //Regular Expressions for validation        var gridRegExp = /([a-zA-Z]{2})+([0-9]{6})/; //Two letters followed by 6 numbers        var textRegExp = /[a-zA-Z 0-9]/; //Letters and numbers        if(textRegExp.test(document.details.reserve_name.value)            && gridRegExp.test(document.details.grid_reference.value)            && textRegExp.test(document.details.description.value))        {            document.details.setAttribute("method", "post");            document.details.setAttribute("action", "add_reserve.php");            //Sets valid form data to be posted back to this form, to be entered into database        }        else if(!textRegExp.test(document.details.reserve_name.value)){            alert("Invalid Reserve Name details entered");        }        else if(!gridRegExp.test(document.details.grid_reference.value)){            alert("Invalid grid reference entered, use a 6 figure OS grid reference" +            " (ie, two letters then 6 numbers");        }        else if(!textRegExp.test(document.details.description.value)){            alert("Invalid description entered");        }        //Warns user if invalid data was entered    }</script>