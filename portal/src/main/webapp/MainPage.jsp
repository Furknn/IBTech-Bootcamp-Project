<%@page import="com.bootcamp.manager.CategoryManager"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    CategoryManager categoryManager = CategoryManager.getInstance();
    List<Category> categories = categoryManager.getAll();
%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/main.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>
	<nav class="navbar">
		<div class="nav">
			<h3>Bootcamp Store</h3>
			<div class="nav-items">

				<a href="#"><img width="10px"
					src="https://cdn-icons-png.flaticon.com/512/1144/1144760.png"
					alt=""></a> <a href="#"><img width="10px"
					src="https://cdn-icons-png.flaticon.com/512/3144/3144456.png"
					alt=""></a>
			</div>
		</div>

		<ul class="links-container">

			<% for (Category category : categories) { %>
			<li class="link-item"><a href="#" class="link"><%=category.getName()%></a></li>
			<%} %>
		</ul>

	</nav>

	<section class="product">
		<h2 class="product-category">best selling</h2>
		
		<div class="product-container">
		<% for(int i=0;i<7;i++){ %>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> 
					<img src="https://images.arcteryx.com/S22/1350x1710/Acrople-Jacket-Canvas.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to whislist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">brand</h2>
					<p class="product-short-des">a short line about the cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<%} %>
		</div>
		
	</section>


</body>
</html>