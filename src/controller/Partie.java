package controller;

import java.awt.BorderLayout;

import model.Joueur;
import view.FenetrePlateau;
import view.HeaderPanel;
import view.JeuPanel;

public class Partie extends Thread {

	private ThreadJoueur[] tabThreadJou;
	private Joueur[] tabJou;
	private HeaderPanel hPan;
	private JeuPanel jPan;
	Joueur humain;
	boolean GameOver;

	public Partie() {
		GameOver = false;
		tabThreadJou = new ThreadJoueur[4];
		tabJou = new Joueur[4];

		// Cr�e les Joueurs( nom, position, vitesse)
		humain = new Joueur("Youn�s", 0, 20);
		tabJou[0] = humain;
		tabJou[1] = new Joueur("Ordi 1", 1, 20);
		tabJou[2] = new Joueur("Ordi 2", 2, 20);
		tabJou[3] = new Joueur("Ordi 3", 3, 20);

		// cr�e les Threads de contr�le
		tabThreadJou[0] = new ThreadHumain(humain);
		tabThreadJou[1] = new ThreadIA(humain, tabJou[1]);
		tabThreadJou[2] = new ThreadIA(humain, tabJou[2]);
		tabThreadJou[3] = new ThreadIA(humain, tabJou[3]);

		// Cr�ation de l'interface
		FenetrePlateau fenetrePlateau = new FenetrePlateau(humain);
		hPan = new HeaderPanel(tabJou, tabThreadJou);
		jPan = new JeuPanel();

		fenetrePlateau.getContentPane().add(hPan, BorderLayout.NORTH);
		fenetrePlateau.getContentPane().add(jPan, BorderLayout.CENTER);
		fenetrePlateau.setVisible(true);
	}

	@Override
	public void run() {
		try {
			int vivant;

			while (!GameOver) {
				vivant = 3; // Vivant contient le nombre d'ordinateurs vivants
				for (int i = 1; i < tabJou.length; i++) {
					if (tabJou[i].isDead())
						vivant--;
				}

				if (vivant == 0 || humain.isDead()) { // Fin de manche

					if (vivant == 0) { // Fin de manche : victoire de l'humain
						tabJou[0].setScore();
					} else { // Fin de manche : victoire de l'ordinateur
						tabJou[1].setScore();
					}
					hPan.setLblScore();
					// Tue tous les threads pour les r�initialiser correctement
					for (Joueur jou : tabJou) {
						jou.setDead(true);    // Tue
					}
					sleep(40);
					for (Joueur jou : tabJou) {
						jou.setDead(false);    // R�anime
					}

					// On arrete la partie quand l'un des scores est sup�rieur � 3
					if (tabJou[1].getScore() >= 3 || tabJou[0].getScore() >= 3) {
						jPan.setOver();
						GameOver = true;
					}
					else {
						hPan.setCommencerTrue();
					}
				}

				sleep(16); // On v�rifie que la partie n'est pas termin�e �
				// toutes les frames
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
