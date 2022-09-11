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
String deletedProductId = request.getParameter("deleted");
if (deletedProductId != null) {
	Product product = new Product();
	pm.delete(Long.parseLong(deletedProductId));
	products = pm.getAll();

}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Summary</title>
</head>
<body>
	<h3>Product Summary</h3>

	<div style="width: 100%;">
		<div style="float: left; width: 50%">

			<%
			if (deletedProductId != null) {
			%>
			<h4>
				Deleted =
				<%=deletedProductId%>
			</h4>
			<%
			}
			%>
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
						String productLink = String.valueOf("/admin/ProductDetail.jsp?id=" + productID);
					%>
					<tr>
						<td><%=productID%></td>
						<td><%=productName%></td>
						<td><%=productPrice%></td>
						<td><%=productCategory%></td>
						<td><img alt="" src="<%=ProductImageUrl%>"></td>
						<td><a href="<%=productLink%>"><input type="button"
								value="detail"></a></td>
						<td><input type="button" value="delete"
							onclick="{document.tableForm.deleted.value=<%=productID%>;tableForm.submit();}">
						</td>

					</tr>
					<%
					}
					%>
				</table>
			</form>
			<br />
		</div>
		<div style="float: left;"></div>
	</div>
	<div style="clear: both"></div>

</body>
</html>