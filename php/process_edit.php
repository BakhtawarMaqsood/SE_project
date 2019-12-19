<?php
session_start();
if(isset($_POST["edit_form"]))
{
$sfn= $_POST["sfn"];
$fn= $_POST["fn"];
$em= $_POST["em"];
$pn= $_POST["pn"];
$g= $_POST["g"];
$Country= $_POST["Country"];
$sid=$_POST["sid"];
//Step 1: make connection with database server.
$con=mysqli_connect("localhost" , "root","","my_database");// sql is structured query language
//Step 2: Write the query...Crud operation..C for create R for retrieve U for update d for delete 
//Create one record in a table...(delete)remove any no of record in a table...  
$query="update student set std_name='$sfn' , std_fname='$fn' , std_email='$em' , std_gender='$g' ,  std_country='$Country', std_mobile_number='pn' where std_Id='$sid'";
//Step 3 send query to the database using connection details
$qry_result=mysqli_query($con , $query);
if(isset($qry_result))
{
    $_SESSION["msg_process_edit"]="Record updated successfully";
    header("location: records.php");
}
else{
    $_SESSION["msg_process_edit"]="Some error occured";
    header("location:  records.php");
}
}
else
{
//echo "Invalid access";
$_SESSION["msg_process_edit"]="Invaid Access";
header("location:records.php");
}
?>