<?php
$con=mysqli_connect("localhost","root","","vocal");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
echo $idGram = $_GET['gramid'];

 $qz = "SELECT * FROM mot where mot_grammer=".$idGram; 
$tabMots = array();
$result = mysqli_query($con,$qz);
while($row = mysqli_fetch_array($result))
  { 
	$tabMots[] = $row[1];
  }
 
 echo $msg='#JSGF V1.0;

/**
 * JSGF Grammar
 */

grammar grammar;

public <feelings>  = ( ' .join(' | ',$tabMots).' );


';
  mysqli_close($con);
?>