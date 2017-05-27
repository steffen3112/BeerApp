<?php
require("password.php");

    $db_name = "beerappdb";
	$mysql_username = "root";
	$mysql_password = "12345";
	$server_name = "localhost";
	
    $connect = mysqli_connect($server_name, $mysql_username, $mysql_password,$db_name);
    
    $email = $_POST["email"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($connect, "SELECT * FROM user_data WHERE Email = ?");
    mysqli_stmt_bind_param($statement, "s", $email);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $Vorname, $Nachname, $Email, $Age, $Passwort);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
	if(password_verify($password, $Passwort)){
        $response["success"] = true;  
        $response["name"] = $Vorname;
		$response["surname"] = $Nachname;
		$response["email"] = $Email;
        $response["age"] = $Age;
        $response["password"] = $Passwort;
		}
    }
    
    echo json_encode($response);
?>