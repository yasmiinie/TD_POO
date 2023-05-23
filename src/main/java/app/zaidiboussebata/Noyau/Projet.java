package my_desktop_planner;

import java.util.List;

public class Projet {
	public String nom;
	public String description;
    public List<SimpleTache> listeTaches ;
    public Etat etat;

 

	// constructeur == ajouter projet 
	public Projet(String nom, String description,List<SimpleTache> listeTaches,Etat etat) {
		this.description = description;
		this.nom = nom;
		this.listeTaches = listeTaches;
		this.etat = etat;
	}
	
	

}
