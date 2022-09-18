<%@page import="com.bootcamp.portal.CategoryClient"%>
<%@page import="com.bootcamp.portal.ProductClient"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="com.bootcamp.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
		boolean redirectToLogin = false;
		String username = session.getAttribute("username")!=null? String.valueOf(session.getAttribute("username")):null;
		String password = session.getAttribute("password")!=null?String.valueOf(session.getAttribute("password")):null;
		long cartId= session.getAttribute("cartid")!=null?(long)session.getAttribute("cartid"):0;
	
		if(username==null ||password==null){
			redirectToLogin = true;
		}
		
		List<Category> categories = CategoryClient.getAll();
		session.setAttribute("categories", categories);
	%>
<!DOCTYPE html>
<link href="./css/nav.css" rel="stylesheet" type="text/css">
<nav class="navbar">
	<div class="nav">
		<a style="all: unset" href="/portal/MainPage.jsp">
		<h3>Bootcamp Store</h3>
		</a>
		<div class="nav-items">

			<a href="#"><img width="10px"
				src="./icons/user.png" alt=""></a>
			<a href=<%="/portal/CartView.jsp"%>><img width="10px"
				src="./icons/cart.png" alt=""></a>
		</div>
	</div>

	<ul class="links-container">

		<%	for (int i=0;i<5;i++) {	%>
		<% 
			if(i<categories.size()){
			Category category= categories.get(i); 
		%>
		<li class="link-item"><a
			href="/portal/ProductList.jsp?id=<%=category.getId() %>" class="link"><%=category.getName()%></a></li>
		<% 	} }	%>
		
		<li class="link-item"><a href="/portal/CategoryList.jsp"
			class="link">All Categories</a></li>
	</ul>

</nav>