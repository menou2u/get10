package model;

import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;

public class Grille extends Observable {

	private int nombreDeClics;
	private LinkedList<Button> listeButtons;
	private LinkedList<Button> lastClicNeighbours;
	private int score;
	private boolean gameState;
	
	public Grille(){
		score = 0;
		nombreDeClics = 0;
		setLastClicNeighbours(new LinkedList<Button>());
    }
	
	public void getInvolvedButtons(Button selectedButton){
		LinkedList<Button> res = new LinkedList<Button>();
		LinkedList<Button> buttonsToCheck = new LinkedList<Button>();
		LinkedList<Button> newButtons = new LinkedList<Button>();
		buttonsToCheck.add(selectedButton);
		int j,i;
		while (buttonsToCheck.size() > 0) {
			for (int k=0; k<buttonsToCheck.size();k++) {
				i = listeButtons.indexOf(buttonsToCheck.get(k));
				if (!(res.contains(buttonsToCheck.get(k)))){
					res.add(buttonsToCheck.get(k));
					j = i - 1;
					if (j > -1 && j%5!=4 && listeButtons.get(j).getValue() == buttonsToCheck.get(k).getValue()){ //voisin de gauche
						//System.out.println("j gauche : "+j);
						newButtons.add(listeButtons.get(j));
					}
					j = i + 1;
					if (j < 25 && j%5!=0 && listeButtons.get(j).getValue() == buttonsToCheck.get(k).getValue()){ //voisin de droite
						//System.out.println("j droite : "+j);
						newButtons.add(listeButtons.get(j));
					}
					j = i - 5;
					if (j > -1 && listeButtons.get(j).getValue() == buttonsToCheck.get(k).getValue()){ //voisin du haut
						//System.out.println("j haut : "+j);
						newButtons.add(listeButtons.get(j));
					}
					j = i + 5;
					if (j < 25 && listeButtons.get(j).getValue() == buttonsToCheck.get(k).getValue()){ //voisin du bas
						//System.out.println("j bas : "+j);
						newButtons.add(listeButtons.get(j));
					}
				}
			}
			//System.out.println("taille de new avant : "+buttonsToCheck.size());
			buttonsToCheck = newButtons;
			//System.out.println("taille de new après : "+buttonsToCheck.size());
			newButtons = new LinkedList<Button>();
		}
		if (!(res.contains(selectedButton)))
			res.add(selectedButton);
		setLastClicNeighbours(res);
	}

	public void highlight(LinkedList<Button> neighbours) {
		for (int i=0; i<neighbours.size();i++){
			neighbours.get(i).setForeground(Color.red);
		}
	}

	public void setToNull(Button selectedButton) {
		if (lastClicNeighbours.size() > 1){
			for (int i=0; i<lastClicNeighbours.size(); i++){
				if (lastClicNeighbours.get(i).equals(selectedButton)){
					int oldValue = lastClicNeighbours.get(i).getValue();
					oldValue++;
					lastClicNeighbours.get(i).setValue(oldValue);
				}
				else {
					lastClicNeighbours.get(i).setValue(0);
				}
			}
		}
		unhighlight(lastClicNeighbours);
		lastClicNeighbours = null;
	}
	
	public void suppressNullButtons(){
		for (int i=0;i<5;i++){ //pour chaque colonne
			LinkedList<Button> columnButtons = new LinkedList<Button>();
			int numberOfZeros = 0;
			for (int j=0;j<listeButtons.size();j++){
				if (j%5==i){
					if (listeButtons.get(j).getValue() == 0){
						numberOfZeros++;
					}
					columnButtons.add(listeButtons.get(j));
				}
			}
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Colonne "+(i+1));
			while (numberOfZeros > 0){ 
				 for (int k=4;k>-1;k--) { //je pars de la fin de la colonne
					if (columnButtons.get(k).getValue() == 0){
						System.out.println("j'ai trouvé un zéro en "+k);
						for (int m=k;m>-1;m--){
							if (m==0){
								setRandomValue(columnButtons.get(m));
								System.out.println("j'ai créé une valeur random "+ columnButtons.get(m).getText()+ " en "+m);
							}
							else {
								System.out.println("j'ai déplacé "+columnButtons.get(m-1).getText()+" de "+(m-1)+" à "+m);
								columnButtons.get(m).setValue(columnButtons.get(m-1).getValue());
							}
						}
						numberOfZeros--;
					}
				}
			}
			System.out.println("il n'y a pas/plus de zéro dans la colonne "+i);
			int indice = 0;
			for (int j=0;j<listeButtons.size();j++){
				if (j%5==i){
					listeButtons.set(j,columnButtons.get(indice));
					setRightColor(listeButtons.get(j));
					indice++;
				}
			}
			columnButtons = new LinkedList<Button>();
		}
		score++;
		setChanged();
		notifyObservers();
		if (endCheck()){
			setChanged();
			notifyObservers();
		}
	}
	
	private boolean endCheck() {
		int prec = listeButtons.get(0).getValue();
		for (int i=1;i<listeButtons.size();i++){
			if (prec == listeButtons.get(i).getValue()){
				gameState = false;
				return gameState;
			}
			prec = listeButtons.get(i).getValue();
		}
		gameState = true;
		return gameState;
	}
	
	public boolean isBlocked(){
		return gameState;
	}

	public void setRandomValue(Button b){
		Random rand = new Random();
		int aleatoire = 1 + rand.nextInt(4);
		b.setValue(aleatoire);
		setRightColor(b);
	}
	
	public void unhighlight(LinkedList<Button> lastClicNeighbours) {
		for (int i=0; i<lastClicNeighbours.size();i++){
			lastClicNeighbours.get(i).setForeground(Color.black);
		}
	}
	
	public int getScore(){
		return score;
	}

	public void updateNumberOfClics(){
		if (nombreDeClics==0){
			nombreDeClics++;
		}
		else {
			nombreDeClics = 0;
		}
	}
	
	public void setRightColor(Button b){
		int number = b.getValue();
		switch (number) {
		case 1 :
	    	try {
	    		b.setImage(ImageIO.read(getClass().getResource("/resources/1.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/2.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 3 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/3.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 4 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/4.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 5 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/5.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 6 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/6.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 7 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/7.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 8 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/8.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 9 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/9.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 10 :
			try {
				b.setImage(ImageIO.read(getClass().getResource("/resources/10.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	public void setScore(int s){
		score = s;
	}
	
	public int getNombreDeClics(){
		return nombreDeClics;
	}
	
	public void setNombreDeClics(int v){
		nombreDeClics = v;
	}
	
	public void setListeBouttons(LinkedList<Button> list){
		listeButtons = list;
	}
	
	public LinkedList<Button> getListeBouttons(){
		return listeButtons;
	}

	public LinkedList<Button> getLastClicNeighbours() {
		return lastClicNeighbours;
	}

	public void setLastClicNeighbours(LinkedList<Button> lastClicNeighbours) {
		this.lastClicNeighbours = lastClicNeighbours;
	}
}
