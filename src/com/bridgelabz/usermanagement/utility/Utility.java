package com.bridgelabz.usermanagement.utility;

/**
 * @Author  : Vishal Yadav
 * @Date    : 16/01/2020
 * @Version : 1.0
 * @Purpose : database connection implemented
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Utility {

	/**
	 * define connection for database
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 */
	public static Connection dbConnection() throws ClassNotFoundException {
		String dbUrl = "jdbc:mysql://localhost:3306/dashboard";
		String user = "RedDevilVishal";
		String passwd = "RAJvishal12@";
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			con = DriverManager.getConnection(dbUrl, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
