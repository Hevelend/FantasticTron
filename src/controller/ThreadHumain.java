package controller;

import model.Joueur;

public class ThreadHumain extends ThreadJoueur {

	public ThreadHumain(Joueur jou) {
		super(jou);
	}

	@Override
	public void run() {
		while (true) {
			pauseSiMort();
			jou.avance();
			if (grille.setPos(jou) == false) {
				// Fin de partie (l'humain s'est crashé)
				System.out.println("T'es mort bouffon !!!!");
				jou.setDead(true);
			}
			try {
				sleep(jou.getVitesse());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
