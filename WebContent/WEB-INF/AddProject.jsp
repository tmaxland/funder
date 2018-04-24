<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>AddProject</title>
</head>
<body>
	<center>
		<p>Add a new project here</p>
		<form action="AddProject" method="post">
			<!--add title, description, date  -->
			<table border="1">

				<tr>
					<td>Project Title :</td>
					<td><input type="text" name="title" maxlength="60"></td>
				</tr>
				<tr>
					<td><a style="vertical-align: top;">Project Description:</a></td>
					<td><a style="text-align: left;"> <textarea cols="40"
								rows="5" name="description"></textarea></a></td>
				</tr>


				<tr>
					<td>Funding Target :$</td>
					<td><input type="text" name="funding" maxlength="60"></td>
				</tr>
				<tr>
					<td>Start Date :</td>
					<td><input type="text" name="date" maxlength="60">(yyyy/MM/dd)</td>
				</tr>
				<tr>
					<td>Funding Duration :</td>
					<td><input type="text" name="duration" maxlength="60">Days</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
					<input type="submit" value="next" name="next"></td>

				</tr>
			</table>

		</form>
				<br><a href="Project">Projects</a>
		
		<c:choose>
		<c:when test ="${empty sessionScope.user}">
		<br><a href="Login">Login</a>
		</c:when>
		
		<c:otherwise>
			<br><br></>Logged in user is:<b>${user.username}</b>
			<br><a href="Logout">Logout</a>	
		</c:otherwise>
	
	</c:choose>
	</center>


</body>
</html>