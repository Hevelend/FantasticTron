package fantastic.tron.screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fantastic.tron.thread.Music;

public class WelcomeScreen extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	
	JFrame gameWindow;
	
    public WelcomeScreen(JFrame _gameWindow, Music mainZik) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    	setTitle("Fantastic Tron");
        setSize(817,596);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JLabel lblOver = new JLabel();
		lblOver.setIcon (new ImageIcon ("images/accueil.png"));
		this.add(lblOver);
		
		this.gameWindow = _gameWindow;
		
		setVisible(true);
    }

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			this.setVisible(false);
			gameWindow.setVisible(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
