package Boogle;
import java.util.ArrayList;
/** Type de donnée représentant un joueur*/
public class Joueur {
	/**nom du joueur*/
	private String nom;
	/** nombre de point du joueur*/
	private int nbPoints;
	/** liste dynamique de mot que le joueur aura rentré*/
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
	 * @param n: le nombre de point à ajouter*/
	public void ajouterPoints(int n){
		this.nbPoints+=n;
	}
	
	/**Renvoie le nom du joueur
	 * @return nom: le nom du joueurt*/
	public String getNom(){
		return this.nom;
	}
	
	/**Ajoute à la liste du joueur un mot
	 * @param n: le mot à ajouter*/
	public void addMot(String mot){
		liste.add(mot);
	}
	
	/**Renvoie le mot contunu dans la liste du joueur à la position n
	 * @param n: l'indice ou position demandée*/
	public String getMot(int n){
		assert(n >= 0);
		return liste.get(n);
	}
	/**Supprime le mot contunu dans la liste du joueur à la position n
	 * @param n: l'indice ou position demandée*/
	public void supprimerMot(int n){
		assert(n >= 0);
		this.liste.remove(n);
	}
	
	/**Renvoie le nombre de mots contenus dans la liste du joueur
	 * @return : la taille de la liste*/
	public int nbMots(){
		return this.liste.size();
	}
	/**Initialise la liste du joueur à partir d'une autre
	 * @param a: l'autre liste*/
	public void setListe(ArrayList<String> a){
		this.liste = a;
	}
	/**Renvoie la chaine de caractère correspondante au joueur
	 * @return : le joueur 
	 * */
	public String toString(){
		return (nom +" : "+ Integer.toString(nbPoints));
	}
}
