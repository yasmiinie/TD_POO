package app.zaidiboussebata.Noyau;
import java.io.Serializable; 
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;
import java.util.Scanner;


 abstract class Tache implements Serializable {
	 public String nom;
	 public Duration duree;
     public LocalDate deadline;
	 public Priorite priorite;
	 public Categorie categorie;
	 public Etat etat;
     public String type;


  // Getter and setter for the duree field
     public Duration getDuree() {
         return duree;
     }
     
     public void setDuree(Duration duree) {
         this.duree = duree;
     }



//-----------------------------------| afficherTaches |------------------------------------// 
     /**
      * permet d'ordonne la list des taches
      * @param tacheList
      */
     public static void orderTacheList(List<SimpleTache> tacheList) {
         // Sort the list based on the deadline
         Collections.sort(tacheList, new Comparator<Tache>() {
             @Override
             public int compare(Tache t1, Tache t2) {
                 return t1.deadline.compareTo(t2.deadline);
             }
         });
     }
     
//-----------------------------------| afficherTaches |------------------------------------// 
     /**
      * permet d'afficher la list des taches
      * @param tacheList
      */
     static void afficherTaches(List<SimpleTache> tacheList) {
    	    System.out.println("Liste des tâches :");
    	    if (tacheList.size() != 0) {
    	        for (int i = 0; i < tacheList.size(); i++) {
    	            Tache tache = tacheList.get(i);
    	            System.out.println("Tâche " + (i + 1) + ": |" + tache.nom + "| , Durée: |" + tache.duree +
    	                    "| , Priorité: |" + tache.priorite +"| ,Deadline: |"+tache.deadline+ "| , Catégorie: |" + tache.categorie +
    	                    "| , État: |" + tache.etat + "| , Type: |" + tache.type);
    	        }
    	    } else {
    	        System.out.println("Votre liste des tâches est vide.");
    	    }

    	}
//-----------------------------------| CreerTache |------------------------------------//     
     /**
      * permet de creer une tache (ajouter la tache dans la list des taches)
      * @param tacheList
      * @param nom
      * @param dureeMinutes
      * @param deadline
      * @param priorite
      * @param categorie
      * @param etat
      */
     static void CreerTache(List<SimpleTache> tacheList, String nom, long dureeMinutes,LocalDate deadline,Priorite priorite,Categorie categorie,Etat etat ) {
    	 SimpleTache simpleTache = new SimpleTache();
         simpleTache.nom = nom;
         simpleTache.duree = Duration.ofMinutes(dureeMinutes);
         simpleTache.deadline = deadline;

         simpleTache.priorite = priorite;
         simpleTache.categorie = categorie;
         simpleTache.etat = etat;
         simpleTache.type = "Simple";

         // Add the SimpleTache object to the list
         tacheList.add(simpleTache);
    }
     

//-----------------------------------| supprimerTache |------------------------------------//     
     /**
      * permet de supprimer une tache par son nom a partir de la lists des taches
      * @param fichier
      * @param tacheList
      * @param nomTache
      */
     static void supprimerTache(String fichier,List<SimpleTache> tacheList, String nomTache) {
    	    Tache tacheToDelete = null;
    	    for (Tache tache : tacheList) {
    	        if (tache.nom.equalsIgnoreCase(nomTache)) {
    	            tacheToDelete = tache;
    	            break;
    	        }
    	    }
    	    if (tacheToDelete != null) {
    	        tacheList.remove(tacheToDelete);
    	        Utilisateur.sauvegarderObjetFichier(fichier, tacheList);
    	        System.out.println("La tâche a été supprimée avec succès.");
    	    } else {
    	        System.out.println("La tâche spécifiée n'a pas été trouvée.");
    	    }
    	}
//-----------------------------------| modifierTache |------------------------------------//     
     
     /**
      * permet de modufier une tache par son nom a partir de la lists des taches
      * @param fichier
      * @param tacheList
      * @param nomTache
      */
     static void modifierTache(String fichier,List<SimpleTache> tacheList, String nomTache) {
    	 Scanner scanner = new Scanner(System.in);

        Tache tacheToModify = null;
        for (Tache tache : tacheList) {
            if (tache.nom.equalsIgnoreCase(nomTache)) {
                tacheToModify = tache;
                break;
            }
        }
        if (tacheToModify != null) {
            // Prompt the user for updated task information
            System.out.print("Nouveau nom de la tâche : ");
            String nouveauNom = scanner.nextLine();
            System.out.print("Nouvelle durée de la tâche en minutes : ");
            long nouvelleDureeMinutes = scanner.nextLong();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Nouvelle date (yyyy-MM-dd): ");
            String userInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Priorite nouvellePriorite = Priorite.HEIGHT;

            // Update the task
            tacheToModify.nom = nouveauNom;
            tacheToModify.duree = Duration.ofMinutes(nouvelleDureeMinutes);
            tacheToModify.deadline =LocalDate.parse(userInput, formatter);
            tacheToModify.priorite = nouvellePriorite;
            

            Utilisateur.sauvegarderObjetFichier(fichier, tacheList);
            System.out.println("La tâche a été modifiée avec succès.");
        } else {
            System.out.println("La tâche spécifiée n'a pas été trouvée.");
        }
    }

     public static void permuterTachesMemeDeadline(List<SimpleTache> tacheList) {
         Random random = new Random();
         int i = 0;
         
         while (i < tacheList.size() - 1) {
             SimpleTache currentTache = tacheList.get(i);
             int j = i + 1;
             
             while (j < tacheList.size() && currentTache.deadline.isEqual(tacheList.get(j).deadline)) {
                 // Permuter la tâche courante avec une tâche aléatoire ayant la même deadline
                 int randomIndex = random.nextInt(j - i) + i;
                 SimpleTache randomTache = tacheList.get(randomIndex);
                 tacheList.set(randomIndex, currentTache);
                 tacheList.set(i, randomTache);
                 
                 j++;
             }
             
             i = j;
         }
     }
    //evaluer
	
}// fin de class
