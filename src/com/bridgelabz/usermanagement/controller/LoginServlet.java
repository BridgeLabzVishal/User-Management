package com.bridgelabz.usermanagement.controller;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : To implement login module for user management
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;

import com.bridgelabz.usermanagement.model.Login;
import com.bridgelabz.usermanagement.repository.UserDetailsRepository;
import com.bridgelabz.usermanagement.services.IServices;
import com.bridgelabz.usermanagement.services.ServicesImpl;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static PrintWriter printWriter;
	private static RequestDispatcher requestDispatcher;
	JSONArray array = new JSONArray();
	IServices services = new ServicesImpl();
	Login login = new Login();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		login.setEmail(email);

		String password = request.getParameter("password");
		login.setPassword(password);

		String userType = request.getParameter("radio");
		login.setUserType(userType);

		response.setContentType("text/html");
		printWriter = response.getWriter();

		if (userType.equalsIgnoreCase("user")) {
			if (services.authentication(login)) {
				HttpSession session = request.getSession();
				session.setAttribute("Email", email);
				session.setAttribute("Password", password);

				printWriter.print(
						"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
				printWriter.print("<script type='text/javascript'>");
				printWriter.print("$(document).ready(function(){");
				printWriter.print("alert('Successfully login!!!');");
				printWriter.print("});");
				printWriter.print("</script>");

				response.sendRedirect("Home.jsp");
			} else {
				printWriter.print(
						"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
				printWriter.print("<script type='text/javascript'>");
				printWriter.print("$(document).ready(function(){");
				printWriter.print("alert('Sorry Wrong Credentials!!!');");
				printWriter.print("});");
				printWriter.print("</script>");
				requestDispatcher = request.getRequestDispatcher("Login.jsp");
				requestDispatcher.include(request, response);
			}
		} else if (userType.equalsIgnoreCase("admin")) {
			if (services.authentication(login)) {
				try {
					array = UserDetailsRepository.getAllDetails();
					request.setAttribute("jsonArray", array);

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				HttpSession session = request.getSession();
				session.setAttribute("Email", email);
				session.setAttribute("Password", password);

				printWriter.print(
						"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
				printWriter.print("<script type='text/javascript'>");
				printWriter.print("$(document).ready(function(){");
				printWriter.print("alert('Successfully login!!!');");
				printWriter.print("});");
				printWriter.print("</script>");

				// response.sendRedirect("Admin.jsp");
				requestDispatcher = request.getRequestDispatcher("Admin.jsp");
				requestDispatcher.forward(request, response);
			} else {
				printWriter.print(
						"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
				printWriter.print("<script type='text/javascript'>");
				printWriter.print("$(document).ready(function(){");
				printWriter.print("alert('Sorry Wrong Credentials!!!');");
				printWriter.print("});");
				printWriter.print("</script>");
				requestDispatcher = request.getRequestDispatcher("Login.jsp");
				requestDispatcher.include(request, response);
			}
		} else {
			printWriter
					.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
			printWriter.print("<script type='text/javascript'>");
			printWriter.print("$(document).ready(function(){");
			printWriter.print("alert('Sorry Wrong Credentials!!!');");
			printWriter.print("});");
			printWriter.print("</script>");
			requestDispatcher = request.getRequestDispatcher("Login.jsp");
			requestDispatcher.include(request, response);
		}
		printWriter.close();
	}

}
