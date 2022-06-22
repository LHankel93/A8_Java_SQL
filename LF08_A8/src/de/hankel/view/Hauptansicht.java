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
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Hauptansicht extends JFrame {

	private DB db;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

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
		setBounds(100, 100, 467, 368);
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

		JPanel panelRegistrieren = new JPanel();
		panelRegistrieren.setBackground(Color.WHITE);
		tabbedPane.addTab("Registrieren",
				new ImageIcon(Hauptansicht.class.getResource("/img/user-plus-solid_small.png")), panelRegistrieren,
				"Hier kann ein neuer Benutzer-Account in der EGameDarling Datenbank angelegt werden.");
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		panelRegistrieren.setLayout(new SpringLayout());

		JPanel panelErfolg = new JPanel();
		panelErfolg.setBackground(Color.WHITE);
		panelErfolg.setVisible(false);
		tabbedPane.addTab("Erfolgreicher Login", null, panelErfolg, null);
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
		tabbedPane.setEnabledAt(2, false);

		// Action Listener implementieren für Action Commands
		// Action Listener implementieren
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
					} else {
//						System.out.println("Schlecht.");
					}
					break;
				}
				}
			}
		};

		// Actionlistener hinzufügen
		btnLogin.addActionListener(aL);
		btnRegistrieren.addActionListener(aL);
	}
}
