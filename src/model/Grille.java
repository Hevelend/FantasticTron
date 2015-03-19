package model;

/**			GRILLE
 j(y)  ^
	   |
  	   | 0  1  2  3 
	  0|-------------->
	  1|    		 i(x)
	  2|
	  3|

 */
public class Grille {
	private int largeur = 200, hauteur = 130;
	private byte[][] plateau;

	// Constructeur
	public Grille() {
		plateau = new byte[hauteur][largeur];
		initGrille();
	}

	// initialisée par défaut à -1
	public synchronized void initGrille() {
		for(int i=0; i<hauteur; i++)
			for(int j=0; j<largeur; j++)
				plateau[i][j] = -1;
	}

	// Modifieur
	public synchronized boolean setPos(Joueur j) {
		int X = j.getPosX(), Y = j.getPosY();

		// Si on est dans les limites du plateau
		if(X>=0 && Y>=0 && X<largeur && Y<hauteur) {
			// S'il n'y a pas colision
			if(plateau[Y][X] == -1) {
				plateau[Y][X] = (byte) j.getRole();
				return true;
			}
		}
		return false;
	}

	// Accesseurs
	public synchronized byte getPos(int x, int y) {
		try {
			return plateau[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {
			//System.err.println("getPos (x,y) NAWAK !!!!!!!!!!!!" + e);
			return -100;
		}
	}

	public synchronized byte getPos(Joueur j) {
		try {
			return plateau [j.getPosY()] [j.getPosX()] ; 
		} catch (ArrayIndexOutOfBoundsException e) {
			//System.err.println("getPos (joueur) NAWAK !!!!!!!!!!!!" + e);
			return -100;
		}
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	@Override
	public String toString() {
		String str = "<===  Grille  ===>\n";
		for(int y=0; y<hauteur; y++) {
			for(int x=0; x<largeur; x++) {
				str += "\t" + plateau[y][x];
			}
			str += "\n";
		}
		return str;
	}
}
