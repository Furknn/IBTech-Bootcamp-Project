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

/**
 * Servlet implementation class UserCheckServlet
 */
@WebServlet("/user/check")
public class UserCheckServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document document;
		try {
			document = XmlHelper.parse(request.getInputStream());

		} catch (IOException | ParserConfigurationException | SAXException e1) {
			e1.printStackTrace();
			response.setStatus(500);
			return;
		}
		User loggedUser = UserXmlManager.getInstance().parse(document);
		boolean isLogged = UserManager.getInstance().check(loggedUser);
		if(isLogged) {
			response.getWriter().append("User exists");
			response.setStatus(200);
		} else {
			response.getWriter().append("User doesn't exist");
			response.setStatus(401);
		}
	}

}
