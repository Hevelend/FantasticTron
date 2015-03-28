package fantastic.tron.player;

import java.awt.Color;
import java.util.Random;

public class Player {
	private String name; // Nom du joueur. Pour les impréssions console
	private int category; // 0 = player. Les autres = IA
	private int scoring; // Score du joueur
	private Color color; // Couleur du joueur
	private int posX, posY; // Positions du joueur
	private String direction; // Direction du joueur
	private int speed; // Vitesse du joueur
	private boolean isDead; // true = joueur mort

	public Player() {
		this("Joueur par défaut", 0, 0);
	}

	public Player(String _name, int _category, int _speed) {
		this(_name, _category, _category * 20 + 30, _category * 30 + 20, _speed);
	}

	public Player(String _name, int _category, int _posX, int _posY, int _speed) {

		this.name = _name;
		this.category = _category;
		scoring = 0;
		color = Player.getColor((byte) _category);
		this.posX = _posX;
		this.posY = _posY;
		this.speed = _speed;
		isDead = false;
		
		System.out.println("Le joueur "+ name + " est entré dans la partie.");
		
		// On génere une direction aléatoire
		switch(new Random().nextInt(2)) {
		case 0:
			this.direction = "right";
			break;
		case 1:
			this.direction = "down";
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public String getDirection()
	{
		return direction;
	}
	public String getName() {
		return name;
	}

	public int getCategory() {
		return category;
	}

	public int getScoring() {
		return scoring;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public Color getColor() {
		return color;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setScoring() {
		this.scoring += 1;
	}

	public void setSpeed(int _speed) {
		this.speed = _speed;
	}

	public void setDirection(String _direction) {
		this.direction = _direction;
	}


	public void playerMovement() {
		switch (direction) {
		case "up":
			posY --;
			break;
		case "down":
			posY ++;
			break;
		case "right":
			posX ++;
			break;
		case "left":
			posX --;
			break;
		}
	}

	public void reinitPos() {
		posX = category * 20 + 30;
		posY = category * 30 + 20;
	}
	
	public static Color getColor(byte _category) {
		switch (_category) {
		case -1:
			return Color.LIGHT_GRAY;
		case 0:
			return Color.CYAN;
		case 1:
			return Color.ORANGE;
		case 2:
			return Color.GREEN;
		case 3:
			return Color.RED;
		}
		return Color.WHITE;
	}
}
