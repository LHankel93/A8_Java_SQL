package de.hankel.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	/**
	 * Standardkonstruktor für den DB Controller.
	 */
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

	/**
	 * Diese Methode prüft, ob die Daten in der Account Tabelle vorhanden sind und stimmen.
	 * Gibt true zurück wenn gut, false wenn schlecht.
	 * @param email Die E-Mail auf welche geprüft werden soll.
	 * @param passwort Das Passwort, welches für die E-Mail angelegt sein soll.
	 * @return true wenn Eingaben korrekt, false wenn falsch.
	 */
	public Boolean checkLogin(String email, String passwort) {
		try {
			// Projektion mit PreparedStatement vorbereiten
			PreparedStatement ps = con.prepareStatement("SELECT email, passwort FROM t_accounts WHERE email =?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			// Durch ResultSet iterieren und nochmal prüfen, ob Daten korrekt
			while (rs.next()) {
				if (email.compareTo(rs.getString(1)) == 0 && passwort.compareTo(rs.getString(2)) == 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Methode zum prüfen der DB.
	 */
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
