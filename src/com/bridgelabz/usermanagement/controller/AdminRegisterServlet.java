package com.bridgelabz.usermanagement.controller;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : Implemented admin registration module for user management
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bridgelabz.usermanagement.model.Registration;
import com.bridgelabz.usermanagement.repository.UserDetailsRepository;
import com.bridgelabz.usermanagement.services.IServices;
import com.bridgelabz.usermanagement.services.ServicesImpl;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
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
		System.out.println(registration);

		boolean flag = UserDetailsRepository.getAdminDetails(email);

		if (flag == false) {
			if (services.addAdmin(registration)) {
				requestDispatcher = request.getRequestDispatcher("Login.jsp");
				requestDispatcher.include(request, response);
			}
		} else {
			requestDispatcher = request.getRequestDispatcher("AdminRegister.jsp");
			requestDispatcher.include(request, response);
		}
	}

}
