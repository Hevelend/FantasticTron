package fantastic.tron.game;

import java.awt.BorderLayout;

import fantastic.tron.player.Player;
import fantastic.tron.screen.WelcomeScreen;
import fantastic.tron.screen.GameWindow;
import fantastic.tron.screen.Header;
import fantastic.tron.screen.GameScreen;
import fantastic.tron.thread.Human;
import fantastic.tron.thread.IA;
import fantastic.tron.thread.PlayerStateManager;
import fantastic.tron.thread.Music;

public class GameLoop extends Thread {

	private PlayerStateManager[] threadsTab;
	private Player[] playersTab;
	private Header head;
	private GameScreen gameScreen;
	private Player human;
	boolean GameOver;
	
	public String txtRebours = "";
	public Music zik1, zik2;

	public GameLoop() {
		
		GameOver = false;
		threadsTab = new PlayerStateManager[4];
		playersTab = new Player[4];		
		
		// On initialise les joueurs
		human = new Player("Lewis", 0, 20);
		playersTab[0] = human;
		playersTab[1] = new Player("L'idiot du village", 1, 20);
		playersTab[2] = new Player("Rival", 2, 20);
		playersTab[3] = new Player("Le professionnel", 3, 20);

		// On initialise les threads
		threadsTab[0] = new Human(human);
		threadsTab[1] = new IA(human, playersTab[1]);
		threadsTab[2] = new IA(human, playersTab[2]);
		threadsTab[3] = new IA(human, playersTab[3]);

		// Initialisation de l'interface de jeu
		GameWindow mainWindow = new GameWindow(human);
		head = new Header(this, mainWindow, playersTab, threadsTab);
		gameScreen = new GameScreen(this);
		mainWindow.getContentPane().add(head, BorderLayout.NORTH);
		mainWindow.getContentPane().add(gameScreen, BorderLayout.CENTER);
		
		// Initialisation des threads pour la musique
		zik1 = new Music("sounds/music1.mp3", true);
		zik2 = new Music("sounds/music2.mp3", true);
		
		// fenetre d'accueil
		new WelcomeScreen(mainWindow, zik2);
		zik1.start();
	}

	@Override
	public void run() {
		try {
			int iaAlive; // Nombre de ia encore en jeu

			while (!GameOver) {
				iaAlive = 3;
				for (int i = 1; i < playersTab.length; i++) {
					if (playersTab[i].isDead())
						iaAlive --;
				}
				
				// Fin de manche
				if (iaAlive == 0 || human.isDead()) {
					if (iaAlive == 0) {
						playersTab[0].setScoring(); // +1 pour le joueur
					} else {
						playersTab[1].setScoring(); // +1 pour les ia
					}
					head.scoringRefresh();
					
					// On tue tous les threads puis on les relance
					for (Player player : playersTab) {
						player.setDead(true);
					}
					sleep(40);
					for (Player player : playersTab) {
						player.setDead(false);
					}

					// Vérification si la partie est terminée
					if (playersTab[1].getScoring() >= 5 || playersTab[0].getScoring() >= 5) {
						gameScreen.setOver();
						GameOver = true;
					}
					else {
						head.buttonActivation();
					}
				}
				sleep(16);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
