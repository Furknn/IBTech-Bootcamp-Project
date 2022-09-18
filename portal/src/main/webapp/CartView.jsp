<%@page import="java.lang.ProcessBuilder.Redirect"%>
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
String cartId = session.getAttribute("cartid") != null ? String.valueOf((long) session.getAttribute("cartid")) : null;
String username = (String) session.getAttribute("username");

if (cartId == null) {
	Cart cart = username == null ? CartClient.createCart() : CartClient.createCart(username);
	session.setAttribute("cartid", cart.getId());
	cartId = String.valueOf(cart.getId());
} else {
	session.setAttribute("cartid", Long.valueOf(cartId));
}
List<CartProduct> cartProducts = CartProductClient.getCartProducts(Long.valueOf(cartId));
String changedCardProduct = (String) request.getParameter("cartproduct");
if (changedCardProduct != null && changedCardProduct != "") {
	CartProduct cp = cartProducts.stream()
	.filter(cartProduct -> cartProduct.getId() == Long.valueOf(changedCardProduct)).findFirst().get();
	String direction = (String) request.getParameter("changed");
	if (direction.equals("inc")) {
		cp.setQuantity(cp.getQuantity() + 1);
	} else if (direction.equals("dec")) {
		cp.setQuantity(cp.getQuantity() - 1);

	}
	double lineAmount = (cp.getPrice() + (cp.getPrice() * cp.getTaxRate() / 100)) * cp.getQuantity();

	cp.setLineAmount(lineAmount);
	CartProductClient.addCart(cp);
	response.sendRedirect("/portal/CartView.jsp");
}

String deleted = (String) request.getParameter("deleted");

if (deleted != null && deleted != "") {
	long deletedId = Long.valueOf(deleted);
	CartProductClient.cartRemove(deletedId);
	response.sendRedirect("/portal/CartView.jsp");
}

List<Category> categories = session.getAttribute("categories") != null
		? (List<Category>) session.getAttribute("categories")
		: null;
double totalLineAmount = 0.0;
for (CartProduct cartProduct : cartProducts) {
	totalLineAmount += cartProduct.getLineAmount();
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
	<form action="#" name="cartForm" hidden="true" class="cart">
		<input type="hidden" name="deleted">
		<div class="shopping-cart">
			<%
			int i = 0;
			for (CartProduct cartProduct : cartProducts) {
				Product product = ProductClient.getById(cartProduct.getProductId());
				Category category = categories.stream().filter(c -> c.getId() == product.getCategoryId()).findFirst().get();
				String productName = product.getName();
				String imageUrl = product.getImageUrl();
				String price = String.valueOf(cartProduct.getPrice());
				String quantity = String.valueOf(cartProduct.getQuantity());
				String taxRate = String.valueOf(cartProduct.getTaxRate());
				String lineAmount = String.valueOf(cartProduct.getLineAmount());
			%>
			<div class="item">
				<div class="buttons">
					<span class="delete-btn"
						onclick="{document.cartForm.elements.deleted.value=<%=cartProduct.getId()%>;cartForm.submit();}"><b>X</b></span>

				</div>

				<div class="image">
					<img src="<%=imageUrl%>" alt="">
				</div>

				<div class="description">
					<span><%=product.getName()%></span> <span><%=category.getName()%></span>
				</div>

				<div class="quantity">
					<form action="#" name="quantityForm">
						<input type="hidden" name="changed"> <input type="hidden"
							name="cartproduct"> <input class="plus-btn" type="button"
							value="+"
							onclick="{document.cartForm.elements.changed.value='inc';document.cartForm.elements.cartproduct.value=<%=cartProduct.getId()%>; cartForm.submit();}">
						<input type="text" name="name" disabled="disabled"
							value="<%=quantity%>"> <input class="minus-btn"
							type="button" value="-"
							onclick="{document.cartForm.elements.changed.value='dec'; document.cartForm.elements.cartproduct.value=<%=cartProduct.getId()%>; cartForm.submit();}">
					</form>
				</div>


				<div class="total-price">
					Price
					<%=price%>
					TL
				</div>
				<div class="total-price">
					Tax
					<%=taxRate%>%
				</div>
				<div class="total-price">
					Amount
					<%=lineAmount%>
					TL
				</div>
			</div>
			<%
			i++;
			}
			%>
		</div>

		<div class="cart-checkout">
			<div class="cart-checkout-title">
				<span>Checkout</span>
			</div>
			<div>
				<div class="total-price"><%=totalLineAmount%>
					TL
				</div>
			</div>
		</div>
	</form>

</body>
</html>