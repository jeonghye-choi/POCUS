<?php 
 
    $con= mysqli_connect("localhost","qazx1110","qazx9911!","qazx1110"); 
    
    if (mysqli_connect_errno($con))
    {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

    mysqli_set_charset($con,"utf8");

    $res = mysqli_query($con,"select * from DISASTERMESSAGE");
 
    $result_disas = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result_disas,
	array('message'=>$row[2]
	));
    } 
       
    echo json_encode(array("result_disas"=>$result_disas));
 
    mysqli_close($con);
 
?>
