package model;

import java.util.Observable;

public class Menu extends Observable {
	
	private Grille referenceGrille;
	
	public Menu(Grille grille){
		referenceGrille = grille;
	}

	public void resetGrille() {
		int newScore = 0;
		referenceGrille.setScore(newScore);
		setChanged();
		notifyObservers();
	}
}
