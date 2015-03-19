package controller;

import model.*;

public class ThreadJoueur extends Thread {
	public static Grille grille = new Grille();
	protected Joueur jou;

	// Constructeur
	public ThreadJoueur(Joueur jou) {
		super(jou.getNom());
		this.jou = jou;
		grille.setPos(jou);
	}

	public synchronized void pauseSiMort() {
		try {
			while(jou.isDead()) {
				System.out.println("Joueur " + getName() + " mort....");
				wait();			// Attend que les autres joueurs
				// le réveillent pour la prochaine manche
				System.out.println("Joueur " + getName() + " réveillé !");
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void relancer() {
		jou.reinitPos();
		grille.setPos(jou);
		notifyAll();
	}

}
