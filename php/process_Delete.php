<?php
session_start();
if(isset ($_GET["sid"]))
{
    $sid=$_GET["sid"];
    //Step 1: make connection with database server.
$con=mysqli_connect("localhost" , "root","","my_database");// sql is structured query language
//Step 2: Write the query...Crud operation..C for create R for retrieve U for update d for delete 
//Create one record in a table...(delete)remove any no of record in a table...  
$query="delete from student where std_id='$sid'";
//Step 3 send query to the database using connection details
$qry_result=mysqli_query($con , $query);
if(isset($qry_result))
    {   
        $_SESSION["msg_delete"]="Record deleted";
        header("location: records.php");
    }   

}
else{
    $_SESSION["msg_delete"]="Choose a record to delete";
    header("location: records.php");
}