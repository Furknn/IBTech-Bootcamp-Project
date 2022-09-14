package com.bootcamp.api.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.bootcamp.entity.CartProduct;
import com.bootcamp.manager.CartProductManager;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.CartProductXmlManager;

@WebServlet("/cart/view")
public class CartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long cartId = Long.parseLong(request.getParameter("cartid"));
		List<CartProduct> cartProducts = CartProductManager.getInstance().getByCartId(cartId);
		Document document = CartProductXmlManager.getInstance().formatList(cartProducts);
		response.setContentType("application/xml; charset=UTF-8");
		try {
			XmlHelper.dump(document, response.getOutputStream());
			response.setStatus(200);
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

}
