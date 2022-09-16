<%@page import="com.bootcamp.manager.CartProductManager"%>
<%@page import="com.bootcamp.entity.CartProduct"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	List<CartProduct> cartProducts = CartProductManager.getInstance().getByCartId(Long.valueOf(request.getParameter("cartid")));
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Detail</title>
</head>
<body>
<a href="/admin/CartSummary.jsp"><input type="button" value="back"></a>
	<div style="width: 100%;">
		<div style="float: left; width: 50%">
			<h3>Cart Detail</h3>
			<form name="tableForm" action="#">
				<input type="hidden" name="deleted">
				<table border="1">
					<tr>
						<th>CartProduct ID</th>
						<th>CartProduct CartId</th>
						<th>CartProduct ProductId</th>
						<th>CartProduct Quantity</th>
						<th>CartProduct Price</th>
						<th>CartProduct TaxRate</th>
						<th>CartProduct LineAmount</th>
					</tr>
					<%
					for (CartProduct cp : cartProducts) {
						String cartProductId = String.valueOf(cp.getId());
						String cartId = String.valueOf(cp.getCartId());
						String productId = String.valueOf(cp.getProductId());
						String quantity = String.valueOf(cp.getQuantity());
						String price = String.valueOf(cp.getPrice());
						String taxRate = String.valueOf(cp.getTaxRate());
						String lineAmount = String.valueOf(cp.getLineAmount());
					%>
					<tr>
						<td><%=cartProductId%></td>
						<td><%=cartId%></td>
						<td><%=productId%></td>
						<td><%=quantity%></td>
						<td><%=price%></td>
						<td><%=taxRate%></td>
						<td><%=lineAmount%></td>
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