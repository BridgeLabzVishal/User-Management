package com.bridgelabz.usermanagement.services;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : To implements the services for the user management 
 */
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.bridgelabz.usermanagement.model.Login;
import com.bridgelabz.usermanagement.model.Registration;
import com.bridgelabz.usermanagement.repository.UserDetailsRepository;

public class ServicesImpl implements IServices {
	JSONObject jsonObject = new JSONObject();

	/**
	 * define function to add users
	 * 
	 * @param Registration
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addUser(Registration registration) {
		jsonObject.put("firstname", registration.getFirstName());
		jsonObject.put("lastname", registration.getLastName());
		jsonObject.put("email", registration.getEmail());
		jsonObject.put("password", registration.getPasswd());
		jsonObject.put("gender", registration.getGender());
		jsonObject.put("dob", registration.getDateOfBirth());
		jsonObject.put("age", registration.getAge());
		jsonObject.put("city", registration.getCity());
		jsonObject.put("state", registration.getState());
		jsonObject.put("zip", registration.getZip());

		return UserDetailsRepository.addUser(jsonObject);
	}

	/**
	 * define function to add admin
	 * 
	 * @param Registration
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAdmin(Registration registration) {
		jsonObject.put("firstname", registration.getFirstName());
		jsonObject.put("lastname", registration.getLastName());
		jsonObject.put("email", registration.getEmail());
		jsonObject.put("password", registration.getPasswd());
		jsonObject.put("gender", registration.getGender());
		jsonObject.put("dob", registration.getDateOfBirth());
		jsonObject.put("age", registration.getAge());
		jsonObject.put("city", registration.getCity());
		jsonObject.put("state", registration.getState());
		jsonObject.put("zip", registration.getZip());

		return UserDetailsRepository.addAdmin(jsonObject);
	}

	/**
	 * define function for authenticated the user
	 * 
	 * @param Login
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean authentication(Login login) {
		jsonObject.put("email", login.getEmail());
		jsonObject.put("password", login.getPassword());
		jsonObject.put("userType", login.getUserType());
		if (jsonObject.get("userType").equals("user"))
			return UserDetailsRepository.authenticateUser(jsonObject);
		else if (jsonObject.get("userType").equals("admin"))
			return UserDetailsRepository.authenticateAdmin(jsonObject);
		else
			return false;
	}

	/**
	 * define function to update user's details
	 * 
	 * @param Registration
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateUserDetails(Registration update) throws ClassNotFoundException, SQLException {
		jsonObject.put("firstname", update.getFirstName());
		jsonObject.put("lastname", update.getLastName());
		jsonObject.put("email", update.getEmail());
		jsonObject.put("password", update.getPasswd());
		jsonObject.put("gender", update.getGender());
		jsonObject.put("dob", update.getDateOfBirth());
		jsonObject.put("age", update.getAge());
		jsonObject.put("city", update.getCity());
		jsonObject.put("state", update.getState());
		jsonObject.put("zip", update.getZip());

		return UserDetailsRepository.update(jsonObject);
	}

	/**
	 * define function to get one user's details
	 * 
	 * @param String
	 * @return JSONObject
	 */
	@Override
	public JSONObject getOneUserDetails(String email) {
		return UserDetailsRepository.getOneUserDetails(email);
	}

	/**
	 * define function to delete user's details
	 * 
	 * @param String
	 * @return boolean
	 */
	@Override
	public boolean deleteUserDetails(String email) {
		return UserDetailsRepository.deleteUser(email);
	}

	/**
	 * define function to get last registered users
	 * 
	 * @return JSONArray
	 */
	@Override
	public JSONArray lastRegistered() {
		return UserDetailsRepository.lastRegistered();
	}
}
