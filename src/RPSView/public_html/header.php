<?php
//Standard header for every page
echo'<!DOCTYPE html>';
echo'<html lang="en">';

echo'<head>';
    echo'<title>RPSRview</title>';
    echo'<meta charset="utf-8">';
    echo'<link rel="shortcut icon" type="image/png" href="images/favicon.ico"/>';
    echo'<link rel="stylesheet" type="text/css" href="css/style221.css" />';
    echo'<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,300,600,700" rel="stylesheet" type="text/css">';
    echo'<meta charset="UTF-8" />';

    //Link to Twitter Bootstrap
    echo'<!-- These files are needed so that I can use "Twitter Bootstrap"-->';
    echo'<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>';
    echo'<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>';
    echo'<link href="css/bootstrap.css" rel="stylesheet">';
    echo'<link href="js/bootstrap.min.js" rel="stylesheet">';
    echo'<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">';


echo'</head>';

echo'<body>';

//Navigation bar
echo'<div class="header">';
    echo'<h1><a class="navbar-brand" href="http://cormacbrady.info/~tkek/"><strong>RPSR</strong>view</a></h1>';
    echo'<div class="header-text"><p>Database of Reserves</p></div>';
    echo'<a class="pull-righty"  href="https://github.com/cob16/cs-221-group-project"> <img src="https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png" alt="" height="45" width="45"></a>';
echo'</div>';
?>