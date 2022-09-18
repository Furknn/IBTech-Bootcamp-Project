<%@page import="com.bootcamp.manager.UserManager"%>
<%@page import="com.bootcamp.entity.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    User user = UserManager.getInstance().getById(Long.valueOf( request.getParameter("userid")));
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Detail</title>
</head>
<body>

	<a href="/admin/UserSummary.jsp"><input type="button"
		value="back"></a>
	<h3>User Detail</h3>
	<br/>
	<h5>
		ID =
		<%=user.getId()%></h5>
	<p>
		Username =
		<%=user.getUsername()%></p>
	<p>
		Password =
		<%=user.getPassword()%></p>

</body>
</html>