package Boogle;
import java.util.Random;

/** Type de donn�e repr�sentant un d�*/
public class De {
	/** Face visible du d�*/
	private String lettre = "";
	/**Chaine de caract�re Contenante des 6 faces*/
	private String chaine = "";

	/**initialise les faces du d� 
	 * @param ch chaine repr�sentant les faces du d�*/
	public void initialiser(String ch){
		chaine = ch;
	}
	/**Change la face visible en majuscule
	 * @param ch chaine repr�sentant la face visible du d�*/
	public void changer(String lettre){
		this.lettre = lettre.toUpperCase();
	}
	/**M�lange le d�*/
	public void melanger(){

			Random r = new Random();
			lettre = "" + chaine.charAt(r.nextInt(6));	
	}
	/**Renvoie la face visible
	 * @return lettre: la face visible du d�*/
	public String lire(){
		return this.lettre;
	}
	
}
