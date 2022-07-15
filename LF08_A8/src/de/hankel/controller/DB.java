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
	@SuppressWarnings("unused")
	private String user;
	@SuppressWarnings("unused")
	private String password;
	private Connection con;
	Statement stmt;
	ResultSet rs;

	/**
	 * Standardkonstruktor für einen Datenbank-Controller Objekt.
	 * 
	 * @param user     String DB Account welcher benutzt werden soll.
	 * @param password String DB Passwort welches benutzt werden soll.
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

	/**
	 * Workaround für das Problem, dass die DB ein CHAR Feld benutzt für den PK.
	 * Gibt den nächsten freien Integer zurück, nachdem die DB abgefragt wurde.
	 * 
	 * @return Integer Der nächste freie PK. (Leider nötig wegen CHAR Feld im PK.)
	 */
	public int getNextAvailableAccountID() {
		int max = 0;
		String sql = "SELECT MAX(CAST(p_account_id AS Int)) FROM t_accounts; ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				max = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return max;
	}

	public void closeConnection() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt einen neuen Account auf der EgameDarlingDB.
	 * 
	 * @param nickname String Der Nickname des neuen Accounts.
	 * @param passwort String Das Passwort des neuen Accounts.
	 * @param email    String Der E-Mail Account des neuen Accounts.
	 * @param avatar   BLOB Avatar des neuen Accounts.
	 * @return Boolean True wenn erfolgreich, false wenn Fehler.
	 */
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
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
}
