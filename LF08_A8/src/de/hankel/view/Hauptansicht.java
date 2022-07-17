package de.hankel.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import de.hankel.controller.DB;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Klasse welche die Hauptansicht des Programmes darstellt.
 * 
 * @author Lorenz Hankel
 *
 */
public class Hauptansicht extends JFrame {

	private DB db;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textFieldRegEmail;
	private JPasswordField passwordFieldReg1;
	private JPasswordField passwordFieldReg2;
	private JTextField textFieldRegNickname;

	/**
	 * Standardkonstruktor des Haupt-Fensters. Soll Login und Registrieren
	 * ermöglichen.
	 * 
	 * @param db Ein Objekt der Controller Klasse DB. Sollte vorher initialisiert
	 *           und deklariert worden sein.
	 */
	public Hauptansicht(DB db) {
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Hauptansicht.class.getResource("/img/egamedarlingdb_logo_small.png")));
		setTitle("EgameDarling Login/Registrieren");
		this.db = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 301);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane);

		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		tabbedPane.addTab("Login",
				new ImageIcon(Hauptansicht.class.getResource("/img/arrow-right-to-bracket-solid_small.png")),
				panelLogin, "Hier können Sie sich in der EGameDarling Datenbank einloggen.");
		tabbedPane.setEnabledAt(0, true);
		SpringLayout sl_panelLogin = new SpringLayout();
		panelLogin.setLayout(sl_panelLogin);

		JTextArea textAreaEmail = new JTextArea();
		textAreaEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaEmail.setBackground(UIManager.getColor("Label.background"));
		sl_panelLogin.putConstraint(SpringLayout.NORTH, textAreaEmail, 10, SpringLayout.NORTH, panelLogin);
		sl_panelLogin.putConstraint(SpringLayout.EAST, textAreaEmail, -10, SpringLayout.EAST, panelLogin);
		panelLogin.add(textAreaEmail);

		JLabel lblEmail = new JLabel("E-Mail");
		sl_panelLogin.putConstraint(SpringLayout.WEST, textAreaEmail, 40, SpringLayout.EAST, lblEmail);
		lblEmail.setIcon(new ImageIcon(Hauptansicht.class.getResource("/img/at-solid_small.png")));
		lblEmail.setLabelFor(textAreaEmail);
		sl_panelLogin.putConstraint(SpringLayout.NORTH, lblEmail, 10, SpringLayout.NORTH, panelLogin);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sl_panelLogin.putConstraint(SpringLayout.WEST, lblEmail, 10, SpringLayout.WEST, panelLogin);
		panelLogin.add(lblEmail);

		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setIcon(new ImageIcon(Hauptansicht.class.getResource("/img/key-solid_small.png")));
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sl_panelLogin.putConstraint(SpringLayout.NORTH, lblPasswort, 6, SpringLayout.SOUTH, lblEmail);
		sl_panelLogin.putConstraint(SpringLayout.WEST, lblPasswort, 0, SpringLayout.WEST, lblEmail);
		panelLogin.add(lblPasswort);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sl_panelLogin.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, lblPasswort);
		sl_panelLogin.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, textAreaEmail);
		sl_panelLogin.putConstraint(SpringLayout.SOUTH, passwordField, 0, SpringLayout.SOUTH, lblPasswort);
		sl_panelLogin.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, textAreaEmail);
		panelLogin.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.setActionCommand("loginValidieren");

		sl_panelLogin.putConstraint(SpringLayout.NORTH, btnLogin, 30, SpringLayout.SOUTH, lblPasswort);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sl_panelLogin.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, lblEmail);
		panelLogin.add(btnLogin);

		JButton btnRegistrieren = new JButton("Registrieren");
		btnRegistrieren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistrieren.setActionCommand("geheZuTabRegistrieren");

		sl_panelLogin.putConstraint(SpringLayout.NORTH, btnRegistrieren, 0, SpringLayout.NORTH, btnLogin);
		sl_panelLogin.putConstraint(SpringLayout.WEST, btnRegistrieren, 10, SpringLayout.EAST, btnLogin);
		sl_panelLogin.putConstraint(SpringLayout.SOUTH, btnRegistrieren, 0, SpringLayout.SOUTH, btnLogin);
		panelLogin.add(btnRegistrieren);

		JTextPane textPaneInfoPaneLogin = new JTextPane();
		textPaneInfoPaneLogin.setEditable(false);
		sl_panelLogin.putConstraint(SpringLayout.NORTH, textPaneInfoPaneLogin, 10, SpringLayout.SOUTH, btnLogin);
		sl_panelLogin.putConstraint(SpringLayout.WEST, textPaneInfoPaneLogin, 0, SpringLayout.WEST, btnLogin);
		sl_panelLogin.putConstraint(SpringLayout.SOUTH, textPaneInfoPaneLogin, -10, SpringLayout.SOUTH, panelLogin);
		sl_panelLogin.putConstraint(SpringLayout.EAST, textPaneInfoPaneLogin, -10, SpringLayout.EAST, panelLogin);
		panelLogin.add(textPaneInfoPaneLogin);

		JPanel panelRegistrieren = new JPanel();
		panelRegistrieren.setBackground(Color.WHITE);
		tabbedPane.addTab("Registrieren",
				new ImageIcon(Hauptansicht.class.getResource("/img/user-plus-solid_small.png")), panelRegistrieren,
				"Hier kann ein neuer Benutzer-Account in der EGameDarling Datenbank angelegt werden.");
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		SpringLayout sl_panelRegistrieren = new SpringLayout();
		panelRegistrieren.setLayout(sl_panelRegistrieren);

		JLabel lblRegEmail = new JLabel("E-Mail");
		lblRegEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, lblRegEmail, 10, SpringLayout.NORTH, panelRegistrieren);
		sl_panelRegistrieren.putConstraint(SpringLayout.WEST, lblRegEmail, 10, SpringLayout.WEST, panelRegistrieren);
		panelRegistrieren.add(lblRegEmail);

		JLabel lblRegPasswort1 = new JLabel("Passwort");
		sl_panelRegistrieren.putConstraint(SpringLayout.WEST, lblRegPasswort1, 0, SpringLayout.WEST, lblRegEmail);
		lblRegPasswort1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, lblRegPasswort1, 10, SpringLayout.SOUTH, lblRegEmail);
		panelRegistrieren.add(lblRegPasswort1);

		textFieldRegEmail = new JTextField();
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, textFieldRegEmail, 0, SpringLayout.NORTH, lblRegEmail);
		sl_panelRegistrieren.putConstraint(SpringLayout.EAST, textFieldRegEmail, -10, SpringLayout.EAST,
				panelRegistrieren);
		textFieldRegEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sl_panelRegistrieren.putConstraint(SpringLayout.SOUTH, textFieldRegEmail, 0, SpringLayout.SOUTH, lblRegEmail);
		panelRegistrieren.add(textFieldRegEmail);
		textFieldRegEmail.setColumns(16);

		passwordFieldReg1 = new JPasswordField();
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, passwordFieldReg1, 0, SpringLayout.NORTH,
				lblRegPasswort1);
		sl_panelRegistrieren.putConstraint(SpringLayout.WEST, passwordFieldReg1, 0, SpringLayout.WEST,
				textFieldRegEmail);
		sl_panelRegistrieren.putConstraint(SpringLayout.EAST, passwordFieldReg1, 0, SpringLayout.EAST,
				textFieldRegEmail);
		panelRegistrieren.add(passwordFieldReg1);

		JButton btnRegRegistrieren = new JButton("Account registrieren");
		btnRegRegistrieren.setEnabled(false);
		btnRegRegistrieren.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegRegistrieren.setActionCommand("starteRegistrieren");
		panelRegistrieren.add(btnRegRegistrieren);

		JLabel lblRegPasswort2 = new JLabel("Passwort wiederholen");
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, lblRegPasswort2, 10, SpringLayout.SOUTH,
				lblRegPasswort1);
		sl_panelRegistrieren.putConstraint(SpringLayout.WEST, lblRegPasswort2, 0, SpringLayout.WEST, lblRegPasswort1);
		lblRegPasswort2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelRegistrieren.add(lblRegPasswort2);

		KeyListener kL = (new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (checkRegFields()) {
					btnRegRegistrieren.setEnabled(true);
				} else {
					btnRegRegistrieren.setEnabled(false);
				}
			}
		});

		passwordFieldReg2 = new JPasswordField();
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, passwordFieldReg2, 0, SpringLayout.NORTH,
				lblRegPasswort2);
		sl_panelRegistrieren.putConstraint(SpringLayout.WEST, passwordFieldReg2, 0, SpringLayout.WEST,
				passwordFieldReg1);
		sl_panelRegistrieren.putConstraint(SpringLayout.EAST, passwordFieldReg2, 0, SpringLayout.EAST,
				passwordFieldReg1);
		panelRegistrieren.add(passwordFieldReg2);

		JLabel lblRegNickname = new JLabel("Nickname");
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, btnRegRegistrieren, 30, SpringLayout.NORTH,
				lblRegNickname);
		sl_panelRegistrieren.putConstraint(SpringLayout.WEST, btnRegRegistrieren, 0, SpringLayout.WEST, lblRegNickname);
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, lblRegNickname, 10, SpringLayout.SOUTH, lblRegPasswort2);
		sl_panelRegistrieren.putConstraint(SpringLayout.WEST, lblRegNickname, 0, SpringLayout.WEST, lblRegPasswort2);
		lblRegNickname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelRegistrieren.add(lblRegNickname);

		textFieldRegNickname = new JTextField();
		sl_panelRegistrieren.putConstraint(SpringLayout.NORTH, textFieldRegNickname, 0, SpringLayout.NORTH,
				lblRegNickname);
		sl_panelRegistrieren.putConstraint(SpringLayout.WEST, textFieldRegNickname, 0, SpringLayout.WEST,
				passwordFieldReg2);
		sl_panelRegistrieren.putConstraint(SpringLayout.SOUTH, textFieldRegNickname, 0, SpringLayout.SOUTH,
				lblRegNickname);
		sl_panelRegistrieren.putConstraint(SpringLayout.EAST, textFieldRegNickname, 0, SpringLayout.EAST,
				passwordFieldReg2);
		textFieldRegNickname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelRegistrieren.add(textFieldRegNickname);
		textFieldRegNickname.setColumns(10);

		JPanel panelErfolg = new JPanel();
		panelErfolg.setVisible(false);
		panelErfolg.setBackground(Color.WHITE);
		tabbedPane.addTab("Erfolgreicher Login", null, panelErfolg, null);
		tabbedPane.setEnabledAt(2, false);
		SpringLayout sl_panelErfolg = new SpringLayout();
		panelErfolg.setLayout(sl_panelErfolg);

		JLabel lblLoginErfolgreich = new JLabel("LOGIN ERFOLGREICH!");
		lblLoginErfolgreich.setBackground(Color.GREEN);
		sl_panelErfolg.putConstraint(SpringLayout.SOUTH, lblLoginErfolgreich, -10, SpringLayout.SOUTH, panelErfolg);
		sl_panelErfolg.putConstraint(SpringLayout.EAST, lblLoginErfolgreich, -10, SpringLayout.EAST, panelErfolg);
		lblLoginErfolgreich.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panelErfolg.putConstraint(SpringLayout.NORTH, lblLoginErfolgreich, 10, SpringLayout.NORTH, panelErfolg);
		sl_panelErfolg.putConstraint(SpringLayout.WEST, lblLoginErfolgreich, 10, SpringLayout.WEST, panelErfolg);
		lblLoginErfolgreich.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblLoginErfolgreich.setOpaque(true);
		lblLoginErfolgreich.setBackground(new Color(0.15f, 1.0f, 0.25f, 0.3333f));
		panelErfolg.add(lblLoginErfolgreich);

		// Action Listener implementieren für Action Commands
		ActionListener aL = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "geheZuTabRegistrieren": {
					// Zum Tab Registrieren wechseln.
					tabbedPane.setSelectedIndex(1);
					break;
				}
				case "loginValidieren": {
					// Hack um aus char[] ein String zu formen
					String pw = "";
					for (char c : passwordField.getPassword()) {
						pw += c;
					}
					// Prüfen des Logins durch Controller
					if (db.checkLogin(textAreaEmail.getText(), pw)) {
//						System.out.println("Gut.");
						panelErfolg.setVisible(true);
						panelErfolg.setEnabled(true);
						tabbedPane.setSelectedIndex(2);

						JDialogErfolg dialog = new JDialogErfolg("Erfolg!");
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} else {
						
					}
					break;
				}
				case "starteRegistrieren": {
//					db.getNextAvailableAccountID();
//					 Hack um aus char[] ein String zu formen
					String pw = "";
					for (char c : passwordFieldReg1.getPassword()) {
						pw += c;
					}

					if (db.createAccount(textFieldRegNickname.getText(), pw, textFieldRegEmail.getText(), null)) {
						JDialogErfolg dialog = new JDialogErfolg("Erfolg!");
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} else {
						System.err.println("Account anlegen fehlgeschlagen!");
					}
					break;
				}
				}
			}
		};

		// Actionlistener hinzufügen
		btnLogin.addActionListener(aL);
		btnRegistrieren.addActionListener(aL);
		btnRegRegistrieren.addActionListener(aL);

		// KeyListener hinzufügen
		passwordFieldReg1.addKeyListener(kL);
		passwordFieldReg2.addKeyListener(kL);
		textFieldRegEmail.addKeyListener(kL);
		textFieldRegNickname.addKeyListener(kL);

		// WindowListener aktivieren und Window hinzufügen
		aktiviereWindowListener(this);

		// Checken der DB Verbindung
		if (db.isDbConnected()) {
			textPaneInfoPaneLogin.setText("DB Verbindung erfolgreich!\nLogin und Registrieren möglich.");
		} else {
			textPaneInfoPaneLogin.setText("DB Verbindung fehlgeschlagen!");
		}
	}

	/**
	 * Funktion um aus Char[] einen String zu Formen aus Faulheits-Gründen.
	 * 
	 * @param pwc char[] aus PasswordField.
	 * @return String aus char[].
	 */
	private String pw2string(char[] pwc) {
		String pws = "";
		for (char c : pwc) {
			pws += c;
		}
		return pws;
	}

	private boolean checkRegFields() {
		if (passwordFieldReg2.getPassword().length == 0 && textFieldRegEmail.getText().length() == 0
				&& textFieldRegNickname.getText().length() == 0) {
			return false;
		} else {
			if (pw2string(passwordFieldReg1.getPassword()).compareTo(pw2string(passwordFieldReg2.getPassword())) == 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Fügt dem übergebenen Frame einen WindowListener hinzu und fügt eine Aktion
	 * hinzu beim Schließen.
	 * 
	 * @param frame Der Frame, zu welchem der WindowListener hinzugefügt werden
	 *              soll.
	 */
	private void aktiviereWindowListener(JFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				db.closeConnection();
			}
		});
	}
}
