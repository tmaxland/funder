<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add your Reward</title>
</head>
<body>
<center>
	<a>Add Rewards for your project</a>
	
	<table border ="1">
	<form action="AddReward" method="post">
	<input type="hidden" name="projectNo" value="${projectNo}">
		<tr>
		
			<td>Pledge Amount $</td>
			<td><input type="text" name ="amount" size="10" ></td>
		</tr>
		<tr>
			<td style="vertical-align:top">Reward Description</td>
			<td><textarea cols="40" rows=4"" name ="description" ></textarea></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: right;"><input type="submit"
			name="add" value="add"><input type="submit"
			name="finish" value="finish"></td>
			
		</tr>
	
	</form>
	
	
	</table>


</center>
</body>
</html>