package model;

import java.awt.CardLayout;
import java.util.List;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends Observable {

	private Grille grille;
	private JLabel scoreLabel;
	private Menu menu;
	private List<Button> listeButtons;
	private CardLayout cardLayout;
	private JPanel changingPanel;
	private JPanel plateau;
	private final static String SCORE = "Score : ";
	
	public Game() {
		cardLayout = new CardLayout();
		plateau = new JPanel();
		plateau.setLayout(cardLayout);
		changingPanel = new JPanel();
		
		grille = new Grille();
		listeButtons = grille.getListeBouttons();
		
		menu = new Menu(grille);
		
		scoreLabel = new JLabel();
		scoreLabel.setText(SCORE + grille.getNombreDeClics());
	}

	//Getters and setters
	public Grille getGrille() {
		return grille;
	}

	public JLabel getScoreLabel() {
		return scoreLabel;
	}

	public Menu getMenu() {
		return menu;
	}

	public List<Button> getListeButtons() {
		return listeButtons;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public JPanel getChangingPanel() {
		return changingPanel;
	}

	public JPanel getPlateau() {
		return plateau;
	}

	public static String getScore() {
		return SCORE;
	}
	
	
	
}
