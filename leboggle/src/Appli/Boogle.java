package Appli;

import Boogle.Partie;

public class Boogle {
	public static void main(String[] args){
		Partie p = new Partie(); //Création d'une partie
		p.initialiserJoueurs();
		p.initialiserPlateau();
		p.jouer();
		
		
	}
}