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
String deletedProductId=request.getParameter("deleted");
if(deletedProductId!=null){
	Product product=new Product();
	product.setId(Long.parseLong(deletedProductId));
	pm.delete(product);
	products=pm.getAll();
	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Summary</title>
</head>
<body>
	<h3>Category Summary</h3>
	<h4>
		Deleted = <%=deletedProductId %>
		</h4>
	<form name="tableForm" action="#">
		<input type="hidden" name="deleted">
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
				String productCategory = category == null ? "" : category.getName();
				String ProductImageUrl = product.getImageUrl();
				String categoryLink = String.valueOf("/admin/ProductDetail.jsp?id=" + productID);
			%>
			<tr>
				<td><%=productID%></td>
				<td><%=productName%></td>
				<td><%=productPrice%></td>
				<td><%=productCategory%></td>
				<td><img alt="" src="<%=ProductImageUrl%>"></td>
				<td>detail</td>
				<td><input type="button" value="delete"
					onclick="{document.tableForm.deleted.value=<%=productID%>;tableForm.submit();	}"></td>

			</tr>
			<%
			}
			%>
		</table>
	</form>

	<br />


	<script type="text/javascript">
	
</script>
</body>
</html>