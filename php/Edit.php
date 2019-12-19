<?php
session_start();
if(isset ($_GET["sid"])){
    $sid=$_GET["sid"];
    $con=mysqli_connect("localhost" , "root" , "" , "my_database");
    //query
    $query="select * from student where std_Id='$sid'";
    //queryResult
    $queryResult=mysqli_query($con , $query);
    if(isset($queryResult) && $queryResult !="")
    { //fetch array function takes the data from the databse table copy. it takes first row,convert into associated array(we define index).
        //it converts the column names into index and values are in rows 
        $row=mysqli_fetch_array($queryResult);
    













?>
<html>
<head>
<title>Update Student record</title>
<center><h1>Update record </h1></center>
</head>
        <body>
        <form action="process_edit.php" method="post">
		<table>
		 <tr>
		  <td>Student Full Name</td>
		  <td><input type="text" name="sfn" value="<?php echo $row["std_name"];?>"></td>
		 </tr>
		 <tr>
		  <td>Father's Name</td>
		  <td><input type="text" name="fn" value="<?php echo $row["std_fname"];?>"></td>
		 </tr>
		 <tr>
		  <td>Email</td>
		  <td><input type="email" name="em" value="<?php echo $row["std_email"];?>"></td>
		 </tr>
		  <td>Phone Number</td>
		  <td><input type="number" name="pn" value="<?php echo $row["std_mobile_number"];?>"></td>
		 </tr>
		 
		 <tr>
		  <td>Gender</td>
		  <td>
    Male<input type="radio" name="g" value="m"<?php if ($row["std_gender"]== 'm') {?>checked <?php }?>}>
			Female<input type="radio" name="g" value="f"<?php if ($row["std_gender"]== 'f') {?>checked <?php }?>}>
			Other<input type="radio" name="g" value="o"<?php if ($row["std_gender"]== 'o') {?>checked <?php }?>}>
		 </td>
		 </tr>
		  <tr>
		  <td>Select Country</td>
		  <td>
			<select name="Country">
				<option value="" >Select One option</option>
				<option value="SA" <?php if ($row["std_country"]== 'SA') {?>Selected <?php }?>} >Saudi Arabia</option>
				<option value="CH" <?php if ($row["std_country"]== 'CH') {?>Selected <?php }?>} >China</option>
				<option value="Pak" <?php if ($row["std_country"]== 'Pak') {?>Selected <?php }?>} >Pakistan</option>
			</select>
		 </td>
		 </tr>
		 <tr>
		<td>
		 <button type="Submit" name="edit_form" value="Update">Update</button>
		</td>
		</tr>
		<tr>
        <td colspan="2">
        <input type="hidden" name="sid" value="<?php echo $sid ; ?>">
        </td>
        </tr>
		</table>
	   </form>
        
        
        </body>
</html>
<?php
}
    }
else {
    $_SESSION["msg_edit"]="Choose a record to edit";
    header("location: records.php");
}
?>