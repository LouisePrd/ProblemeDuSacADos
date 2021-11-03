
public class Objet {
	public String nom;
	public double valeur;
	public double poids;
	public double rapport;

	
	public Objet(String nom, double poids, double val) {
		this.nom = nom;
		this.valeur = val;
		this.poids = poids;
		this.rapport = -(this.poids)/(this.valeur); // "-" pour gérer ordre dans gloutonne
	}
	

	
	//getter
	public String getNom() {
		return nom;
	}

	public double getValeur() {
		return valeur;
	}

	public double getPoids() {
		return poids;
	}
	
	public double getRapport() {
		return rapport;
	}
	
	//tostring d'affichage d'un objet
	public String toString() {
	 return this.nom + " " + this.poids + " " + this.valeur + "\n ";
	}

 
}