<%@page import="com.bootcamp.portal.CategoryClient"%>
<%@page import="com.bootcamp.portal.ProductClient"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="com.bootcamp.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		long productId=Long.parseLong(request.getParameter("id"));
		Product product=ProductClient.getById(productId);
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product View</title>
</head>
<body>
	<jsp:include page="./common/Navigator.jsp"></jsp:include>
	<section class="product" >
		
	</section>
	
</body>
</html>