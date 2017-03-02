package Boogle;
import java.util.TimerTask;

public class Chronomètre extends TimerTask {
	/** Temps initial*/
	private long tempsStart = System.currentTimeMillis();
	public void run(){
		
	 try{
		  Thread.sleep(1000);
	  }
	  catch(InterruptedException exception){
		  
	  }
	}
/** Renvoie le temps d'execution du timer
 * @ return: le temps d'execution*/
 public long temps(){
	  return System.currentTimeMillis()-tempsStart;
 }
}