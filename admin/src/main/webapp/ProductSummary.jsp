<%@page import="com.bootcamp.manager.ProductManager"%>
<%@page import="com.bootcamp.entity.Product"%>
<%@page import="com.bootcamp.manager.CategoryManager"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ProductManager pm = new ProductManager();
    	CategoryManager cm = new CategoryManager();
    	List<Product> products = pm.getAll();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Summary</title>
</head>
<body>
<h3>Category Summary</h3>
	<table border="1">
		<tr>
			<th>Product ID</th>
			<th>Product Name</th>
			<th>Product Price</th>
			<th>Product Category</th>
			<th>Product Image</th>
			<th></th>
			<th></th>
		</tr>
		<%
		for (Product product : products) {
			String productID = String.valueOf(product.getId());
			String productName = product.getName();
			String productPrice = String.valueOf(product.getPrice());
			Category category = cm.getById(product.getCategoryId());
			String productCategory = category==null?"":category.getName();
			String ProductImageUrl = product.getImageUrl();
			String categoryLink = String.valueOf("/admin/ProductDetail.jsp?id=" + productID);
		%>
		<tr>
			<td><%=productID%></td>
			<td><%=productName%></td>
			<td><%=productPrice%></td>
			<td><%=productCategory%></td>
			<td><img alt="" src="<%=ProductImageUrl%>"></td>
			<td><button>details</button></td>
			<td><button>delete</button></td>
		</tr>
		<%
		}
		%>
	</table>
	<br />

</body>
</html>