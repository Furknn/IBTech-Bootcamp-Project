<%@page import="com.bootcamp.manager.UserManager"%>
<%@page import="com.bootcamp.entity.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    	List<User> users = UserManager.getInstance().getAll();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Summary</title>
</head>
<body>
	<a href="/admin/AdminPage.jsp"><input type="button" value="back"></a>
	<div style="width: 100%;">
		<div style="float: left; width: 50%">
			<h3>User Summary</h3>
			<form name="tableForm" action="#">
				<input type="hidden" name="deleted">
				<table border="1">
					<tr>
						<th>User ID</th>
						<th>User Username</th>
						<th>User Password</th>
						<th></th>
					</tr>
					<%
					for (User user : users) {
						String userId = String.valueOf(user.getId());
						String username = user.getUsername();
						String password = user.getPassword();
						String userUrl = "/admin/UserDetail.jsp?userid=" + userId;
					%>
					<tr>
						<td><%=userId%></td>
						<td><%=username%></td>
						<td><%=password%></td>
						<td><a href="<%=userUrl%>"><input type="button"	value="detail"></a></td>
					</tr>
					<%
					}
					%>
				</table>
			</form>
			<br />
		</div>
	</div>

</body>
</html>