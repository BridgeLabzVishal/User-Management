package com.bridgelabz.usermanagement.repository;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : To implements the services for the user management 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.bridgelabz.usermanagement.utility.Utility;

public class UserDetailsRepository {
	private static ResultSet resultSet = null;

	/**
	 * define function to add users
	 * 
	 * @param Registration
	 * @return boolean
	 */
	public static boolean addUser(JSONObject jsonObject) {
		String query = "insert into users(First_Name, Last_Name, Email, Password, Gender, DOB, Age, City, State, Zip) values(?,?,?,?,?,?,?,?,?,?)";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query)) {
			statement.setString(1, jsonObject.get("firstname").toString());
			statement.setString(2, jsonObject.get("lastname").toString());
			statement.setString(3, jsonObject.get("email").toString());
			statement.setString(4, jsonObject.get("password").toString());
			statement.setString(5, jsonObject.get("gender").toString());
			statement.setString(6, jsonObject.get("dob").toString());
			statement.setString(7, jsonObject.get("age").toString());
			statement.setString(8, jsonObject.get("city").toString());
			statement.setString(9, jsonObject.get("state").toString());
			statement.setString(10, jsonObject.get("zip").toString());

			int result = statement.executeUpdate();
			if (result > 0)
				return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Registration error");
		}
		return false;
	}

	/**
	 * define function to add admin
	 * 
	 * @param JSONObject
	 * @return boolean
	 */
	public static boolean addAdmin(JSONObject jsonObject) {
		String query = "insert into admin(First_Name, Last_Name, Email, Password, Gender, DOB, Age, City, State, Zip) values(?,?,?,?,?,?,?,?,?,?)";

		System.out.println("add admin : " + jsonObject.toString());
		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query)) {
			statement.setString(1, jsonObject.get("firstname").toString());
			statement.setString(2, jsonObject.get("lastname").toString());
			statement.setString(3, jsonObject.get("email").toString());
			statement.setString(4, jsonObject.get("password").toString());
			statement.setString(5, jsonObject.get("gender").toString());
			statement.setString(6, jsonObject.get("dob").toString());
			statement.setString(7, jsonObject.get("age").toString());
			statement.setString(8, jsonObject.get("city").toString());
			statement.setString(9, jsonObject.get("state").toString());
			statement.setString(10, jsonObject.get("zip").toString());

			int result = statement.executeUpdate();
			if (result > 0)
				return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Registration error");
		}
		return false;
	}

	/**
	 * define function for authenticated the user
	 * 
	 * @param JSONObject
	 * @return boolean
	 */
	public static boolean authenticateUser(JSONObject jsonObject) {
		String email = (String) jsonObject.get("email");
		String password = (String) jsonObject.get("password");

		String query = "select * from users";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("Email").equals(email) && resultSet.getString("Password").equals(password))
					return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Authentication error");
		}
		return false;
	}

	/**
	 * define function for authenticated the admin
	 * 
	 * @param JSONObject
	 * @return boolean
	 */
	public static boolean authenticateAdmin(JSONObject jsonObject) {
		String email = (String) jsonObject.get("email");
		String password = (String) jsonObject.get("password");

		String query = "select * from admin";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("Email").equals(email) && resultSet.getString("Password").equals(password))
					return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Authentication error");
		}
		return false;
	}

	/**
	 * define function to get one user's details
	 * 
	 * @param String
	 * @return boolean
	 */
	public static boolean getUserDetails(String email) {
		String query = "select * from users";
		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("Email").equals(email))
					return true;
			}
		} catch (Exception e) {
			System.out.println("Reading user details error");
		}
		return false;
	}

	/**
	 * define function to get one admin's details
	 * 
	 * @param String
	 * @return boolean
	 */
	public static boolean getAdminDetails(String email) {
		String query = "select * from admin";
		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("Email").equals(email))
					return true;
			}
		} catch (Exception e) {
			System.out.println("Reading user details error");
		}
		return false;
	}

	/**
	 * define function to get user's details
	 * 
	 * @return JSONArray
	 */
	public static JSONArray getAllDetails() throws ClassNotFoundException, SQLException {
		JSONArray jsonArray = new JSONArray();
		String query = "select * from users";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("firstname", resultSet.getString("First_Name"));
				jsonObject.put("lastname", resultSet.getString("Last_Name"));
				jsonObject.put("email", resultSet.getString("Email"));
				jsonObject.put("gender", resultSet.getString("Gender"));
				jsonObject.put("dob", resultSet.getString("DOB"));
				jsonObject.put("age", resultSet.getInt("Age"));
				jsonObject.put("city", resultSet.getString("City"));
				jsonObject.put("state", resultSet.getString("State"));
				jsonObject.put("zip", resultSet.getInt("Zip"));
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}

	/**
	 * define function to get top locations
	 * 
	 * @return JSONArray
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static JSONArray topLocations() throws ClassNotFoundException, SQLException {
		JSONArray jsonArray = new JSONArray();

		String query = "select distinct city,state from users";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("city", resultSet.getString("City"));
				jsonObject.put("state", resultSet.getString("State"));
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}

	/**
	 * define function to update admin details
	 * 
	 * @param jsonObject
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static boolean update(JSONObject jsonObject) throws ClassNotFoundException, SQLException {
		String updateUser = "UPDATE admin SET First_Name=?,Last_Name=?,Password=?,Gender=?,DOB=?,Age=?,City=?,State=?,Zip=? WHERE Email=?";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(updateUser);) {
			statement.setString(1, jsonObject.get("firstname").toString());
			statement.setString(2, jsonObject.get("lastname").toString());
			statement.setString(3, jsonObject.get("password").toString());
			statement.setString(4, jsonObject.get("gender").toString());
			statement.setString(5, jsonObject.get("dob").toString());
			statement.setString(6, jsonObject.get("age").toString());
			statement.setString(7, jsonObject.get("city").toString());
			statement.setString(8, jsonObject.get("state").toString());
			statement.setString(9, jsonObject.get("zip").toString());
			statement.setString(10, jsonObject.get("email").toString());

			boolean rowDeleted = statement.executeUpdate() > 0;
			return rowDeleted;
		}
	}

	/**
	 * define function to get one user's details
	 * 
	 * @param email
	 * @return JSONObject
	 */
	public static JSONObject getOneUserDetails(String email) {
		String query = "select * from admin where Email=?";
		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("firstname", resultSet.getString("First_Name"));
				jsonObject.put("lastname", resultSet.getString("Last_Name"));
				jsonObject.put("email", resultSet.getString("Email"));
				jsonObject.put("password", resultSet.getString("Password"));
				jsonObject.put("gender", resultSet.getString("Gender"));
				jsonObject.put("dob", resultSet.getString("DOB"));
				jsonObject.put("age", resultSet.getInt("Age"));
				jsonObject.put("city", resultSet.getString("City"));
				jsonObject.put("state", resultSet.getString("State"));
				jsonObject.put("zip", resultSet.getInt("Zip"));

				return jsonObject;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * define function to delete user
	 * 
	 * @param email
	 * @return JSONObject
	 */
	public static boolean deleteUser(String email) {
		String deleteQuery = "DELETE FROM users where Email=?";

		try (Connection con = Utility.dbConnection();
				PreparedStatement statement = con.prepareStatement(deleteQuery);) {
			statement.setString(1, email);
			boolean result = statement.executeUpdate() > 0;
			return result;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * define function to get last registered user's details
	 * 
	 * @return JSONArray
	 */
	public static JSONArray lastRegistered() {
		JSONArray jsonArray = new JSONArray();
		String query = "SELECT * from users ORDER by Id desc";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("firstname", resultSet.getString("First_Name"));
				jsonObject.put("lastname", resultSet.getString("Last_Name"));
				jsonObject.put("email", resultSet.getString("Email"));
				jsonObject.put("gender", resultSet.getString("Gender"));
				jsonObject.put("dob", resultSet.getString("DOB"));
				jsonObject.put("age", resultSet.getInt("Age"));
				jsonObject.put("city", resultSet.getString("City"));
				jsonObject.put("state", resultSet.getString("State"));
				jsonObject.put("zip", resultSet.getInt("Zip"));
				jsonArray.add(jsonObject);
			}
			return jsonArray;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * define function to users details gender wise
	 * 
	 * @return JSONObject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static JSONObject getGenderDetails() throws ClassNotFoundException, SQLException {
		JSONObject jsonObject = new JSONObject();
		int male = 0;
		int female = 0;

		String query = "select * from users";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("Gender").equalsIgnoreCase("male"))
					male++;
				else
					female++;
			}
			jsonObject.put("male", male);
			jsonObject.put("female", female);
		}
		return jsonObject;
	}

	/**
	 * define function to get users according to their state
	 * 
	 * @return JSONArray
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray getUserState() throws ClassNotFoundException, SQLException {
		JSONArray jsonArray = new JSONArray();

		String query = "SELECT State,count(*) as Times from users GROUP BY State";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				/*
				 * JSONObject jsonObject = new JSONObject(); jsonObject.put("state",
				 * resultSet.getString("State")); jsonObject.put("times",
				 * resultSet.getString("Times"));
				 */
				jsonArray.add(resultSet.getString("State"));

			}
		}
		return jsonArray;
	}

	/**
	 * define function to get number of states
	 * 
	 * @return JSONArray
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray getStateTimes() throws ClassNotFoundException, SQLException {
		JSONArray jsonArray = new JSONArray();

		String query = "SELECT State,count(*) as Times from users GROUP BY State";

		try (Connection con = Utility.dbConnection(); PreparedStatement statement = con.prepareStatement(query);) {
			resultSet = statement.executeQuery();
			while (resultSet.next())
				jsonArray.add(resultSet.getString("Times"));
		}
		return jsonArray;
	}

}
