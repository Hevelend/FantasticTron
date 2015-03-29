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
	private JLabel labelPlayer; // Label score joueur
	private JLabel labelIA1; // Label score Hard IA
	private JLabel labelIA2; // Label score Medium IA
	private JLabel labelIA3; // Label score Easy IA
	private GameWindow mainWindow;
	private GameLoop game;

	public Header(GameLoop _game, GameWindow _mainWindow, Player[] _playersTab, PlayerStateManager[] _threadsTab) {
		this.game = _game;
		this.threadsTab = _threadsTab;
		this.playersTab = _playersTab;
		labelPlayer = new JLabel(); 
		labelIA1 = new JLabel(); 
		labelIA2 = new JLabel(); 
		labelIA3 = new JLabel(); 

		setBackground(Color.DARK_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		startingButton = new JButton("Commencer (Appuyez sur Entrée)");
		startingButton.setFocusable(false);
		startingButton.addActionListener(new gameStartListener());
		add(startingButton);
		this.mainWindow = _mainWindow;
		this.mainWindow.setStartBut(startingButton);

		labelPlayer = new JLabel("   Player : " + 0);
		labelPlayer.setForeground(_playersTab[0].getColor());
		add(labelPlayer);
		labelIA1 = new JLabel("   Hard IA : " + 0);
		labelIA1.setForeground(_playersTab[1].getColor());
		add(labelIA1);
		labelIA2 = new JLabel("   Medium IA : " + 0);
		labelIA2.setForeground(_playersTab[2].getColor());
		add(labelIA2);
		labelIA3 = new JLabel("   Easy IA : " + 0);
		labelIA3.setForeground(_playersTab[3].getColor());
		add(labelIA3);
	}

	public void buttonActivation() {
		startingButton.setEnabled(true);
	}

	public void  scoringRefresh() {
		if(!playersTab[0].isDead()) {
			labelPlayer.setText("   Player :  "+ playersTab[0].getScoring());
		} else {
			labelIA1.setText("   Hard IA : " + playersTab[1].getScoring());
			labelIA2.setText("   Medium IA : " + playersTab[2].getScoring());
			labelIA3.setText("   Easy IA : " + playersTab[3].getScoring());
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
