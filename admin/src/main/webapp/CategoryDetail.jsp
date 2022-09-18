<%@page import="com.bootcamp.manager.CategoryManager"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
long categodyId = Long.parseLong(request.getParameter("id"));
CategoryManager categoryManager = CategoryManager.getInstance();
Category category = categoryManager.getById(categodyId);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Detail</title>
</head>
<body>
	<a href="/admin/ProductSummary.jsp"><input type="button"
		value="back"></a>
	<h3>Category</h3>
	<h5>
		Category ID:
		<%=category.getId()%></h5>
	<h5>
		Category Name:
		<%=category.getName()%></h5>
	<h5>
		Category Details:
		<%=category.getDetail()%>
	</h5>
</body>
</html>