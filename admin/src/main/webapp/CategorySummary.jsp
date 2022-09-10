<%@page import="com.bootcamp.manager.CategoryManager"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// get categories
	CategoryManager cm = new CategoryManager();
	List<Category> categories = cm.getAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Summary</title>
</head>
<body>
	<h3>Category Summary</h3>
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
			String categoryLink = String.valueOf("/admin/CategorySummary.jsp?id=" + categoryID);
		%>
		<tr>
			<td><%=categoryID%></td>
			<td><%=categoryName%></td>
			<td><button>details</button></td>
			<td><button>delete</button></td>
		</tr>
		<%
		}
		%>
	</table>
	<br />
	<form action="#">
		<label>Category Name</label><br /> <input type="text"
			name="categoryname"> <input type="submit" name="add"
			title="add">
	</form>
</body>
</html>