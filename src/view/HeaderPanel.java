package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Joueur;
import controller.ThreadJoueur;

public class HeaderPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton debut;
	private ThreadJoueur[] tabThreadJou;
	private Joueur[] tabJou;
	private JLabel lblJoueur;
	private JLabel lblOrdi;

	// Constructeur
	public HeaderPanel(Joueur[] tabJou, ThreadJoueur[] tabThreadJou) {
		this.tabThreadJou = tabThreadJou;
		this.tabJou = tabJou;
		lblJoueur = new JLabel(); 
		lblOrdi = new JLabel(); 

		setBackground(Color.DARK_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		debut = new JButton("Commencer");
		debut.setFocusable(false);		// Sinon le focus est sur le bouton...
		// et donc le listenner sur les flèches du clavier est HS
		debut.addActionListener(new ClicCommencerListener());
		add(debut);

		lblJoueur = new JLabel("   User : "+ 0);
		lblJoueur.setForeground(tabJou[0].getCouleur());
		add(lblJoueur);

		lblOrdi = new JLabel("   Ordi : "+ 0);
		lblOrdi.setForeground(tabJou[1].getCouleur());
		add(lblOrdi);
	}

	public void setCommencerTrue() {

		debut.setEnabled(true);


	}

	public void  setLblScore() {
		if(!tabJou[0].isDead())
			lblJoueur.setText("   User :  "+ tabJou[0].getScore());
		else
			lblOrdi.setText("   Ordi : " + tabJou[1].getScore());

	}

	// Listenner du bouton
	class ClicCommencerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clic commencer !!");
			debut.setEnabled(false);
			ThreadJoueur.grille.initGrille();	// Réinitialise la grille (clean)

			// Lance tous les Threads des joueurs
			for(ThreadJoueur thj : tabThreadJou) {
				if(!thj.isAlive())
					thj.start();	// start
				else {
					thj.relancer();	// ou réveille
				}
			}
		}
	}
}
