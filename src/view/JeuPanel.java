package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.ThreadJoueur;
import model.Joueur;

public class JeuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_CASE = 4;

	public JeuPanel() {
		setBackground(new Color(120, 222, 245));

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

		// Dessine un rectangle pour chaque case de la grille
		for(int x=0; x < ThreadJoueur.grille.getLargeur(); x++) {
			for(int y=0; y < ThreadJoueur.grille.getHauteur(); y++) {
				Rectangle2D carre = new Rectangle2D.Double(
						x*TAILLE_CASE,	// X coin supérieur gauche
						y*TAILLE_CASE,	// Y coin supérieur gauche
						TAILLE_CASE,	// largeur (-1 permet un espacement entre cases)
						TAILLE_CASE);	// hauteur (idem)
				g2.setPaint(Joueur.getColor(ThreadJoueur.grille.getPos(x, y))); 
				g2.fill(carre);
			}
		}
	}
	public void setOver() {
		JLabel lblOver = new JLabel();
		lblOver.setIcon (new ImageIcon ("images\\image.png"));
		this.add(lblOver);


	}

}
