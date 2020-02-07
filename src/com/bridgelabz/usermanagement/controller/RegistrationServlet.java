package com.bridgelabz.usermanagement.controller;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : To implement registration module for user in user management
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bridgelabz.usermanagement.model.Registration;
import com.bridgelabz.usermanagement.repository.UserDetailsRepository;
import com.bridgelabz.usermanagement.services.IServices;
import com.bridgelabz.usermanagement.services.ServicesImpl;

//@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static PrintWriter printWriter;
	private static RequestDispatcher requestDispatcher;
	IServices services = new ServicesImpl();
	Registration registration = new Registration();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fname = request.getParameter("firstname");
		registration.setFirstName(fname);

		String lname = request.getParameter("lastname");
		registration.setLastName(lname);

		String email = request.getParameter("email");
		registration.setEmail(email);

		String password = request.getParameter("password");
		registration.setPasswd(password);

		String gender = request.getParameter("radio");
		registration.setGender(gender);

		String dob = request.getParameter("dob");
		registration.setDateOfBirth(dob);

		int age = Integer.parseInt(request.getParameter("age"));
		registration.setAge(age);

		String city = request.getParameter("city");
		registration.setCity(city);

		String state = request.getParameter("state");
		registration.setState(state);

		int zip = Integer.parseInt(request.getParameter("zip"));
		registration.setZip(zip);

		response.setContentType("text/html");
		printWriter = response.getWriter();

		boolean flag = UserDetailsRepository.getUserDetails(email);
		if (flag == false) {
			if (services.addUser(registration)) {
				printWriter.print(
						"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
				printWriter.print("<script type='text/javascript'>");
				printWriter.print("$(document).ready(function(){");
				printWriter.print("alert('Successfully Registrated!!!');");
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
			printWriter.print("alert('Already Exists,Please Choose Another Email!!!');");
			printWriter.print("});");
			printWriter.print("</script>");
			requestDispatcher = request.getRequestDispatcher("Registration.jsp");
			requestDispatcher.include(request, response);
		}
	}

}
