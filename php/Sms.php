<?php 

    $con = mysqli_connect("localhost", "qazx1110", "qazx9911!", "qazx1110");
    mysqli_query($con,'SET NAMES utf8');

    $Sender = $_POST["Sender"];
    $Contents = $_POST["Contents"];
    $Times = $_POST["Times"];
    
    $statement = mysqli_prepare($con, "INSERT INTO SMS VALUES (?,?,?)");
    mysqli_stmt_bind_param($statement, "sss", $Sender, $Contents, $Times);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;
    
    echo json_encode($response);

?>