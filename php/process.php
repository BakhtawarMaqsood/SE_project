<?php
session_start();
if(isset($_POST["submit_form"]))
{
$sfn= $_POST["sfn"];
$fn= $_POST["fn"];
$em= $_POST["em"];
$pn= $_POST["pn"];
$g= $_POST["g"];
$Country= $_POST["Country"];
//Step 1: make connection with database server.
$con=mysqli_connect("localhost" , "root","","my_database");// sql is structured query language
//Step 2: Write the query...Crud operation..C for create R for retrieve U for update d for delete 
//Create one record in a table...(delete)remove any no of record in a table...  
$query="insert into student(std_name, std_fname , std_email , std_gender ,  std_country, std_mobile_number) values('$sfn' , '$fn' , '$em' , '$g' ,'$Country', '$pn')";
//Step 3 send query to the database using connection details
$qry_result=mysqli_query($con , $query);
if(isset($qry_result)){
echo "Query Occured";

}
else{//echo "Some error occured";

}
}

else
{
//echo "Invalid access";
$_SESSION["msg"]="Invaid Access";
header("location:index.php");
}
?>