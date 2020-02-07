package com.bridgelabz.usermanagement.services;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 */
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bridgelabz.usermanagement.model.Login;
import com.bridgelabz.usermanagement.model.Registration;

public interface IServices {
	public boolean addUser(Registration registration);

	public boolean addAdmin(Registration registration);

	public boolean authentication(Login login);

	public boolean deleteUserDetails(String email);

	public boolean updateUserDetails(Registration update) throws ClassNotFoundException, SQLException;

	public JSONObject getOneUserDetails(String email);

	public JSONArray lastRegistered();
}