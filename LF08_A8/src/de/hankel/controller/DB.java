package de.hankel.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection con;
	Statement stmt;
	ResultSet rs;

	public DB() {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/egamedarling?";
		user = "username";
		password = "mypassword";
		try {
			con = DriverManager.getConnection(url, user, password);
			Class.forName(driver);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Boolean checkLogin(String email, String passwort) {
		try {
			stmt = con.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public void testdb() {

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM t_accounts");
			while (rs.next()) {
				System.out.println(rs.getString("email"));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
