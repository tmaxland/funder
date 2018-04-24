<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Projects</title>
</head>
<body>
<center>
<p>Projects</p>
	
	
	<table border = 1>
	<tr>
		<th>Project</th>
		<th>Posted By</th>
		<th>Start Date</th>
		<th>Days to Go</th>
		<th>Amount Pledged</th>
		<th>Percentage</th>
	</tr>
	<c:forEach items = "${entries}" var="entry">
	<tr>
		<!--  <td style="text-align:center"><a href="DisplayProject?projectNo=${entry.id}">${entry.title}</a></td>
		-->
		
		<td style="text-align:center"><a href="DisplayProject?projectNo=${entry.id}">
		${entry.title}</a></td>
		<td style="text-align:center">${entry.postedBy}</td>
		<td style="text-align:center"><fmt:formatDate value="${entry.startDate}" 
		pattern ="MMM dd, yyyy"></fmt:formatDate></td>
   		<td style="text-align:center">${entry.daysToGo}</td>
		<td style="text-align:center">${entry.amountFunded}</td>
		<td style="text-align:center">${entry.percentage} %</td>
						
	</tr>
	</c:forEach>
	<tr>
		<td  colspan="6" style="text-align: right;" ><a href="AddProject"> Add Project </a></td>
	</tr>
	
	</table>
	<c:choose>
		<c:when test ="${empty sessionScope.user}">
		<br><a href="Login">Login</a>
		<br><a href="SignUp">SignUp </a>
		</c:when>
		
		<c:otherwise>
			<br></>Logged in user is:<b>${user.username}</b>
			<br><a href="Logout">Logout</a>	
		</c:otherwise>
	
	</c:choose>
		
		
		
		
		
</center>

</body>
</html>