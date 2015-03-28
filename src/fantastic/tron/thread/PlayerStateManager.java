package fantastic.tron.thread;

import fantastic.tron.game.GameBoard;
import fantastic.tron.player.*;

public class PlayerStateManager extends Thread {
	public static GameBoard gameBoard = new GameBoard();
	protected Player player;

	public PlayerStateManager(Player _player) {
		super(_player.getName());
		this.player = _player;
		gameBoard.setPos(_player);
	}

	// Gestion de la mort du joueur
	public synchronized void ifDead() {
		try {
			while(player.isDead()) {
				System.out.println(getName() + " est mort");
				wait();	// Le joueur est mort il doit attendre la prochaine manche
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	// On relance une manche, respawn du joueur mort
	public synchronized void restart() {
		player.reinitPos();
		gameBoard.setPos(player);
		notifyAll();
	}

}
