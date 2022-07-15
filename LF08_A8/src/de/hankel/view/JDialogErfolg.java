package de.hankel.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Klasse welche JDialog um eine Erfolgsnachricht ergänzt.
 * 
 * @author Lorenz Hankel
 *
 */
public class JDialogErfolg extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ActionListener aL;

	/**
	 * Erstellt einen JDialog mit einer Nachricht.
	 * 
	 * @param message String Eine Nachricht für den Nutzer.
	 */
	public JDialogErfolg(String message) {
		aktiviereActionListener();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JDialogErfolg.class.getResource("/img/arrow-right-to-bracket-solid_small.png")));
		setTitle("Erfolg!");
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 313, 156);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		FlowLayout fl_contentPanel = new FlowLayout();
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblErfolg = new JLabel(message);
			lblErfolg.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblErfolg.setIcon(new ImageIcon(JDialogErfolg.class.getResource("/img/check-solid_small.png")));
			lblErfolg.setBackground(Color.GREEN);
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
		this.dispose();
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
