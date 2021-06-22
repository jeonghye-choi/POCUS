<?php 
 
    $con= mysqli_connect("localhost","qazx1110","qazx9911!","qazx1110"); 
    
    if (mysqli_connect_errno($con))
    {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

    mysqli_set_charset($con,"utf8");

    $res = mysqli_query($con,"select * from SMS");
 
    $result_sms=array();

    while($row = mysqli_fetch_array($res)){

        array_push($result_sms,
	array('Contents'=>$row[1]
	));
    } 
       
    echo json_encode(array("result_sms"=>$result_sms));
 
    mysqli_close($con);
 
?>
