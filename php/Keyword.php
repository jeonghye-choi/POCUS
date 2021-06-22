<?php 

    $con = mysqli_connect("localhost", "qazx1110", "qazx9911!", "qazx1110");
    mysqli_query($con,'SET NAMES utf8');

    $userKey = $_POST["userKey"];
    
    $statement = mysqli_prepare($con, "INSERT INTO KEYWORD VALUES (?)");
    mysqli_stmt_bind_param($statement, "s", $userKey);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;
    
    echo json_encode($response);

?>