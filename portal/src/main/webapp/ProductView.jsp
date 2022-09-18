<%@page import="com.bootcamp.portal.CategoryClient"%>
<%@page import="com.bootcamp.portal.ProductClient"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="com.bootcamp.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
long productId = Long.parseLong(request.getParameter("id"));
Product product = ProductClient.getById(productId);
%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/product.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Product View</title>
</head>
<body>
	<jsp:include page="./common/Navigator.jsp"></jsp:include>
		<div class="card">
			<div class="photo">
				<img
					src="<%=product.getImageUrl()%>">
			</div>
			<div class="description">
				<h2><%=product.getName() %></h2>
				<h1><%=product.getPrice() %> TL</h1>
				<p><%=product.getDetail() %></p>
				<button>Add to Cart</button>
			</div>
		</div>

</body>
</html>