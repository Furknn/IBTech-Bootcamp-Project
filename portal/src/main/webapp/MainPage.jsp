<%@page import="com.bootcamp.portal.CategoryClient"%>
<%@page import="com.bootcamp.portal.ProductClient"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="com.bootcamp.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Category> categories = CategoryClient.getAll();
List<Product> products = ProductClient.getByCategoryId(categories.get(0).getId());

products = products.subList(0, products.size()<8?products.size():8);
%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/main.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>

	<jsp:include page="./common/Navigator.jsp"></jsp:include>
	<section class="product">
		<h2 class="product-category">best selling</h2>

		<div class="product-container">
			<%
			for (Product product : products) {
			%>
			<div class="product-card">
				<a style="all: unset"
					href="/portal/ProductView.jsp?id=<%=product.getId()%>">
					<div class="product-image">
						<img src="<%=product.getImageUrl()%>" class="product-thumb" alt="">
					</div>
					<div class="product-info">
						<h2 class="product-brand"><%=product.getName()%></h2>
						<%String detail=product.getDetail(); %>
						<p class="product-short-des"><%=product.getDetail().substring(0,detail.length()>20?20:detail.length())+"..."%></p>
						<span class="price"><%=product.getPrice()%> TL</span>
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