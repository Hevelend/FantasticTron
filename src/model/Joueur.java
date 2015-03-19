package model;

import java.awt.Color;
import java.util.Random;

public class Joueur {
	private String nom;

	/*	Role :
	 * 0 = humain
	 * 1 = ordinateur d'IA 1 
	 * 2 = ordinateur d'IA 2 
	 * 3 = ordinateur d'IA 3 */
	private int role;
	private int score;
	private Color couleur;
	private int posX, posY;
	private char direction;
	private int vitesse;
	private boolean isDead;

	/*  Constructeurs  */
	public Joueur() {
		this("Joueur par défaut", 0, 0);
	}

	public Joueur(String nom, int role, int vitesse) {
		this(nom, role, role*20+30, role*30+20, vitesse);
	}

	public Joueur(String nom, int role, int posX,
			int posY, int vitesse) {

		this.nom = nom;
		this.role = role;
		score = 0;
		couleur = Joueur.getColor((byte) role);
		this.posX = posX;
		this.posY = posY;
		this.vitesse = vitesse;
		isDead = false;
		// Direction aléatoire (droite ou bas 1 chance sur 2)
		switch(new Random().nextInt(2)) {
		case 0:
			this.direction = 'd';
			break;
		case 1:
			this.direction = 'b';
		}
		System.out.println("player created : "+nom + " dir="+ direction);
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	/*  Getters  */
	public char getDirection()
	{
		return direction;
	}
	public String getNom() {
		return nom;
	}

	public int getRole() {
		return role;
	}

	public int getScore() {
		return score;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public Color getCouleur() {
		return couleur;
	}

	public int getVitesse() {
		return vitesse;
	}

	/*  Setters  */
	public void setScore() {
		this.score++;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}


	public void avance() {
		switch (direction) {
		case 'h':
			posY--;
			break;
		case 'b':
			posY++;
			break;
		case 'd':
			posX++;
			break;
		case 'g':
			posX--;
			break;
		}
	}

	public void reinitPos() {
		posX = role*20+30;
		posY = role*30+20;
	}

	// Méthode statique
	public static Color getColor(byte role) {
		switch (role) {
		case -1:
			return Color.LIGHT_GRAY;
		case 0:
			return Color.GREEN;
		case 1:
			return Color.YELLOW;
		case 2:
			return Color.BLUE;
		case 3:
			return Color.RED;
		}
		return Color.WHITE;
	}
}
