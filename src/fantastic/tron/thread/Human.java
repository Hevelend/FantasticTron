package fantastic.tron.thread;

import fantastic.tron.player.Player;

public class Human extends PlayerStateManager {

	public Human(Player _player) {
		super(_player);
	}

	@Override
	public void run() {
		while (true) {
			ifDead();
			player.playerMovement();
			
			// Fin de la game si le joueur heurte quelque chose
			if (gameBoard.setPos(player) == false) {
				System.out.println("Vous venez de perdre");
				player.setDead(true);
			}
			
			try {
				sleep(player.getSpeed());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
