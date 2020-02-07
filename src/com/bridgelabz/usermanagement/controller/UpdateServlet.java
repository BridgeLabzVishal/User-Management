package com.bridgelabz.usermanagement.controller;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : To implement update module for user management
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import com.bridgelabz.usermanagement.model.Registration;
import com.bridgelabz.usermanagement.repository.UserDetailsRepository;
import com.bridgelabz.usermanagement.services.IServices;
import com.bridgelabz.usermanagement.services.ServicesImpl;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Registration update = new Registration();
	IServices services = new ServicesImpl();
	private static PrintWriter printWriter;
	private static RequestDispatcher requestDispatcher;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fname = request.getParameter("firstname");
		update.setFirstName(fname);

		String lname = request.getParameter("lastname");
		update.setLastName(lname);

		String email = request.getParameter("email");
		update.setEmail(email);

		String password = request.getParameter("password");
		update.setPasswd(password);

		String gender = request.getParameter("gender");
		update.setGender(gender);

		String dob = request.getParameter("dob");
		update.setDateOfBirth(dob);

		int age = Integer.parseInt(request.getParameter("age"));
		update.setAge(age);

		String city = request.getParameter("city");
		update.setCity(city);

		String state = request.getParameter("state");
		update.setState(state);

		int zip = Integer.parseInt(request.getParameter("zip"));
		update.setZip(zip);
		System.out.println(update);

		boolean flag = UserDetailsRepository.getUserDetails(email);
		if (flag == true) {
			System.out.println("coming inside true block");
			try {
				if (services.updateUserDetails(update)) {
					/*
					 * System.out.println(services.updateUserDetails(update)); printWriter.
					 * print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>"
					 * ); printWriter.print("<script type='text/javascript'>");
					 * printWriter.print("$(document).ready(function(){");
					 * printWriter.print("alert('Successfully login!!!');");
					 * printWriter.print("});"); printWriter.print("</script>");
					 */

					requestDispatcher = request.getRequestDispatcher("Login.jsp");
					requestDispatcher.include(request, response);
				}
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
		} else {
			printWriter
					.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
			printWriter.print("<script type='text/javascript'>");
			printWriter.print("$(document).ready(function(){");
			printWriter.print("alert('Sorry try again invalid email!!!');");
			printWriter.print("});");
			printWriter.print("</script>");

			requestDispatcher = request.getRequestDispatcher("Admin.jsp");
			requestDispatcher.include(request, response);
		}
	}
}
