package Boogle;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
/**Type de donnée représentant une Partie*/
public class Partie extends Thread{
	/**nombre de joueur(s) participant au jeu*/
	private int nbJoueurs;
	/**liste des joueurs*/
	private Joueur[] listeJoueur;
	/**tableau indiquant l'état de la case(visitée ou non)*/
	private boolean[][] caseVisitees;
	/**liste des mots supprimes*/
	private ArrayList<String> motSupprimes;
	/**liste de tout les mots possible*/
	private ArrayList<String> motATrouver;
	/**liste des doublons*/
	private ArrayList<String> doublons = new ArrayList<String>();
	/**Le plateau de jeu*/
	private Plateau p = new Plateau();
	/**Le Dico*/
	private Dictionnaire d = new Dictionnaire("ods4.txt");
	/**Initialisation d'une entrée standard*/
	private Scanner sc = new Scanner(System.in);
	
	/**constructeur d'une partie 
	 * initialise le nombre de joueur , ainsi que la liste associée*/
	public Partie(){
		System.out.println("Entrez le nombre de joueurs : ");
		int nb = sc.nextInt();
		assert(nb > 0);
		this.nbJoueurs = nb;
		listeJoueur = new Joueur[nb];
	}
	

	/**Alloue les joueurs en mémoire dans la listeJoueur
	 * */
	public void initialiserJoueurs(){
		for (int i = 0 ; i < nbJoueurs; ++i){
			System.out.println("Entrez le nom du joueur n° "+(i+1)+" :");
			sc.nextLine();
			String nom = sc.next();
			assert(nom.length()>0);
			listeJoueur[i] = new Joueur(nom);
		}
	}
	
	/**Initialise le plateau selon la volonté du joueur
	 * 2 possibilités manuelle ou automatique*/
	public void initialiserPlateau(){
		System.out.println("Voulez vous un melange automatique(1) ou manuel(2) du plateau ?");
		System.out.println("Reponse : ");
		int n = sc.nextInt();
		sc.nextLine();
		assert (n >= 1 || n <= 2);
		if(n == 1)
			p.melanger();
		else{
			System.out.println("Entrez les 16 lettres du plateau :");
			String s = sc.nextLine();
			assert(s.length() == 16);
			p.manuel(s);
		}
		//sc.close();
	}
	
