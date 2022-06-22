package de.hankel;

import java.awt.EventQueue;
import de.hankel.controller.DB;
import de.hankel.view.Hauptansicht;

public class Starte_A8_1 {

	public static void main(String[] args) {
		DB db = new DB();
		// Zum testen der DB Verbindung:
		// db.testdb();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hauptansicht frame = new Hauptansicht(db);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
