<%@page import="com.bootcamp.entity.Category"%>
<%@page import="com.bootcamp.portal.CategoryClient"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bootcamp.portal.ProductClient"%>
<%@page import="com.bootcamp.entity.Product"%>
<%@page import="com.bootcamp.portal.CartProductClient"%>
<%@page import="java.util.List"%>
<%@page import="com.bootcamp.entity.CartProduct"%>
<%@page import="com.bootcamp.entity.Cart"%>
<%@page import="com.bootcamp.portal.CartClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String cartId = session.getAttribute("cartid")!=null?String.valueOf((long) session.getAttribute("cartid")):null;
String username = (String) session.getAttribute("username");

if (cartId == null) {
	Cart cart = username == null ? CartClient.createCart() : CartClient.createCart(username);
	session.setAttribute("cartid", cart.getId());
	cartId = String.valueOf(cart.getId());
}else{
	session.setAttribute("cartid", Long.valueOf(cartId));
}
cartId="29";
List<CartProduct> cartProducts = CartProductClient.getCartProducts(Long.valueOf(cartId));
List<Category> categories=session.getAttribute("categories")!=null?(List<Category>)session.getAttribute("categories"):null;
double totalLineAmount=0.0;
for(CartProduct cartProduct:cartProducts){
	totalLineAmount+= cartProduct.getPrice()+(cartProduct.getPrice()*cartProduct.getTaxRate());
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/cart.css" rel="stylesheet" type="text/css">
<title>Cart Detail</title>
</head>
<body>
	<jsp:include page="./common/Navigator.jsp"></jsp:include>
	<section class="cart">
		<div class="shopping-cart">
			<%
			for (CartProduct cartProduct: cartProducts) {
				Product product = ProductClient.getById(cartProduct.getProductId());
				Category category= categories.stream().filter(c->c.getId()==product.getCategoryId()).findFirst().get();
				String productName = product.getName();
				String imageUrl= product.getImageUrl();
				String price = String.valueOf(cartProduct.getPrice());
				String quantity = String.valueOf(cartProduct.getQuantity());
				String taxRate = String.valueOf(cartProduct.getTaxRate());
				String lineAmount = String.valueOf(cartProduct.getLineAmount());
			%>
			<div class="item">
				<div class="buttons">
					<span class="delete-btn"><b>X</b></span>
				</div>

				<div class="image">
					<img src="<%=imageUrl %>" alt="">
				</div>

				<div class="description">
					<span><%=product.getName()%></span> <span><%=category.getName()%></span>
				</div>

				<div class="quantity">
					<form action="#">
						<input class="plus-btn" type="button" name="button" value="+">
						<input type="text" name="name" value="<%=quantity%>"> 
						<input class="minus-btn" type="button" name="button" value="-">
					</form>
				</div>

				<div class="total-price"><%=price %></div>
			</div>
			<%
			}
			%>
		</div>
		<div class="cart-checkout">
			<div class="cart-checkout-title">
				<span>Checkout</span>
			</div>
			<div>
			<div class="total-price"><%=totalLineAmount %> TL</div>
			</div>
		</div>
	</section>

</body>
</html>