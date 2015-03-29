package fantastic.tron.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import fantastic.tron.game.GameLoop;
import fantastic.tron.player.Player;
import fantastic.tron.thread.PlayerStateManager;

public class GameScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_CASE = 4;
	private Image background;
	private GameLoop game;
	
	public GameScreen(GameLoop _game) {

		this.game = _game;
		try {
			background = ImageIO.read(new File("images/bg.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// 60fps : 16ms * 60 = 960 ms
		Timer fps = new Timer(16, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateUI();
			}
		});
		fps.start();
	}


	// Mise a jour de l'affichage
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.drawImage(background, 0, 0, this);
		g2D.setFont(new Font("TimesRoman", Font.PLAIN, 150));
		g2D.setColor(Color.WHITE);
		g2D.drawString(game.txtRebours, 340, 280);
		
		// Dessine dans chaque case du plateau de jeu
		for(int x = 0; x < PlayerStateManager.gameBoard.getWidth(); x ++) {
			for(int y = 0; y < PlayerStateManager.gameBoard.getHeight(); y ++) {
				Rectangle2D carre = new Rectangle2D.Double(x * TAILLE_CASE,
									y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
				g2D.setPaint(Player.getColor(PlayerStateManager.gameBoard.getPos(x, y)));
				if(Player.getColor(PlayerStateManager.gameBoard.getPos(x, y)) != Color.LIGHT_GRAY){
					g2D.fill(carre);
				}
			}
		}
	}
	
	// Affichage du Game Over
	public void setOver(Player[] playersTab) {
		JLabel labelGameOver = new JLabel();
		if(playersTab[0].getScoring() == 3) {
			labelGameOver.setIcon (new ImageIcon ("images/you_win.png"));
		} else {
			labelGameOver.setIcon (new ImageIcon ("images/gameover.png"));
		}
		this.add(labelGameOver);
	}

}
