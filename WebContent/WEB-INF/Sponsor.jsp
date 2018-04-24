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
<title>Sponsor project ${entry.title}</title>
</head>
<body>
<center>
				<a>Sponsor project <b>${entries.title}</b></a>
				 <c:if test="${entries.rewards!=null}">
				
				<c:forEach items ="${entries.rewards}" var="entry">
				<br><br><input type="radio" name="amount" value="amount">
				<a>Pledge Amount: ${entry.rewardAmount} </a>
				<br><a>Reward Description: ${entry.rewardDescription}</a>
				</c:forEach>
				</c:if>
				<form action="Sponsor" method="post">
				<input type="hidden" name="projectNo" value="${entries.id}">
				<br><a>Pledge Amount:<input size="10" name="amount"
				 ></input>
				 <br><input type="submit" value="next" name="next">
				</form>
				

</center>
</body>
</html>