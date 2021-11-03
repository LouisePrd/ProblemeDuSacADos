import java.io.IOException;
import java.util.ArrayList;


public class Appli {
	public static void main(String[] args) throws IOException {
	
		//sac de base avec tous les objets et le poids max
		SacADos sac = new SacADos("C:\\Users\\Louise\\Desktop\\objets.txt", 3); 		
		
		//Methode gloutonne
		  System.out.println("Avec la méthode gloutonne");
		  sac.methodeGloutonne(sac.getSac1(), sac.getSac2(), sac);
		  System.out.println(sac.getSac2());
		 


		//Methode dynamique
		/*
		 * System.out.println("Avec la méthode dynamique"); sac.dynamique(sac);
		 * System.out.println(sac.getSac2());
		 */


		//Methode PSE
		/*
		 * sac.methodeGloutonne(sac.getSac1(), sac.getSac2(), sac); SacADos resultat =
		 * new SacADos("C:\\Users\\Louise\\Desktop\\objets.txt", 3); ArrayList<Objet>
		 * best = new ArrayList<>(); best = Pse.resoudre(resultat.getSac1(),
		 * resultat.getSac2(), best, sac.getValeur(), resultat.getPoidsMax(), 0);
		 * System.out.println(best);
		 */
		 
		

		
		
	}
}
