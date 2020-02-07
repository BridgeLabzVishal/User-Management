package com.bridgelabz.usermanagement.controller;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : Implemented delete module for user management
 */
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bridgelabz.usermanagement.services.IServices;
import com.bridgelabz.usermanagement.services.ServicesImpl;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher requestDispatcher;
	IServices services = new ServicesImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println(email);
		boolean flag = services.deleteUserDetails(email);

		if (flag == true) {
			requestDispatcher = request.getRequestDispatcher("Admin.jsp");
			System.out.println(requestDispatcher);
			requestDispatcher.forward(request, response);
		} else {

			response.sendRedirect("Admin.jsp");
		}
	}

}
