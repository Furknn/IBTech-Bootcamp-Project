<%@page import="com.bootcamp.manager.CategoryManager"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// get categories
CategoryManager cm = CategoryManager.getInstance();
List<Category> categories = cm.getAll();
String deletedCategoryId = request.getParameter("deleted");
if (deletedCategoryId != null) {
	Category category = new Category();
	cm.delete(Long.parseLong(deletedCategoryId));
	categories = cm.getAll();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Summary</title>
</head>
<body>
	<a href="/admin/AdminPage.jsp"><input type="button" value="back"></a>
	<div style="width: 100%; float: left;">
		<div style="float: left; width: 50%">
			<h3>Category Summary</h3>
			<form name="tableForm" action="#">
				<input type="hidden" name="deleted">
				<table border="1">
					<tr>
						<th>Category ID</th>
						<th>Category Name</th>
						<th></th>
						<th></th>
					</tr>
					<%
					for (Category category : categories) {
						String categoryID = String.valueOf(category.getId());
						String categoryName = category.getName();
						String categoryLink = String.valueOf("/admin/CategoryDetail.jsp?id=" + categoryID);
					%>
					<tr>
						<td><%=categoryID%></td>
						<td><%=categoryName%></td>
						<td><a href="<%=categoryLink%>"> <input type="button"
								value="detail">
						</a></td>
						<td><input type="button" value="delete"
							onclick="{document.tableForm.deleted.value=<%=categoryID%>;tableForm.submit();}"></td>
					</tr>
					<%
					}
					%>
				</table>
			</form>
			<br />


		</div>
		<div style="float: left;">
			<h3>Category Create</h3>
			<form action="#">
				<label>Category Name</label><br /> <input type="text"
					name="categoryname"> <input type="submit" value="add">
			</form>
		</div>
	</div>
	<div style="clear: both"></div>
</body>
</html>