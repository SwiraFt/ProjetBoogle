package Boogle;
import java.util.Random;

/** Type de donnée représentant un dé*/
public class De {
	/** Face visible du dé*/
	private String lettre = "";
	/**Chaine de caractère Contenante des 6 faces*/
	private String chaine = "";

	/**initialise les faces du dé 
	 * @param ch chaine représentant les faces du dé*/
	public void initialiser(String ch){
		chaine = ch;
	}
	/**Change la face visible en majuscule
	 * @param ch chaine représentant la face visible du dé*/
	public void changer(String lettre){
		this.lettre = lettre.toUpperCase();
	}
	/**Mélange le dé*/
	public void melanger(){

			Random r = new Random();
			lettre = "" + chaine.charAt(r.nextInt(6));	
	}
	/**Renvoie la face visible
	 * @return lettre: la face visible du dé*/
	public String lire(){
		return this.lettre;
	}
	
}
