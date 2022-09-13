<%@page import="com.bootcamp.entity.Category"%>
<%@page import="com.bootcamp.portal.CategoryClient"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Category> categories = CategoryClient.getAll();
%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/categorylist.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Category List</title>
</head>
<body>
	<jsp:include page="./common/Navigator.jsp"></jsp:include>

	<section class="category">
		<h2 class="page-title">Categories</h2>
		<div class="category-container">

			<%
			for (Category category : categories) {
			%>
			<div class="category-card">
				<a style="all: unset" href="/portal/ProductList.jsp?id=<%=category.getId() %>">
					<div class="category-info">
						<h2 class="category-name">
							<%=category.getName()%></h2>
							<%String detail=category.getDetail(); %>
							
						<p class="category-short-des"><%=category.getDetail().substring(0,detail.length()>20?20:detail.length())+"..." %></p>
						<span class="count"> 123 Products </span>
					</div>
				</a>
			</div>
			<%
			}
			%>

		</div>
	</section>

</body>
</html>