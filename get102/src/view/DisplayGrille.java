package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

import controlor.GrilleListener;
import model.Button;
import model.Grille;

public class DisplayGrille extends JPanel {

	private static final long serialVersionUID = -5434161891575064077L;
	private Grille referenceGrille;
	private LinkedList<Button> listeButtons;
	
	public DisplayGrille(Grille g){
		listeButtons = new LinkedList<Button>();
		referenceGrille = g;
		setLayout(new GridLayout(5,5));
		Random rand = new Random();
	    for (int i = 0 ; i<25 ; i++){
	       	int aleatoire = 1 + rand.nextInt(4);
	       	Button b = new Button(aleatoire);
	       	b.addActionListener(new GrilleListener(referenceGrille));
	       	listeButtons.add(b);
	        add(b);
	    }
	    g.setListeBouttons(listeButtons);
	    setPreferredSize(new Dimension(350,400));
	}
	
	public int getNombreDeClics(){
		return referenceGrille.getNombreDeClics();
	}
	
	public void setGrille(DisplayGrille dg){
		referenceGrille = dg.getGrille();
	}
	
	public Grille getGrille(){
		return referenceGrille;
	}
}
