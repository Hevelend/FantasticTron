package fantastic.tron.thread;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import fantastic.tron.player.Player;

public class IA extends PlayerStateManager {
	Player human;
	
	public IA(Player _human, Player _ia) {
		super(_ia);
		this.human = _human;
		
		Timer refresh = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(player.getCategory() == 1)
				{
					switch(new Random().nextInt(4)) {
					case 0:
						if(player.getDirection() != "right")
							player.setDirection("left"); 
						break;
					case 1:
						if(player.getDirection() != "up")
							player.setDirection("down"); 
					case 2:
						if(player.getDirection() != "left")
							player.setDirection("right"); 
					case 3:
						if(player.getDirection() != "down")
							player.setDirection("up"); 
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
			int x = player.getPosX();
			int y = player.getPosY();
			
			ifDead();
			
			// IA qui copie les mouvements du joueur
			if(player.getCategory() == 2) {
				player.setDirection(human.getDirection());
			}
						
			// Gestion de la direction des IA
			// On vérifie si la case devant est libre ou non. non alors on tourne
			if(player.getDirection() == "right" && gameBoard.getPos(x+1 ,y) != -1)
			{
				// On vérifie si le haut ou le bas est libre
				if(player.getCategory() == 3 && gameBoard.getPos(x ,y-1) == -1 &&
				   gameBoard.getPos(x ,y+1) == -1) {
					
					if(rand <5) {
						player.setDirection("down");
					} else {
						player.setDirection("up");
					}
				} else {
					if(gameBoard.getPos(x ,y-1) == -1) {
						player.setDirection("up");
					} else {
						player.setDirection("down");
					}
				}
			} else if(player.getDirection() == "left" &&
					  gameBoard.getPos(x-1, y) != -1) {
	
				if(player.getCategory() == 3 &&
					gameBoard.getPos(x ,y-1) == -1 &&
					gameBoard.getPos(x ,y+1) == -1)
				{
					if(rand <5) {
						player.setDirection("down");
					} else{
						player.setDirection("up");
					}
				}
				else 
				{
					if(gameBoard.getPos(x ,y-1) == -1){
						player.setDirection("up");
					} else {
						player.setDirection("down");
					}
				}
			} else if(player.getDirection() == "up" &&
					  gameBoard.getPos(x, y-1) != -1) {
				
				if(player.getCategory() == 3 && 
						gameBoard.getPos(x+1 ,y) == -1 &&
						gameBoard.getPos(x-1 ,y) == -1) {
					
					if(rand <5) {
						player.setDirection("left");
					} else {
						player.setDirection("right");
					}
				} else {
					if(gameBoard.getPos(x-1 ,y) == -1) {
						player.setDirection("left");
					} else {
						player.setDirection("right");
					}
				}
			} else if(player.getDirection() == "down" &&
					  gameBoard.getPos(x, y+1) != -1) {

				if(player.getCategory() == 3 && 
					gameBoard.getPos(x+1 ,y) == -1 &&
					gameBoard.getPos(x-1 ,y) == -1) {
					
					if(rand <5) {
						player.setDirection("left");
					} else {
						player.setDirection("right");
					}
				} else {
					if(gameBoard.getPos(x-1 ,y) == -1) {
						player.setDirection("left");
					} else {
						player.setDirection("right");
					}
				}
			}

			// On valide le déplacement
			player.playerMovement();
			
			if(gameBoard.setPos(player) == false) {
				System.out.println("Une IA vient de mourrir.");
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