	/**Lance une instance de jeu*/
	public void jouer(){
		System.out.println("Vous avez 3 min pour trouver le maximun de mots :");
		pause(1000); // on met une pause de une seconde pour laisser au joueur le temp de lire
		p.afficher();
		for(int i = 0 ; i < nbJoueurs ; ++i){
		Chronomètre chrono = new Chronomètre();
	    Timer timer = new Timer();
	    timer.schedule(chrono, 0, 1000);
		    boolean ok = true;
		    System.out.println("Joueur "+(listeJoueur[i].getNom())+", entrez "
					+ "tous les mots que vous avez trouve, entrez '*' quand "
					+ "vous avez termine");
		    System.out.print("Mot trouvé : ");
		   	while(ok && (sc.hasNext())){
		   		String mot = sc.next();
				mot = normaliser(mot);
				if (comparer(mot,"*")){
					sc.nextLine();
					break;
		    	}
				listeJoueur[i].addMot(mot);
				if(chrono.temps() >= 180000){
		   			System.out.println("Fin du temps reglementaire");
		   			pause(1500); //Afin de laisser au joueur le temps de lire
		   			break;
		   		}
				System.out.print("Mot suivant : ");
		   	}

	    timer.cancel();
	  }
		/*System.out.println("");
		System.out.println("Fin du temps reglementaire.");
		for(int i = 0 ; i < nbJoueurs ; ++i){	
			System.out.println("Joueur "+(listeJoueur[i].getNom())+", entrez "
					+ "tous les mots que vous avez trouve, entrez '*' quand "
					+ "vous avez termine");
			while(sc.hasNext()){
				String mot = sc.next();
				mot = normaliser(mot);
				if (comparer(mot,"*")){
					sc.nextLine();
					break;
				}
				listeJoueur[i].addMot(mot);
			
			}*/
			
			for(int n = 0 ; n < 60 ; n++){
				System.out.println("°");
			}
			p.afficher();
			System.out.println("");
		supprimerDoublons();
		solution();
		verification();
		AttribuerPoints();
		fin();
	}
	/**Annonce la fin de la partie, affiche les résultat correspondant aux joueurs
	 * affiche les doublons,mots erronnés, et mots qu'il fallait trouver*/
	private void fin(){
		System.out.println("La partie est terminee, voici les resultats : ");
		System.out.println("");
		for(int i = 0; i < nbJoueurs ; ++i){
			System.out.print(listeJoueur[i].toString()+" , mots :");
			for(int p = 0 ; p < listeJoueur[i].nbMots(); ++p){
				System.out .print(" - "+listeJoueur[i].getMot(p));
			}
			System.out.println("");	
		}
		System.out.println("");	
		System.out.println("Les Doublons sont : ");
		for(int i = 0 ; i < doublons.size(); ++i){
			System.out.print(" - "+doublons.get(i));
		}
		System.out.println("");
		System.out.println("Les mots érronnés sont :");
		for(int i = 0; i < motSupprimes.size(); ++i){
			System.out.print("- "+motSupprimes.get(i)+" ");
		}
		System.out.println("");
		
		System.out.println("Les mots possibles dans le plateau étaient :");

		for(int i = 0 ; i < motATrouver.size(); ++i){
				System.out.print("- "+motATrouver.get(i)+" ");
				if((i % 10 == 0) && i !=0)
					System.out.println("");
		}
		System.out.println("");
		sc.close();
	}
	/**ajoute tous les mots possible du boggle*/
	private void solution(){
		motATrouver = new ArrayList<String>();
		for(int i = 0 ; i < d.taille(); ++i){
			String s = d.getMot(i);
			if( (recherche(s)) && (s.length() >=3))
				motATrouver.add(d.getMot(i));	
		}
	}
	/**Ajoute aux joueur le nombre de point correspondant à la taille du mot qu'il a trouvé*/
	private void AttribuerPoints(){
		for(int i = 0 ; i < nbJoueurs; ++i){
			for(int j = 0; j < listeJoueur[i].nbMots(); ++j){
				switch(listeJoueur[i].getMot(j).length()){
				case 3:
				case 4: listeJoueur[i].ajouterPoints(1);
				break;
				case 5: listeJoueur[i].ajouterPoints(2);
				break;
				case 6: listeJoueur[i].ajouterPoints(3);
				break;
				case 7: listeJoueur[i].ajouterPoints(5);
				break;
				case 8:
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
				case 16: listeJoueur[i].ajouterPoints(11);
				break;
				default:;
				break;
				}
			}
		}
	}
	/***/
	private boolean contient(ArrayList<String> a, String mot){
		for (int i = 0 ; i < a.size() ; ++i){
			if (a.get(i).equals(mot))
				return true;
		}
		return false;
	}
	/***/
	private void verification(){
		motSupprimes = new ArrayList<String>();
		for (int i = 0 ; i < nbJoueurs ; ++i){
			ArrayList<String> liste = new ArrayList<String>();
			for(int j = 0 ; j < listeJoueur[i].nbMots(); ++j){
				if(!contient(motATrouver,listeJoueur[i].getMot(j))){
					motSupprimes.add(listeJoueur[i].getMot(j));
				}
				else{
					liste.add(listeJoueur[i].getMot(j));
				}
			}
			listeJoueur[i].setListe(liste);
		}
		/*for(int i = 0; i < nbJoueurs ; ++i){
			for(int j = 0 ; j < listeJoueur[i].nbMots(); ++j){
				String mot = listeJoueur[i].getMot(j);
				if(mot.length() < 3){
					motSupprimes.add(mot);
					listeJoueur[i].supprimerMot(j);
					continue;
				}	
				if((!d.estDansLeDico(mot)) || (!recherche(mot))){
					motSupprimes.add(mot);
					listeJoueur[i].supprimerMot(j);
				}	
			}
		}
		for(int i = 0; i < nbJoueurs ; ++i){
			for(int j = 0 ; j < listeJoueur[i].nbMots(); ++j){
				if(!recherche(listeJoueur[i].getMot(j))){
					motSupprimes.add(listeJoueur[i].getMot(j));
					listeJoueur[i].supprimerMot(j);
				}
			}
		}*/
		for(int i = 0; i < nbJoueurs ; ++i){
			for(int j = 0 ; j < listeJoueur[i].nbMots(); ++j){
				String mot = listeJoueur[i].getMot(j);
				for(int p = 0 ; p < listeJoueur[i].nbMots(); ++p){
					if((mot.equals(listeJoueur[i].getMot(p))) && (p != j))
						listeJoueur[i].supprimerMot(p);
				}
			}		
		}
	}
	/**Algorithme de recherche de solution pour vérification du Boggle*/
	private boolean recherche(String mot) {
		caseVisitees = new boolean[4][4];
		for(int i = 0 ; i < 4 ; ++i){
			for (int j = 0 ; j < 4 ; ++j){
				caseVisitees[i][j] = false;
			}
		}
		for(int i = 0; i < 4 ; ++i){
			for(int j = 0; j < 4; ++j){
				
				if (sousRecherche(mot,0,i,j))
					return true;
			}
		}
		return false;
	}


