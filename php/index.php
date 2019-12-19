<?php
session_start();
if(isset($_SESSION["msg"])){
echo $_SESSION["msg"];
unset($_SESSION["msg"]);
}
?>


<html>
  <head>
     <title>Second webpage</title>
  </head>
  <body> 
   	<a href="index.html">Back</a>
	<center>
           <h1>Registration Form</h1>
	   <form action="process.php" method="post">
		<table>
		 <tr>
		  <td>Student Full Name</td>
		  <td><input type="text" name="sfn"></td>
		 </tr>
		 <tr>
		  <td>Father's Name</td>
		  <td><input type="text" name="fn"></td>
		 </tr>
		 <tr>
		  <td>Email</td>
		  <td><input type="email" name="em"></td>
		 </tr>
		  <td>Phone Number</td>
		  <td><input type="number" name="pn"></td>
		 </tr>
		 
		 <tr>
		  <td>Gender</td>
		  <td>
			Male<input type="radio" name="g" value="m">
			Female<input type="radio" name="g" value="f">
			Other<input type="radio" name="g" value="o">
		 </td>
		 </tr>
		  <tr>
		  <td>Select Country</td>
		  <td>
			<select name="Country">
				<option value="" >Select One option</option>
				<option value="SA">Saudi Arabia</option>
				<option value="CH">China</option>
				<option value="Pak">Pakistan</option>
			</select>
		 </td>
		 </tr>
		 <tr>
		<td>
		 <button type="Submit" name="submit_form" value="Enter">Submit</button>
		</td>
		</tr>
		
		</table>
	   </form>
	</center>
	<a href="records.php" >Records</a>
  </body>
</html>