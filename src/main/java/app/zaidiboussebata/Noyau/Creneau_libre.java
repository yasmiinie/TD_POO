package my_desktop_planner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;


public class Creneau_libre implements Serializable{
	private LocalTime debut;
	private LocalTime fin;
    private LocalDate date;
	public static Duration  dureeMin = Duration.ofMinutes(30);

	
//------------------------------| Constructeur |------------------------------------------------//	
		
		public Creneau_libre(LocalTime debut,LocalTime fin,LocalDate date) {
			// TODO Auto-generated constructor stub
			this.debut = debut;
			this.fin = fin;
			this.date = date;

		}	
//------------------------------| Getters setters |------------------------------------------------//	
		public LocalDate getDate() {return date;}
		public void getDate(LocalDate date) {		this.date = date;	}
		public LocalTime getDebut() {		return debut;	}
		public void getDebut(LocalTime debut) {		this.debut = debut;	}
		public LocalTime getFin() {		return fin;	}
		public void getFin(LocalTime fin) {		this.fin = fin;	}


//------------------------------| calculeDuree  |------------------------------------------------//			
	
	/**
	 * permet de calculer la duree entre deux instance (debut et fin)
	 * @param debut
	 * @param fin
	 * @return Duree 
	 */
	 public static Duration calculeDuree(LocalTime debut, LocalTime fin) {
	        return Duration.between(debut, fin);
	 }

//----------------------------------------| createrCreneauLibre |-----------------------------------------------------//
	 /**
	  * permet de creer un creneau libre
	  * @param scanner
	  * @param CreneauLibreList
	  */
	 void createrCreneauLibre(String fichier,String debutString,String finString,LocalDate date, List<Creneau_libre> CreneauLibreList) {

	     LocalTime debut = LocalTime.parse(debutString, DateTimeFormatter.ofPattern("HH:mm"));
	     LocalTime fin = LocalTime.parse(finString, DateTimeFormatter.ofPattern("HH:mm"));
	     Duration duree = Creneau_libre.calculeDuree(debut, fin);

	     if (duree.compareTo(Creneau_libre.dureeMin) < 0) {
	         System.out.println("La durée du créneau libre est inférieure à la durée minimale requise.");
	         return;
	     }
	     
	     Creneau_libre newCreneauLibre = new Creneau_libre(debut, fin,date);
	     CreneauLibreList.add(newCreneauLibre);
	     Utilisateur.sauvegarderObjetFichier(fichier, CreneauLibreList);
	 }

//-------------------------------------| afficherCreneauxLibres |-----------------------------------//
	 /**
	  * permet d'afficher la liste des creneaux libres
	  * @param CreneauLibreList
	  */
	 void afficherCreneauxLibres(List<Creneau_libre> CreneauLibreList) {
	     
	     if(CreneauLibreList.size() != 0) {
		   System.out.println("Liste des créneaux libres :");
	     for (int i = 0; i < CreneauLibreList.size(); i++) {
	         Creneau_libre creneauLibre = CreneauLibreList.get(i);
	         System.out.println("Créneau libre |" + (i + 1) + "| " +creneauLibre.date+" : "+ creneauLibre.debut + " -> " + creneauLibre.fin );
	     }
	     }else {
	         System.out.println("Votre liste des créneaux librses est vide ");
	     }
	 }

//----------------------------------------| supprimerCreneauLibre |-----------------------------------------------------//

	 /**
	  * permert de supprimer un creneau libre dans le fichier des creneau libre
	  * @param scanner
	  * @param CreneauLibreList
	  */
	 void supprimerCreneauLibre(String fichier,int index, List<Creneau_libre> CreneauLibreList) {
	     // Lire la ligne vide après la lecture de l'indice

	     if (index >= 0 && index < CreneauLibreList.size()) {
	         Creneau_libre creneauLibre = CreneauLibreList.get(index);
	         CreneauLibreList.remove(index);
	         Utilisateur.sauvegarderObjetFichier(fichier, CreneauLibreList);
	         System.out.println("Créneau libre supprimé avec succès : " + creneauLibre);
	     } else {
	         System.out.println("Indice invalide. Suppression du créneau libre échouée.");
	     }
	 }


}// fin de class
