package view;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Button;
import model.Grille;
import model.Menu;

/**
 *
 * @author menou2u
 */
public class GameFrame extends JFrame implements Observer {
    
	private static final long serialVersionUID = 1L;
	private Grille grille;
	private DisplayGrille affichageGrille;
	private JLabel score;
	private Menu menu;
	private DisplayMenu affichageMenu;
	private LinkedList<Button> listeButtons;
	private CardLayout cl;
	private JPanel changingPanel;
	private JPanel plateau;
	private String[] listContent = {"Reset, Démarrage"};
	private GridBagConstraints gbc;
	private final static String SCORE = "Score : ";
	
	public GameFrame(){
		//Parameters of the JFrame
		super("get10");
		setPreferredSize(new Dimension(800,600));
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		//Setting of the cardlayout
		cl = new CardLayout();
		plateau = new JPanel();
		plateau.setLayout(cl);
		changingPanel = new JPanel();
		
		//Settings of the board position
		this.grille = new Grille();
		listeButtons = grille.getListeBouttons();
		grille.addObserver(this);
		affichageGrille = new DisplayGrille(grille);
		affichageGrille.setPreferredSize(new Dimension(500,500));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		changingPanel.add(affichageGrille,gbc);
		plateau.add(affichageGrille,listContent[listContent.length - 1]);
		
		//Settings of the menu
		this.menu = new Menu(grille);
		menu.addObserver(this);
		affichageMenu = new DisplayMenu(menu);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		add(affichageMenu,gbc);
		
		//Settings of the clic count
		score = new JLabel();
		score.setText(SCORE + grille.getNombreDeClics());
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		add(score,gbc);
		
		changingPanel.add(plateau);
		add(changingPanel);
		
		//Handles final display
		cl.show(plateau, listContent[listContent.length -1]);
		pack();
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Grille){
			score.setText(SCORE + grille.getScore());
			if (((Grille) o).isBlocked()){
				new LostPopUp(menu);
			}
		}
		if (o instanceof Menu){
			score.setText(SCORE + grille.getScore());
			affichageGrille = new DisplayGrille(grille);
			JPanel newGrille = affichageGrille;
			plateau.add(newGrille, listContent[0]);
			cl.show(plateau, listContent[0]);
		}
	}
	
	//Getters and setters
	private void setGrille(Grille g) {
		grille = g;
	}
	
	public DisplayGrille getAffichageGrille(){
		return affichageGrille;
	}
	
	public void setAffichageGrille(Grille g){
		affichageGrille = new DisplayGrille(g);
		setGrille(g);
	}

	public LinkedList<Button> getListeButtons() {
		return listeButtons;
	}

	public void setListeButtons(LinkedList<Button> linkedList) {
		this.listeButtons = linkedList;
	}
}
