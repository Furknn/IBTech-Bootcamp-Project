<%@page import="com.bootcamp.entity.User"%>
<%@page import="com.bootcamp.portal.UserClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% 
		String username=request.getParameter("uname");
		String password=request.getParameter("pword");
		if(username!=null&&password!=null){
			User user=new User();
			user.setPassword(password);
			user.setUsername(username);
			if(UserClient.checkUser(user)){
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				response.sendRedirect("/portal/MainPage.jsp");
			}
			else
			{
				session.setAttribute("username", null);
				session.setAttribute("password", null);
			}
				
		}
		
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/user.css" rel="stylesheet" type="text/css">
<title>User Login</title>
</head>
<body>
<jsp:include page="./common/UserNavigator.jsp"></jsp:include>

	<div class="form-container">
		<form action="#" method="post" name="loginForm">
			<div class="title">Login</div>
			<br/><br/><br/>

			<div class="text-input">
				<label>Username</label> <input type="text" name="uname" placeholder="Username">
			</div>
			
			<br/><br/><br/>
			<div class="text-input">
				<label>Password</label> <input type="password" name="pword" placeholder="Password">
			</div>
			
			<div class="buttons">
				<span class="button" onclick="{loginForm.submit();}">
					Login
				</span >
				
				<span class="button" onclick="location.href='/portal/UserRegister.jsp'">
					Register ->
				</span>
			</div>

		</form>
	</div>

</body>
</html>