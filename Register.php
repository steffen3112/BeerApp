<?php
 require("password.php");
 
    $db_name = "beerappdb";
	$mysql_username = "root";
	$mysql_password = "12345";
	$server_name = "localhost";
	
    $connect = mysqli_connect($server_name, $mysql_username, $mysql_password,$db_name);
    
    $name = $_POST["vorname"];//in android!!
    $surname = $_POST["nachname"];//in android!!
    $email = $_POST["email"];//in android!!
    $age = $_POST["age"];//in android!!
    $password = $_POST["password"];//in android!!
     function registerUser() {
        global $connect, $name, $surname, $email, $age, $password;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO user_data (Vorname, Nachname, Email, Age, Passwort) VALUES (?, ?, ?, ?,?)");
        mysqli_stmt_bind_param($statement, "sssis", $name, $surname, $email, $age, $passwordHash);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
     function emailAvailable() {
        global $connect, $email;
        $statement = mysqli_prepare($connect, "SELECT * FROM user_data WHERE Email = ?"); 
        mysqli_stmt_bind_param($statement, "s", $email);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }
    $response = array();
    $response["success"] = false;  
    if (emailAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
    echo json_encode($response);
?>