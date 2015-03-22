package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ThreadMusic;

public class FenetreAccueil extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	
	JFrame fenetrePlateau;
	
    public FenetreAccueil(JFrame fenetrePlateau, ThreadMusic mainZik) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    	setTitle("Tron du ghetto - LePolotech/GodGiven/Brillon");
        setSize(817,596);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JLabel lblOver = new JLabel();
		lblOver.setIcon (new ImageIcon ("images/accueil.png"));
		this.add(lblOver);
		
		this.fenetrePlateau = fenetrePlateau;
		
		setVisible(true);
    }

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			this.setVisible(false);
			fenetrePlateau.setVisible(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
