import java.util.ArrayList;

public class Pse {

	public static ArrayList<Objet> resoudre(ArrayList<Objet> objets, ArrayList<Objet> sac, ArrayList<Objet> best, double inf, double sup, int profondeur) {
		//méthode par récursivité
		
		//borne sup 
		if(poidsObjets(sac) > sup) 
			return best;
		
		//borne inf : meilleure valeur trouvée
		double valeur = valeurObjets(sac);
		for(int i= profondeur; i < objets.size(); i++)
			valeur += objets.get(i).getValeur();
		if(valeur < inf) 
			return best;
		
		//update de la meilleure reponse
		if(valeurObjets(sac) > valeurObjets(best)) {
			best = new ArrayList<>();
			best.addAll(sac);
		}
		
		//mettre fin lors de l'arrivée
		if(profondeur >= objets.size())
			return best;
		
		//fils sans ajout
		best = resoudre(objets, sac, best, inf, sup, profondeur+1);
		
		//fils avec ajout
		sac.add(objets.get(profondeur));
		best = resoudre(objets, sac, best, inf, sup, profondeur+1);
		sac.remove(objets.get(profondeur));
		
		return best;
	}

	//somme de la valeur des objets d'une arraylist
	public static double valeurObjets(ArrayList<Objet> objets) {
		double valeur = 0;
		for(Objet o : objets)
			valeur += o.getValeur();
		return valeur;
	}
	
	//somme du poids des objets d'une arraylist
	public static double poidsObjets(ArrayList<Objet> objets) {
		double poids = 0;
		for(Objet o : objets)
			poids += o.getPoids();
		return poids;
	}
}
