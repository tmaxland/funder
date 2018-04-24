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
<title>Display Project</title>
</head>
<body>
<center>
		
		
		<b><a>${entries.title}</a></b>		
		<br><a>by ${entries.postedBy}</a>	
		<br><br><a>${entries.description}</a>
		
 		<br><br><b><a>Funding Target:</b> ${entries.fundingTarget}</a>
 		<br><a><b>Start Date:</b><fmt:formatDate value="${entries.startDate}" 
		pattern ="MMM dd, yyyy"></fmt:formatDate></td>
 		<br><b><a>Funding Duration: </b>${entries.fundingDuration}</a>
 		<br><b><a>Days to Go: </b>${entries.daysToGo}</a>
 		
 		<br><br><br><b><a>Rewards</b></a>
 		<br>
 		
 		<c:forEach items="${entries.rewards}" var="reward">
 		<br><b><a>Reward Amount:</b> ${reward.rewardAmount}</a>
 		<br><b><a>Reward Description:</b> ${reward.rewardDescription}</a>
 		</c:forEach>

		 <c:if test="${entries.sponsorLink == true}">
			<br><br><a href="Sponsor?projectNo=${entries.id}">Sponsor this project</a><br />
		</c:if> 
		
		<br><br /><a href="Project">Project</a><br />
		<c:choose>
		<c:when test ="${empty sessionScope.user}">
		<a href="Login">Login</a>
		</c:when>
		
		<c:otherwise>
			<br></>Logged in user is:<b>${user.username}</b>
			<br><a href="Logout">Logout</a>	
		</c:otherwise>
	
	</c:choose>
		
		
		
		
</center>
</body>
</html>