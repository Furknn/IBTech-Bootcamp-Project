<%@page import="com.bootcamp.manager.CartManager"%>
<%@page import="com.bootcamp.entity.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Cart> carts = CartManager.getInstance().getAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Summary</title>
</head>
<body>
	<a href="/admin/AdminPage.jsp"><input type="button" value="back"></a>
	<div style="width: 100%;">
		<div style="float: left; width: 50%">
			<h3>Cart Summary</h3>
			<form name="tableForm" action="#">
				<input type="hidden" name="deleted">
				<table border="1">
					<tr>
						<th>Cart ID</th>
						<th>Cart CustomerName</th>
						<th>Cart TotalAmount</th>
						<th></th>
					</tr>
					<%
					for (Cart cart : carts) {
						String cartId = String.valueOf(cart.getId());
						String cartUserName = cart.getCustomerName();
						String totalAmount = String.valueOf(cart.getTotalAmount());
						String cartUrl = "/admin/CartDetail.jsp?cartid=" + cartId;
					%>
					<tr>
						<td><%=cartId%></td>
						<td><%=cartUserName%></td>
						<td><%=totalAmount%></td>
						<td><a href="<%=cartUrl%>"><input type="button"
								value="detail"></a></td>
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