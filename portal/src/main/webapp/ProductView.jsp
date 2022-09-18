<%@page import="com.bootcamp.entity.Cart"%>
<%@page import="com.bootcamp.portal.CartClient"%>
<%@page import="com.bootcamp.portal.CartProductClient"%>
<%@page import="com.bootcamp.entity.CartProduct"%>
<%@page import="com.bootcamp.portal.CategoryClient"%>
<%@page import="com.bootcamp.portal.ProductClient"%>
<%@page import="com.bootcamp.entity.Category"%>
<%@page import="com.bootcamp.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%


String addToCart=(String) request.getParameter("addcart");
long productId = Long.parseLong(request.getParameter("id")==null?addToCart:request.getParameter("id"));
Product product = ProductClient.getById(productId);


if(addToCart!=null){
	CartProduct cp= new CartProduct();
	String cartId = session.getAttribute("cartid") != null ? String.valueOf((long) session.getAttribute("cartid")) : null;
	if(cartId==null){
		Cart cart=CartClient.createCart();
		cartId=String.valueOf(cart.getId());
	}
	
	cp.setCartId(Long.valueOf(cartId));
	cp.setProductId(product.getId());
	cp.setPrice(product.getPrice());
	cp.setTaxRate(18.0);
	cp.setQuantity(1);
	cp.setLineAmount((product.getPrice()+(cp.getTaxRate()*product.getPrice()))*cp.getQuantity());
	
	CartProductClient.addCart(cp);
	response.sendRedirect("/portal/CartView.jsp");
}

%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/product.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Product View</title>
</head>
<body>
	<jsp:include page="./common/Navigator.jsp"></jsp:include>
		<div class="card">
			<div class="photo">
				<img
					src="<%=product.getImageUrl()%>">
			</div>
			<div class="description">
				<h2><%=product.getName() %></h2>
				<h1><%=product.getPrice() %> TL</h1>
				<p><%=product.getDetail() %></p>
				<form action="#" name="addToCartForm">
				<input type="hidden" name="addcart">
				<button onclick="{document.addToCartForm.elements.addcart.value=<%=product.getId()%>;addToCartForm.submit()}">Add to Cart</button>
				</form>
				
				
			</div>
		</div>

</body>
</html>