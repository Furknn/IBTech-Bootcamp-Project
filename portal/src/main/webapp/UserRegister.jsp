<%@page import="com.bootcamp.entity.User"%>
<%@page import="com.bootcamp.portal.UserClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String username = request.getParameter("uname");
String password = request.getParameter("pword");
if (username != null && password != null) {
	User user= new User();
	user.setUsername(username);
	user.setPassword(password);
	if(UserClient.createUser(user)){
		response.sendRedirect("/portal/UserLogin.jsp");
	}else{
		response.sendRedirect("/portal/UserRegister.jsp");
	}
	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/user.css" rel="stylesheet" type="text/css">
<title>User Register</title>
</head>
<body>
	<div class="form-container">
		<form action="#" method="post" name="loginForm">
			<div class="title">Register</div>
			<br />
			<br />
			<br />
			<div class="text-input">
				<label>Username</label> <input type="text" name="uname"
					placeholder="Username">
			</div>

			<br />
			<br />
			<br />
			<div class="text-input">
				<label>Password</label> <input type="password" name="pword"
					placeholder="Password">
			</div>

			<div class="buttons">
				<span class="button" onclick="{loginForm.submit();}">
					Register </span> <span class="button"
					onclick="location.href='/portal/UserLogin.jsp'"> Login </span>
			</div>

		</form>
	</div>
</body>
</html>