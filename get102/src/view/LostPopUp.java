package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controlor.MenuListener;
import model.Grille;
import model.Menu;

@SuppressWarnings("serial")
public class LostPopUp extends JFrame {

	private GridBagConstraints gbc;

	public LostPopUp(Menu menu){
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(500,200));
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		JLabel message = new JLabel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		add(message,gbc);
		message.setText("Vous avez perdu, il n'y a plus de mouvements possibles");
		add(message);
		
		JButton recommencer = new JButton();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		recommencer.setText("Recommencer");
		recommencer.addActionListener(new MenuListener(menu));
		add(recommencer,gbc);
		
		setVisible(true);
		pack();
		
	}
	
	public static void main(String[] args) {
		new LostPopUp(new Menu(new Grille()));
	}
	
}
