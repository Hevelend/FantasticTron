package view;

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

import controller.Partie;
import controller.ThreadJoueur;
import model.Joueur;

public class JeuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_CASE = 4;
	private Image bg;

	private Partie p;
	
	public JeuPanel(Partie p) {

		this.p = p;
		try {
			bg = ImageIO.read(new File("images/bg.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// Refresh rate : 16ms*60 = 960ms (~1s) ==> 60fps
		Timer refresh = new Timer(16, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateUI();
			}
		});
		refresh.start();
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(bg, 0, 0, this);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 150));
		g2.setColor(Color.WHITE);
		g2.drawString(p.txtRebours, 340, 280);
		
		// Dessine un rectangle pour chaque case de la grille
		for(int x=0; x < ThreadJoueur.grille.getLargeur(); x++) {
			for(int y=0; y < ThreadJoueur.grille.getHauteur(); y++) {
				Rectangle2D carre = new Rectangle2D.Double(
						x*TAILLE_CASE,	// X coin supérieur gauche
						y*TAILLE_CASE,	// Y coin supérieur gauche
						TAILLE_CASE,	// largeur (-1 permet un espacement entre cases)
						TAILLE_CASE);	// hauteur (idem)
				g2.setPaint(Joueur.getColor(ThreadJoueur.grille.getPos(x, y)));
				if(Joueur.getColor(ThreadJoueur.grille.getPos(x, y)) != Color.LIGHT_GRAY){
					g2.fill(carre);
				}
			}
		}
	}
	public void setOver() {
		JLabel lblOver = new JLabel();
		lblOver.setIcon (new ImageIcon ("images/gameover.png"));
		this.add(lblOver);


	}

}