	/**Algorithme de recherche de solution pour vérification du Boggle
	 * @return true: */
	private boolean sousRecherche(String mot, int pos, int i, int j) {
		//assert((i >= 0) && (j >= 0) && (i < 4) && (j < 4));
		if(pos >= mot.length())
			return true;
		if((i < 0) || (j < 0) || (i >= 4) || (j >= 4))
			return false;
		if(p.getTab(i,j).lire().charAt(0) != mot.charAt(pos))
			return false;
		if(caseVisitees[i][j])
			return false;
		caseVisitees[i][j] = true;
		for(int x = -1 ; x <= 1 ; ++x){
			for(int y =-1 ; y <= 1; ++y){
				if(sousRecherche(mot,pos+1,i+x,j+y)){
					//System.out.println("Lettre "+mot.charAt(pos));
					return true;
				}
					
			}
		}
		caseVisitees[i][j] = false;
		return false;
	}



	private boolean comparer(String mot1, String mot2){
		return mot1.equals(mot2);
	}
	
	private String normaliser(String s){
		return s.toUpperCase();
	}

	private void supprimerDoublons(){
		for(int i = 0; i < nbJoueurs; ++i){
			for(int j = 0; j < listeJoueur[i].nbMots(); ++j){
				String mot = listeJoueur[i].getMot(j);
				for(int n = 0 ; n < nbJoueurs ; ++n){
					if(n == i)
						continue;
					for(int p = 0 ; p < listeJoueur[n].nbMots(); ++p){
						if(comparer(mot, listeJoueur[n].getMot(p) )){
							doublons.add(mot);
							listeJoueur[i].supprimerMot(j);
							listeJoueur[n].supprimerMot(p);
						}
							
					}
				}
			}
		}
		for(int i = 0; i < nbJoueurs; ++i){
			for(int j = 0 ; j < listeJoueur[i].nbMots(); ++j){
				for(int n = 0 ; n < listeJoueur[i].nbMots(); ++n){
					if(j == n)
						continue;
					if(listeJoueur[i].getMot(n) == listeJoueur[i].getMot(j))
						listeJoueur[i].supprimerMot(n);
				}
			}
		}
	}
	
	
	/**Permet de faire une pause dans l'enchainement du programme 
	 * @param temps: le temps en milliseconde*/
	private void pause(long temps){
		try {
			sleep(temps);
		} catch (InterruptedException e) {
			// remplir au cas où
		}
	}
}

