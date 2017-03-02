package Boogle;
import java.util.Random;

/**Type de donnée représentant un plateau de dé */
public class Plateau {
	/**tableau de 16 dé(4x4) */
	De tab[][] = new De[4][4];
	/**Lettres possible sur les faces du dé */
	String lettresDes[] = {"TUPSEL","MASROI","GITNEV","YUNLEG","DECPAM","KEOTUN","SERLAC","LIREWU","EAATOI","DESTON","SIHFEE","NEHRIS","TIBRAL","BOQMAJ","ZENVAD","FIXROA"};
	/**Constructeur du plateau
	 * initialise un dé dans chaque case du plateau*/
	public Plateau(){
		int n = 0;
		for (int i = 0; i < 4; ++i){
			for (int j = 0; j < 4; ++j){
				tab[i][j] = new De();
				tab[i][j].initialiser(lettresDes[n]);
				++n;
			}
		}
	}
	
	/** Renvoie le Dé à la position (i,j) du plateau
	 * 
	 * @param i position selon l'abcisse
	 * @param j position selon l'ordonnée
	 * @return De à la position (i,j)
	 */
	public De getTab(int i, int j){
		return tab[i][j];
	}
	
	/**Renvoie un dé mélangé
	 * @return res : le dé mélanger*/
	private De[] remplir(){
		De res[] = new De[16];
		int cpt = 0;
		for( int i = 0 ; i < 4 ; ++i){
			for(int j = 0 ; j <4 ; ++j){
				res[cpt] = tab[i][j];
				tab[i][j].melanger();
				cpt++;
			}
		}
		return res;
	}
	/**Melange le plateau aléatoirement ainsi que les dé*/
	public void melanger(){
		De liste[] = remplir();
		boolean place[][] = new boolean[4][4];
		for(int i = 0; i < 4 ; ++i){
			for(int j = 0; j < 0; ++j){
				place[i][j] = false;
			}
		}		
		for(int n = 0 ; n < 16 ; ++n){
			boolean fini = false;
			while(fini == false){
				Random r = new Random();
				int i = r.nextInt(4);
				int j = r.nextInt(4);
				if(!place[i][j]){
					tab[i][j] = liste[n];
					place[i][j] = true;
					fini = true;
				}
			}
		}
	}
	/**Permet une saisie manuelle du plateau
	 * @param lettre: lettres du boggle à placer dans l'ordre*/
	public void manuel(String lettre){
		int n = 0;
		for(int i = 0; i < 4; ++i){
			for(int j = 0; j < 4; ++j){
				tab[i][j].changer("" +lettre.charAt(n));
				n++;
			}
		}
	}
	/**Affiche le plateau de dé sur la sortie standard*/
	public void afficher(){
		System.out.println("Voici le plateau de lettres aleatoires :");
		System.out.println("");
		for (int i = 0; i < 4; ++i){
			System.out.print("| ");
			for (int j = 0; j < 4; ++j){
				System.out.print(tab[i][j].lire()+ " | ");
			}
			System.out.println("");
		}
	}
}
