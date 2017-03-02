package Boogle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/** Type de donnée représentant un dictionnaire*/
public class Dictionnaire {
	/** liste dynamique de mots contenus dans le dico*/
	ArrayList<String> dictionnaire = new ArrayList<String>();
	
	/**constructeur d'un Dictionnaire
	 * @param nomFichier : nom du fichier contenant les mots du dico */
	public Dictionnaire(String nomFichier){
		System.out.println("Chargement du dictionnaire ... Veuillez Patienter.");
		try{
			Scanner fich = new Scanner(new FileInputStream(nomFichier));	
			while (fich.hasNextLine()){	
					Scanner ligne = new Scanner(fich.nextLine());
					String s = new String(ligne.next());
					this.dictionnaire.add(s);
					ligne.close();
			}
			fich.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Le fichier est introuvable");
			System.exit(-1);
		}
	}
	
	/**Renvoie vrai si deux chaines de caractères sont égales faux sinon
	 * @return : true ou false*/	
	public boolean comparer(String s1, String s2){
		return s1.equals(s2);
	}
	
	/**Renvoie vrai si le mot est contenu dans le dico, faux sinon
	 * @return : true ou false*/
	public boolean estDansLeDico(String mot){
		return dictionnaire.contains(mot);
	}
	/**Renvoie la taille du dico
	 * @return : la taille du dico*/
	public int taille(){
		return dictionnaire.size();
	}
	/**Renvoie le mot à l'indice n de la liste
	 * @param n: l'indice
	 * @return : le mot à l'indice n */
	public String getMot(int n){
		return dictionnaire.get(n);
	}
}
	