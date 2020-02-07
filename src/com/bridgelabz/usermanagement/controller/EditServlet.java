package com.bridgelabz.usermanagement.controller;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : Implemented edit details for admin in user management
 */
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import com.bridgelabz.usermanagement.services.IServices;
import com.bridgelabz.usermanagement.services.ServicesImpl;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RequestDispatcher requestDispatcher;
	IServices services = new ServicesImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");

		JSONObject jsonObject = services.getOneUserDetails(email);

		if (jsonObject != null) {
			request.setAttribute("userdetails", jsonObject);

			HttpSession session = request.getSession();
			session.setAttribute("Email", email);

			requestDispatcher = request.getRequestDispatcher("Update.jsp");
			requestDispatcher.include(request, response);
		}
	}

}
