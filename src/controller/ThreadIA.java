package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import model.Joueur;

public class ThreadIA extends ThreadJoueur {
	Joueur humain;
	
	public ThreadIA(Joueur humain, Joueur ordi) {
		super(ordi);
		this.humain = humain;
		
		Timer refresh = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jou.getRole() == 1)
				{
					switch(new Random().nextInt(4)) {
					case 0:
						if(jou.getDirection() != 'd' )
							jou.setDirection('g'); 
						break;
					case 1:
						if(jou.getDirection() != 'h' )
							jou.setDirection('b'); 
					case 2:
						if(jou.getDirection() != 'g' )
							jou.setDirection('d'); 
					case 3:
						if(jou.getDirection() != 'b' )
							jou.setDirection('h'); 
					}
				}
			}
		});
		refresh.start();
	}

	@Override
	public void run() {
		while(true) {
			int rand = (int) (Math.random() *10);
			
			int x = jou.getPosX();
			int y = jou.getPosY();
			
			pauseSiMort();
			
			// une intelligence pour suivre le "humain"
			if(jou.getRole() == 2)
				jou.setDirection(humain.getDirection());
			// Si la case devant est pas libre alors on tourne	
			
			
			
			// direction droite
			if(jou.getDirection() == 'd' && grille.getPos(x+1 ,y) != -1)
			{
				// si le haut et le bas sont libre
	
				if(jou.getRole() == 3 &&
						grille.getPos(x ,y-1) == -1 &&
						grille.getPos(x ,y+1) == -1)	
				{
					if(rand <5) 						jou.setDirection('b');
					else							    jou.setDirection('h');
				}
				else 
				{
					if(grille.getPos(x ,y-1) == -1)	 	jou.setDirection('h');
					else 								jou.setDirection('b');
				}
	
				}
				
				else if(jou.getDirection() == 'g' &&
						grille.getPos(x-1, y) != -1)  // direction gauche
				{
				// si le haut et le bas sont libre
	
				if(jou.getRole() == 3 &&
						grille.getPos(x ,y-1) == -1 &&
						grille.getPos(x ,y+1) == -1)
				{
					if(rand <5) 						jou.setDirection('b');
					else							    jou.setDirection('h');
				}
				else 
				{
					if(grille.getPos(x ,y-1) == -1)	 	jou.setDirection('h');
					else 								jou.setDirection('b');
				}

			}
			
			// direction haut
			else if(jou.getDirection() == 'h' && grille.getPos(x, y-1) != -1)  
			{ 
				{
				// si la gauche  et la droite sont libre
				if(jou.getRole() == 3 && 
						grille.getPos(x+1 ,y) == -1 &&
						grille.getPos(x-1 ,y) == -1)
				{
					if(rand <5) 						jou.setDirection('g');
					else							    jou.setDirection('d');
				}
				else 
				{
					if(grille.getPos(x-1 ,y) == -1)	 	jou.setDirection('g');
					else 								jou.setDirection('d');
				}

				}
			}
			
			// direction bas
			else if(jou.getDirection() == 'b' && grille.getPos(x, y+1) != -1)  
			{ 
				{
				// si la gauche  et la droite sont libre
				if(jou.getRole() == 3 && 
						grille.getPos(x+1 ,y) == -1 &&
						grille.getPos(x-1 ,y) == -1)
				{
					if(rand <5) 						jou.setDirection('g');
					else							    jou.setDirection('d');
				}
				else 
				{
					if(grille.getPos(x-1 ,y) == -1)	 	jou.setDirection('g');
					else 								jou.setDirection('d');
				}

				}
			}

			jou.avance();		// On avance
			
			if(grille.setPos(jou) == false) {	// Si on s'est planté
				// Fin de manche (l'IA s'est crashé)
				System.out.println("Ordinateur crashé !!!!");
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
