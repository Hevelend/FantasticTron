package controller;

import java.awt.BorderLayout;

import model.Joueur;
import view.FenetreAccueil;
import view.FenetrePlateau;
import view.HeaderPanel;
import view.JeuPanel;

public class Partie extends Thread {

	private ThreadJoueur[] tabThreadJou;
	private Joueur[] tabJou;
	private HeaderPanel hPan;
	private JeuPanel jPan;
	private Joueur humain;
	boolean GameOver;
	
	public String txtRebours = "";
	public ThreadMusic zik1, zik2;

	public Partie() {
		
		GameOver = false;
		tabThreadJou = new ThreadJoueur[4];
		tabJou = new Joueur[4];		
		
		// Crée les Joueurs( nom, position, vitesse)
		humain = new Joueur("Lewis", 0, 20);
		tabJou[0] = humain;
		tabJou[1] = new Joueur("L'idiot du village", 1, 20);
		tabJou[2] = new Joueur("Rival", 2, 20);
		tabJou[3] = new Joueur("Le professionnel", 3, 20);

		// crée les Threads de contrôle
		tabThreadJou[0] = new ThreadHumain(humain);
		tabThreadJou[1] = new ThreadIA(humain, tabJou[1]);
		tabThreadJou[2] = new ThreadIA(humain, tabJou[2]);
		tabThreadJou[3] = new ThreadIA(humain, tabJou[3]);


		
		// Création de l'interface
		FenetrePlateau fenetrePlateau = new FenetrePlateau(humain);
		hPan = new HeaderPanel(this, fenetrePlateau, tabJou, tabThreadJou);
		jPan = new JeuPanel(this);

		fenetrePlateau.getContentPane().add(hPan, BorderLayout.NORTH);
		fenetrePlateau.getContentPane().add(jPan, BorderLayout.CENTER);
		
		// crée les threads de musique
		zik1 = new ThreadMusic("sounds/music1.mp3", true);
		zik2 = new ThreadMusic("sounds/music2.mp3", true);
		
		// fenetre d'accueil
		new FenetreAccueil(fenetrePlateau, zik2);
		// demarage thread et mise en attente
		
		zik1.start();
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
					// Tue tous les threads pour les réinitialiser correctement
					for (Joueur jou : tabJou) {
						jou.setDead(true);    // Tue
					}
					sleep(40);
					for (Joueur jou : tabJou) {
						jou.setDead(false);    // Réanime
					}

					// On arrete la partie quand l'un des scores est supérieur à 3
					if (tabJou[1].getScore() >= 3 || tabJou[0].getScore() >= 3) {
						jPan.setOver();
						GameOver = true;
					}
					else {
						hPan.setCommencerTrue();
					}
				}

				sleep(16); // On vérifie que la partie n'est pas terminée à
				// toutes les frames
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
