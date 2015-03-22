package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Joueur;
import controller.Partie;
import controller.ThreadJoueur;

public class HeaderPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton debut;
	private ThreadJoueur[] tabThreadJou;
	private Joueur[] tabJou;
	private JLabel lblJoueur;
	private JLabel lblOrdi;
	private FenetrePlateau fenetreMere;
	private Partie p;

	// Constructeur
	public HeaderPanel(Partie p, FenetrePlateau fenetreMere, Joueur[] tabJou, ThreadJoueur[] tabThreadJou) {
		this.p = p;
		this.tabThreadJou = tabThreadJou;
		this.tabJou = tabJou;
		lblJoueur = new JLabel(); 
		lblOrdi = new JLabel(); 

		setBackground(Color.DARK_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		debut = new JButton("Commencer (Appuyez sur Entrée)");
		debut.setFocusable(false);		// Sinon le focus est sur le bouton...
		// et donc le listenner sur les flèches du clavier est HS
		debut.addActionListener(new ClicCommencerListener());
		add(debut);
		this.fenetreMere = fenetreMere;
		this.fenetreMere.setStartBut(debut);

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
			System.out.println("La partie commence. GOOO!");

			debut.setEnabled(false);
			ThreadJoueur.grille.initGrille();	// Réinitialise la grille (clean)

			if(!p.zik1.isInterrupted())
				p.zik1.close();
			if(!p.zik2.isAlive())
				p.zik2.start();
			CompteARebours cr = new CompteARebours();
			new Thread(cr).start();
		}
		
		public class CompteARebours implements Runnable {
		    public void run() {
		    	
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// REBOURS
				p.txtRebours = "3";

				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				p.txtRebours = "2";
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.txtRebours = "1";
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.txtRebours = "GO!";
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.txtRebours = "";
				
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
}
