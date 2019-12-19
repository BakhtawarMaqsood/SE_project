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
	<center>
           <h1>Sign Up Form</h1>
	   <form action="usersprocess.php" method="post">
		<table>
		 <tr>
		  <td>First Name</td>
		  <td><input type="text" name="fn"></td>
		 </tr>
		 <tr>
		  <td>Last Name</td>
		  <td><input type="text" name="ln"></td>
		 </tr>
		 <tr>
		  <td>Email</td>
		  <td><input type="email" name="em"></td>
		 </tr>
         <tr>
         <td>Date of Birth</td>
         <td><input type="date" name="db"> </td>
         </tr>
         <tr>
		  <td>Gender</td>
		  <td>
			Male<input type="radio" name="g" value="m">
			Female<input type="radio" name="g" value="f">
			Other<input type="radio" name="g" value="o">
		 </td>
		 </tr>
		 
		  <td>Mobile Number</td>
		  <td><input type="number" name="mn"></td>
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
  </body>
</html>