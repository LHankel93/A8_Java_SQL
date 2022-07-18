package de.hankel.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;

public class JDialogFehler extends JDialogErfolg {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ActionListener aL;
	private boolean kritisch;

	/**
	 * Standardkonstruktor für JDialogFehler Klasse, Super ist die Klasse
	 * JDialogErfolg.
	 * 
	 * @param message  String Die (Fehler-) Nachricht, welche dem Nutzer mitgeteilt
	 *                 werden soll.
	 * @param kritisch Boolean Ist der Fehler kritisch? Bestimmt, ob das Programm
	 *                 geschlossen wird nach schließen des Dialoges.
	 */
	public JDialogFehler(String message, boolean kritisch) {
		super(message); // Super-Standardkonstruktor aufrufen und Fehlernachricht übergeben.
		this.kritisch = kritisch;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setType(Type.POPUP);
		aktiviereActionListener();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JDialogFehler.class.getResource("/img/triangle-exclamation-solid.png")));
		setTitle("Fehler!");
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 637, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		FlowLayout fl_contentPanel = new FlowLayout();
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblErfolg = new JLabel(message);
			lblErfolg.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblErfolg.setIcon(new ImageIcon(JDialogFehler.class.getResource("/img/triangle-exclamation-solid.png")));
			lblErfolg.setBackground(Color.RED);
			contentPanel.add(lblErfolg);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				aktiviereActionListener();
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(aL);
				getRootPane().setDefaultButton(okButton);
			}
		}

	}

	/**
	 * Schließt den Dialog.
	 */
	private void schliesse() {
		if (this.kritisch) {
			System.exit(1);
		} else {
			this.dispose();
		}
	}

	/**
	 * Fügt den ActionListener zum JDialog hinzu und implementiert ActionCommands.
	 */
	private void aktiviereActionListener() {
		// Action Listener implementieren für Action Commands
		aL = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "OK": {
					schliesse();
					break;
				}
				}
			}
		};
	}

}