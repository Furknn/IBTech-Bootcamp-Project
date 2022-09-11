<%@page import="com.bootcamp.manager.ProductManager"%>
<%@page import="com.bootcamp.manager.CategoryManager"%>
<%@page import="com.bootcamp.entity.Product"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	long id =Long.valueOf(request.getParameter("id"));
    	ProductManager pm= new ProductManager();
    	Product product= pm.getById(id);    
    	CategoryManager cm=new CategoryManager();
    	Category productCategory=cm.getById(product.getCategoryId());
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Detail</title>
</head>
<body>
<h3>Product Detail</h3>
<h5>Name = <%= product.getName() %></h5>
<p>ID = <%=product.getId() %></p>
<p>Price = <%=product.getPrice() %></p>
<p>category = <%=productCategory.getName() %></p>
<img alt="" src="<%=product.getImageUrl()%>">
</body>
</html>