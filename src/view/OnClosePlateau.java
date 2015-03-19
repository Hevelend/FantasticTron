package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class OnClosePlateau implements WindowListener {
	// La fenêtrePlateau est nécessaire pour pouvoir afficher la popup
	// de fermeture DANS la fenêtrePlateau
	private FenetrePlateau fp;
	JFrame confirm;

	public OnClosePlateau(FenetrePlateau fp) {
		this.fp = fp;
	}

	@Override
	public void windowActivated(WindowEvent arg0) { }

	@Override
	public void windowClosed(WindowEvent arg0) { }

	@Override
	// Quand on ferme la fenêtre du jeu
	public void windowClosing(WindowEvent e) {
		confirm = new JFrame();
		JPanel pan = new JPanel();
		JLabel msg = new JLabel("Voulez-vous vraiment quitter ?");
		JButton quit = new JButton("Oui");
		JButton cancel = new JButton("Non");

		// Quitte le programe
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// Annulle la fermeture en fermant la popup
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm.dispose();
			}
		});
		pan.add(quit);
		pan.add(cancel);

		confirm.setMinimumSize(new Dimension(300,  100));
		confirm.setLocationRelativeTo(fp);
		confirm.add(msg, BorderLayout.NORTH);
		confirm.add(pan, BorderLayout.SOUTH);
		confirm.setVisible(true);
	}

	@Override
	public void windowDeactivated(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowOpened(WindowEvent e) { }

}
