package fantastic.tron.screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import fantastic.tron.player.*;

public class GameWindow extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private Player human;
	private JButton startGame;

	public GameWindow(Player _human) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
		this.human = _human;
		setTitle("Tron Game");
		setSize(817, 596);
		setLocationRelativeTo(null); // Au centre de l'écran
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new ActionUser());
	}

	class ActionUser implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) { }

		// Déplacement du joueur avec vérification pour éviter qu'il
		// se retourne et se marche sur lui même
		@Override
		public void keyPressed(KeyEvent e) {
			if(!human.isDead()) {
				if(e.getKeyCode() == KeyEvent.VK_UP){
					if(human.getDirection() != "down") {
						human.setDirection("up");
					}
				}

				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					if(human.getDirection() != "up") {
						human.setDirection("down");	
					}
				}

				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					if(human.getDirection() != "right") {
						human.setDirection("left");
					}
				}

				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					if(human.getDirection() != "left") {
						human.setDirection("right");
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) { }		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			startGame.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	// Le listener pour la touche entrée active le boutton COMMENCER
	public void setStartBut(JButton _startGame) {
		startGame = _startGame;
	}

}