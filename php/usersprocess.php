<?php
session_start();
if(isset($_POST["submit_form"]))
{
$fn= $_POST["fn"];
$ln= $_POST["ln"];
$em= $_POST["em"];
$db= $_POST["db"];
$g= $_POST["g"];
$mn= $_POST["mn"];
$Country= $_POST["Country"];

$con=mysqli_connect("localhost" , "root","","my_database");
  
$query="insert into users (first_name, last_name , email , date_of_birth , gender ,  mobile_no, country) values('$fn' , '$ln' , '$em' ,'$db' '$g' , '$mn' ,'$Country' )";

$qry_result=mysqli_query($con , $query);
if(isset($qry_result)){
echo "Query Occured";

}
    else
         {//echo "Some error occured";
         }

}
else
{
//echo "Invalid access";
$_SESSION["msg"]="Invaid Access";
header("location:users.php");
}
?>