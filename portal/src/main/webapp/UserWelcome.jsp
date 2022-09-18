<%@page import="java.util.Iterator"%>
<%@page import="java.awt.Desktop.Action"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String username=(String)session.getAttribute("username");
    	boolean logout=request.getParameter("logout")!=null?true:false;
    	if(logout){
    		
    		
    		Iterator<String> iterator= session.getAttributeNames().asIterator();
    		while(iterator.hasNext()){
    			session.removeAttribute(iterator.next());
    		}
    		response.sendRedirect("/portal/MainPage.jsp");
    	}
    			
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/user.css" rel="stylesheet" type="text/css">
<title>User Welcome</title>
</head>
<body><jsp:include page="./common/UserNavigator.jsp"></jsp:include>
<div class="form-container">
		<form action="#" method="get" name="logoutForm">
			<div class="title">User</div>
			<br />
			<br />
			
				<span class="welcome">Welcome, <%=username %></span>
				<br/><br/>
				<input type="hidden" name="logout" >
				<span class="button" onclick="document.logoutForm.elements.logout.value=true;logoutForm.submit()">Log out</span>
			
		</form>
	</div>
</body>
</html>