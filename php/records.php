
<?php
    session_start();
    if(isset($_SESSION["msg_edit"])){
        echo $_SESSION["msg_edit"];
        unset($_SESSION["msg_edit"]);
    }
    if(isset($_SESSION["msg_process_edit"])){
        echo $_SESSION["msg_process_edit"];
        unset($_SESSION["msg_process_edit"]);
    }
    if(isset($_SESSION["msg_delete"])){
        echo $_SESSION["msg_delete"];
        unset($_SESSION["msg_delete"]);
    }

?>

<html>
<head>
<title>Student Record</title>
</head>
<body> 
<center> 
<table border="1"> 
<tr>
<th>Student name</th>
<th>Fathers Name</th>
<th>Eamil</th>
<th>Gender</th>
<th>Country</th>
<th>phome number</th>
<th>Date time</th>

</tr>
<?php
    //connection
    $con=mysqli_connect("localhost" , "root" , "" , "my_database");
    //query
    $query="select * from student";
    //queryResult
    $queryResult=mysqli_query($con , $query);
    if(isset($queryResult) && $queryResult !="")
    { //fetch array function takes the data from the databse table copy. it takes first row,convert into associated array(we define index).
        //it converts the column names into index and values are in rows 
        while($row=mysqli_fetch_array($queryResult))
        {
    

?>
<tr>
<td><?php   echo $row["std_name"];         ?></td>
<td><?php   echo $row["std_fname"];         ?></td>
<td><?php   echo $row["std_email"];         ?></td>
<td><?php   echo $row["std_gender"];         ?></td>
<td><?php   echo $row["std_country"];         ?></td>
<td><?php   echo $row["std_mobile_number"];         ?></td>
<td> <a href="Edit.php?sid=<?php echo  $row["std_Id"];?>">Edit</a> | <a href="process_Delete.php?sid=<?php echo  $row["std_Id"];?>" onClick= "return confirm('Are you sure?');">Delete</a> </td>
</tr>


<?php
    }
    }

?>



</table>
<a href="index.php">Home</a>
</center>
</body>
</html>