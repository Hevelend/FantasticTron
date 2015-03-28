package fantastic.tron.game;

import fantastic.tron.player.Player;

public class GameBoard {
	private int width = 200, height = 130; // Taille du plateau de jeu
	private byte[][] gameBoard; // Plateau de jeu

	public GameBoard() {
		gameBoard = new byte[height][width];
		initGameBoard();
	}

	public synchronized void initGameBoard() {
		for(int i = 0; i < height; i ++)
			for(int j = 0; j < width; j ++)
				gameBoard[i][j] = -1;
	}

	public synchronized boolean setPos(Player j) {
		int X = j.getPosX(), Y = j.getPosY();

		// Pas de colision si on est sur les rebords du plateau
		if(X >= 0 && Y >= 0 && X < width && Y < height) {
			if(gameBoard[Y][X] == -1) {
				gameBoard[Y][X] = (byte) j.getCategory();
				return true;
			}
		}
		return false;
	}

	public synchronized byte getPos(int x, int y) {
		try {
			return gameBoard[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return -100;
		}
	}

	public synchronized byte getPos(Player j) {
		try {
			return gameBoard[j.getPosY()][j.getPosX()] ; 
		} catch (ArrayIndexOutOfBoundsException e) {
			return -100;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
