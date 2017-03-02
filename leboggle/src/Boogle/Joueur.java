package Boogle;
import java.util.ArrayList;
/** Type de donn�e repr�sentant un joueur*/
public class Joueur {
	/**nom du joueur*/
	private String nom;
	/** nombre de point du joueur*/
	private int nbPoints;
	/** liste dynamique de mot que le joueur aura rentr�*/
	private ArrayList<String> liste = new ArrayList<String>();
	
	/**constructeur d'un joueur
	 * @param nom : nom du joueur */
	public Joueur(String nom){
		assert(nom.length() > 0);
		this.nom = nom;
		nbPoints = 0;
	}
	/**Renvoie le nombre de point du joueur
	 * @return nbPoints: le nombre de point*/
	public int getPoints(){
		return this.nbPoints;
	}
	/**Ajoute au joueur n points
	 * @param n: le nombre de point � ajouter*/
	public void ajouterPoints(int n){
		this.nbPoints+=n;
	}
	
	/**Renvoie le nom du joueur
	 * @return nom: le nom du joueurt*/
	public String getNom(){
		return this.nom;
	}
	
	/**Ajoute � la liste du joueur un mot
	 * @param n: le mot � ajouter*/
	public void addMot(String mot){
		liste.add(mot);
	}
	
	/**Renvoie le mot contunu dans la liste du joueur � la position n
	 * @param n: l'indice ou position demand�e*/
	public String getMot(int n){
		assert(n >= 0);
		return liste.get(n);
	}
	/**Supprime le mot contunu dans la liste du joueur � la position n
	 * @param n: l'indice ou position demand�e*/
	public void supprimerMot(int n){
		assert(n >= 0);
		this.liste.remove(n);
	}
	
	/**Renvoie le nombre de mots contenus dans la liste du joueur
	 * @return : la taille de la liste*/
	public int nbMots(){
		return this.liste.size();
	}
	/**Initialise la liste du joueur � partir d'une autre
	 * @param a: l'autre liste*/
	public void setListe(ArrayList<String> a){
		this.liste = a;
	}
	/**Renvoie la chaine de caract�re correspondante au joueur
	 * @return : le joueur 
	 * */
	public String toString(){
		return (nom +" : "+ Integer.toString(nbPoints));
	}
}
