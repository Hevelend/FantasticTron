package fantastic.tron.screen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fantastic.tron.game.GameLoop;
import fantastic.tron.player.Player;
import fantastic.tron.thread.PlayerStateManager;

public class Header extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton startingButton; // Bouton commencer
	private PlayerStateManager[] threadsTab; // Tableau des threads
	private Player[] playersTab; // Tableau des joueurs
	private JLabel labelPlayer;
	private JLabel labelIA;
	private GameWindow mainWindow;
	private GameLoop game;

	public Header(GameLoop _game, GameWindow _mainWindow, Player[] _playersTab, PlayerStateManager[] _threadsTab) {
		this.game = _game;
		this.threadsTab = _threadsTab;
		this.playersTab = _playersTab;
		labelPlayer = new JLabel(); 
		labelIA = new JLabel(); 

		setBackground(Color.DARK_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		startingButton = new JButton("Commencer (Appuyez sur Entrée)");
		startingButton.setFocusable(false);
		startingButton.addActionListener(new gameStartListener());
		add(startingButton);
		this.mainWindow = _mainWindow;
		this.mainWindow.setStartBut(startingButton);

		labelPlayer = new JLabel("   Player : "+ 0);
		labelPlayer.setForeground(_playersTab[0].getColor());
		add(labelPlayer);

		labelIA = new JLabel("   IA : "+ 0);
		labelIA.setForeground(_playersTab[1].getColor());
		add(labelIA);
	}

	public void buttonActivation() {
		startingButton.setEnabled(true);
	}

	public void  scoringRefresh() {
		if(!playersTab[0].isDead()) {
			labelPlayer.setText("   Player :  "+ playersTab[0].getScoring());
		} else {
			labelIA.setText("   AI : " + playersTab[1].getScoring());
		}

	}

	// Listenner du bouton commencer
	class gameStartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Début de la partie.");
			
			// Nettoyage du plateau de jeu et démarrage de la partie
			startingButton.setEnabled(false);
			PlayerStateManager.gameBoard.initGameBoard();

			if(!game.zik1.isInterrupted())
				game.zik1.close();
			if(!game.zik2.isAlive())
				game.zik2.start();
			CompteARebours cr = new CompteARebours();
			new Thread(cr).start();
		}
		
		public class CompteARebours implements Runnable {
		    public void run() {
		    	
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// REBOURS
				game.txtRebours = "3";

				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				game.txtRebours = "2";
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.txtRebours = "1";
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.txtRebours = "GO!";
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.txtRebours = "";
				
				// On démarre tous les threads de notre tableau
				for(PlayerStateManager threads : threadsTab) {
					if(!threads.isAlive())
						threads.start(); // On démarre les threads
					else {
						threads.restart();	// On redémarre les threads
					}
				}
		    }
		}
	}
}
