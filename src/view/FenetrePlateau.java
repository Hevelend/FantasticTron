package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.*;

public class FenetrePlateau extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private Joueur humain;
	private JButton start;

	public FenetrePlateau(Joueur humain) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
		System.out.println("Cr�ation de la fen�tre principale");
		this.humain = humain;
		setTitle("Tron Game");
		setSize(817, 596);
		setLocationRelativeTo(null);		// Au centre de l'�cran
		/* D�commenter pour ajouter la popup de fermeture de fen�tre
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new OnClosePlateau(this));*/
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new ActionUser());
	}

	class ActionUser implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) { }

		@Override
		public void keyPressed(KeyEvent e) {
			if(!humain.isDead()) {
				if(e.getKeyCode() == KeyEvent.VK_UP){
					if(humain.getDirection() != 'b')	// �vite de se retourner
						humain.setDirection('h');
				}

				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					if(humain.getDirection() != 'h')	// �vite de se retourner
						humain.setDirection('b');	
				}

				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					if(humain.getDirection() != 'd')	// �vite de se retourner
						humain.setDirection('g');
				}

				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					if(humain.getDirection() != 'g')	// �vite de se retourner
						humain.setDirection('d');
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
			start.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	// Le listener pour la touche entr�e active le boutton COMMENCER
	public void setStartBut(JButton jb) {
		start = jb;
	}

}