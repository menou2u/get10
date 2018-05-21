package controlor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Button;
import model.Grille;

public class GrilleListener implements ActionListener {

	private Grille referenceGrille;
	
	public GrilleListener(Grille g) {
		referenceGrille = g;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Button b = (Button) arg0.getSource();
		if (referenceGrille.getNombreDeClics()==0){ //si on n'a pas cliqué
			referenceGrille.updateNumberOfClics();  //on incrémente de 1
			referenceGrille.getInvolvedButtons(b);  //on récupère les voisins du bouton cliqué
			referenceGrille.highlight(referenceGrille.getLastClicNeighbours());  //on les colore en rouge
		}
		else if (referenceGrille.getNombreDeClics()==1 && referenceGrille.getLastClicNeighbours().contains(b)){ //si on a déjà cliqué et qu'on reclique dans le paquet
			referenceGrille.updateNumberOfClics();  //on repasse à 0 clics
			referenceGrille.setToNull(b);           //on met tout à 0 sauf sur celui où on a cliqué où on met +1
			referenceGrille.suppressNullButtons();
		}
		else if (referenceGrille.getNombreDeClics()==1 && !(referenceGrille.getLastClicNeighbours().contains(b))){ //si on a déjà cliqué mais qu'on clique ailleurs
			referenceGrille.updateNumberOfClics();
			referenceGrille.unhighlight(referenceGrille.getLastClicNeighbours());
			referenceGrille.setLastClicNeighbours(null);
			referenceGrille.getInvolvedButtons(b);
			referenceGrille.highlight(referenceGrille.getLastClicNeighbours());
		}
	}
}
