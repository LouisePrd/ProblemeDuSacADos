import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SacADos {

	private ArrayList<Objet> sacDeBase = new ArrayList<Objet>();
	private ArrayList<Objet> sacFinal = new ArrayList<Objet>();
	private double poidsMax;
	private double poids;
	private double valeur;
	
	//constructeur sac vide
	public SacADos () { 
		this.poidsMax = 0;
	}
	
	//constructeur qui va initialiser la première liste du sac à dos 
	//rempli de tous les objets
	public SacADos(String chemin, double poidsmaximal) throws FileNotFoundException {   
		this.poidsMax = poidsmaximal;
		File file = new File(chemin);
		Scanner scan = new Scanner(file);
		String nom;
		double valeur;
		double poids;
		String ligne;
		while(scan.hasNext()) {
			ligne = scan.nextLine();
			String[] tabObj = ligne.split(" ; ");
			nom = tabObj[0];
			poids = Double.parseDouble(tabObj[1]);
			valeur = Double.parseDouble(tabObj[2]);
			this.getSac1().add(new Objet(nom, poids, valeur));
			for (int i = 0; i < this.getSac1().size(); ++i)  {
                valeur = valeur + this.valeur;
            }
		}
		
	}

	
	/**
	 * Méthode gloutonne : sort en fonction du rapport
	 * ajoute objets de la liste 1 dans la liste 2 finale tant que poids max pas dépassé
	 * @param sacDeBase liste 1
	 * @param sacFinal liste 2
	 * @param sac à dos
	 */
	public void methodeGloutonne(List<Objet> sacDeBase, List<Objet> sacFinal, SacADos sac) {
		
		//tri de la liste en fonction du rapport
		Collections.sort(sacDeBase, new Comparator<Objet>() 
		{
		     @Override
		     public int compare(Objet obj1, Objet obj2) {
		       return Double.valueOf(obj2.getRapport()).compareTo(obj1.getRapport());
		      }
		 });

		double poidsDansSac = 0;
		double valDansSac = 0;
            for (int i = 0; i < sacDeBase.size(); ++i)  {
                if (sacDeBase.get(i).poids <= this.poidsMax && (poidsDansSac+sacDeBase.get(i).poids)<=this.poidsMax) {
                    sacFinal.add(sacDeBase.get(i));
                    poidsDansSac = sacDeBase.get(i).poids + poidsDansSac;
                    valDansSac = sacDeBase.get(i).valeur + valDansSac;
                }
            }
            sac.poids = poidsDansSac;
            sac.valeur = valDansSac;
	}
	
	
	//ajoute objets méthode dynamique
	public void addSacDyn(Objet objet) { 
	        if(objet.getPoids() + this.poids <= this.poidsMax) {
	            this.sacFinal.add(objet);
	            this.poids += objet.getPoids();
	        }
	}
	
	
	/**
	 * Méthode dynamique qui va créer la matrice puis récupérer les objets en fonction du meilleur résultat possible
	 * @param sac
	 */
	public void dynamique (SacADos sac) {
        double[][] tableau = new double[sac.sacDeBase.size()][(int)(sac.poidsMax*100 + 1)]; //avec +1 pour pas dépasser taille tab
        //boucle pour remplir la 1e ligne
        for (int j = 0; j <= sac.poidsMax*100; j++) {
        		if (sac.sacDeBase.get(0).getPoids()*100 > j) 
        			tableau[0][j] = 0;
        		else 
        			tableau[0][j] = sac.sacDeBase.get(0).getValeur();
        }
        //boucle pour les autres
        for (int i = 1; i < sac.sacDeBase.size(); i++) {
            for (int j = 0; j <= poidsMax*100; j++) {
                if (sac.sacDeBase.get(i).getPoids()*100 > j)
                    tableau [i][j] = tableau [i - 1][j];
                else
                    tableau [i][j] = Math.max(tableau[i-1][j], tableau[i-1][(int) (j-sac.sacDeBase.get(i).getPoids()*100)] + sac.sacDeBase.get(i).getValeur());
            }
        }        

        //On récupère dans la dernière ligne le poids minimal pour faire le bénéfice optimal 
        int i = sac.sacDeBase.size() - 1;
        int j = (int) sac.poidsMax*100;
        while(j>0&&tableau[i][j] == tableau[i][j-1])
        	--j;
        
        //On récupère les objets
        while(j > 0) {
        	while(i>0 && tableau[i][j] == tableau[i-1][j])
        		--i;
        	j = j - (int)(sac.getSac1().get(i).poids*100);
        	if(j>=0) {
        		sac.addSacDyn(sac.getSac1().get(i));
        	--i;
        	}
       }
        
	}      
        
        
	//toString d'affichage du sac
		public String toString() { 
			return "" + this.getSac1();
		}

	//getter
		public ArrayList<Objet> getSac1() {
			return sacDeBase;
		}
		public ArrayList<Objet> getSac2() {
			return sacFinal;
		}
		public double getPoidsMax() {
			return poidsMax;
		}
		public double getPoids() {
			return poids;
		}
		public double getValeur() {
			return valeur;
		}

		
}
