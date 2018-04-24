'<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
	<center>
		<h4>Welcome New User!!</h4>
		<form method='post'>
			<table border='1'>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Retype Password:</td>
					<td><input type="password" name="rpassword" /></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="fName" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lName" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
					<input type="submit" value="register" name="Register"></td>

				</tr>
			</table>
		</form>
		${comment}
		<br><a href="Project">Project</a>
	</center>
</body>
</html>