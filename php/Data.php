<?php 
 
    $con= mysqli_connect("localhost","qazx1110","qazx9911!","qazx1110"); 
    
    if (mysqli_connect_errno($con))
    {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

    mysqli_set_charset($con,"utf8");

    $res = mysqli_query($con,"select * from KEYWORD");
 
    $result=array();

    while($row = mysqli_fetch_array($res)){

        array_push($result,
	array('userKEY'=>$row[0]
	));
    } 
       
    echo json_encode(array("result"=>$result));
 
    mysqli_close($con);
 
?>
