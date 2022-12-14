package com.bootcamp.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.bootcamp.entity.User;
import com.bootcamp.manager.UserManager;
import com.bootcamp.utils.XmlHelper;
import com.bootcamp.xml.UserXmlManager;

@WebServlet("/user/create")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document document;
		try {
			document = XmlHelper.parse(request.getInputStream());

		} catch (IOException | ParserConfigurationException | SAXException e1) {
			e1.printStackTrace();
			response.getWriter().append("Bad request");
			response.setStatus(500);
			return;
		}
		User user = UserXmlManager.getInstance().parse(document);
		if (user == null) {
			response.getWriter().append("Bad request");
			response.setStatus(500);
			return;
		}

		if (UserManager.getInstance().check(user)){
			response.getWriter().append("User already exists");
			response.setStatus(500);
			return;
		}

		User createdUser = UserManager.getInstance().create(user);
		if(createdUser != null) {
			response.getWriter().append("true");
			response.setStatus(200);
		} else {
			response.getWriter().append("false");
			response.setStatus(401);
		}
	}
}
