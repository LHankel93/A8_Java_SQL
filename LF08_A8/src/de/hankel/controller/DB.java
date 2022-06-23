package de.hankel.controller;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Klasse welche den Datenbank Controller darstellt.
 * 
 * @author Lorenz Hankel
 *
 */
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
	public DB(String user, String password) {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/egamedarling?";
		this.user = user;
		this.password = password;
//		user = "username";
//		password = "mypassword";
		try {
			con = DriverManager.getConnection(url, user, password);
			Class.forName(driver);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode prüft, ob die Daten in der Account Tabelle vorhanden sind und
	 * stimmen. Gibt true zurück wenn gut, false wenn schlecht.
	 * 
	 * @param email    Die E-Mail auf welche geprüft werden soll.
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prüft die DB Verbindung.
	 * 
	 * @param con Das Connection Objekt als Verbindung zur DB.
	 * @return true bei Erfolg, false bei Fehler
	 */
	public boolean isDbConnected() {
		try {
			return con != null && !con.isClosed();
		} catch (SQLException ignored) {
		}

		return false;
	}

	public int getNextAvailableAccountID() {
		int max = 0;
		String sql = "SELECT MAX(CAST(p_account_id AS Int)) FROM t_accounts; ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				max = rs.getInt(1);
				System.out.println(max);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return max;
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean createAccount(String nickname, String passwort, String email, Blob avatar) {
		if (avatar == null) {
			int p_account_id = 1;
			p_account_id += getNextAvailableAccountID();
			String insert = "INSERT INTO t_accounts (p_account_id, email, passwort, nickname) VALUES (?,?,?,?)";
			try {
				PreparedStatement ps = con.prepareStatement(insert);
				ps.setInt(1, p_account_id);
				ps.setString(2, email);
				ps.setString(3, passwort);
				ps.setString(4, nickname);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
