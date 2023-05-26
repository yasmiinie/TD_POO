package app.zaidiboussebata.Noyau;

import app.zaidiboussebata.Control.LogInController;
import app.zaidiboussebata.Noyau.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static app.zaidiboussebata.Control.LogInController.pseudo;
import static app.zaidiboussebata.Control.LogInController.recupererObjetFichier;
import static app.zaidiboussebata.Noyau.Utilisateur.sauvegarderObjetFichier;


public abstract class Tache implements Serializable {
	 public String nom;
	 public Duration duree;
     public LocalDate deadline;
	 public Priorite priorite;
	 public Categorie categorie;
	 public Etat etat;
     public String type;

//----------------------------------------------------------------------------------------

    /**
     * permet de permuter entre deux taches de meme ddl pour l'utiliser dans la replanification
     * (si l'utilisateur ne valide pas le planning )
     * @param tacheList
     */
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
     public static void afficherTaches(List<SimpleTache> tacheList) {
    	    System.out.println("Liste des tâches :");
    	    if (tacheList.size() != 0) {
    	        for (int i = 0; i < tacheList.size(); i++) {
    	            Tache tache = tacheList.get(i);
    	            System.out.println("Tâche " + (i + 1) + ": " + tache.nom + ", Durée: " + tache.duree +
    	                    ", Priorité: " + tache.priorite +",Deadline: "+tache.deadline+ ", Catégorie: " + tache.categorie +
    	                    ", État: " + tache.etat + ", Type: " + tache.type);
    	        }
    	    } else {
    	        System.out.println("Votre liste des tâches est vide.");
    	    }

    	}
//-----------------------------------| CreerTache |------------------------------------//     
     /**
      * permet de creer une tache (ajouter la tache dans la list des taches)
      *
      * @param nom
      * @param dureeMinutes
      * @param deadline
      * @param priorite
      * @param categorie
      * @param etat
      */
     public void CreerTache( String nom, long dureeMinutes,LocalDate deadline,Priorite priorite,Categorie categorie,Etat etat, String type ) {

         this.nom = nom;
         this.duree = Duration.ofMinutes(dureeMinutes);
         this.deadline = deadline;
         this.priorite = priorite;
         this.categorie = categorie;
         this.etat = etat;
         this.type = type;

         // Add the SimpleTache object to the list
        // tacheList.add(simpleTache);
    }

//-----------------------------------| supprimerTache |------------------------------------//     
     /**
      * permet de supprimer une tache dans le planning et dans les taches par son nom a partir de la lists des taches

      * @param tacheList
      * @param nomTache
      */
     public Boolean supprimerTache(List<SimpleTache> tacheList, String nomTache) {
         Tache tacheToDelete = null;
         Planning ToDelete = null;

         // on cherche la tache a supprimer
         for (Tache tache : tacheList) {
             if (tache.nom.equalsIgnoreCase(nomTache)) {
                 tacheToDelete = tache;
                 break;
             }
         }

         if (tacheToDelete != null) {
             //si elle existe dans le fichier des taches

             // Si elle existe je la cherche dans le dernier planning
             Planning planning = new Planning();
             List<HistoriquePlanning> historiquePlanList = recupererObjetFichier(pseudo + "_historiquePlanning.ser");
             HistoriquePlanning history = new HistoriquePlanning();

             // pour recuperer le dernier planning
             HistoriquePlanning historiquePlanning;
             if ((historiquePlanList.size()) > 0) {
                 // pour recuperer le dernier planning s'il existe
                 historiquePlanning = history.recupererHistorique(historiquePlanList, historiquePlanList.size() - 1);

                 for (Planning plan : historiquePlanning.listPlanning) {
                     if (plan.tache.nom.equalsIgnoreCase(nomTache)) {
                         ToDelete = plan;
                         break;
                     }
                 }

                 tacheList.remove(tacheToDelete); // on la supprime de la liste des taches
                 sauvegarderObjetFichier(pseudo + "_taches.ser", tacheList);

                 if (ToDelete != null) {
                     if(historiquePlanning.listPlanning.size() == 1){
                         // il ya un seul element dedans
                         historiquePlanList.remove(historiquePlanning);
                     }
                     historiquePlanning.listPlanning.remove(ToDelete); // on la supprime de la liste des taches
                     sauvegarderObjetFichier(pseudo + "_historiquePlanning.ser", historiquePlanList);


                 }
             } else {

                 tacheList.remove(tacheToDelete); // on la supprime de la liste des taches
                 Utilisateur.sauvegarderObjetFichier(pseudo + "_taches.ser", tacheList);

             }
             System.out.println("La tâche a été supprimer.");
             return true;


         } else {
             System.out.println("La tâche spécifiée n'a pas été trouvée.");
             return false;
         }

     }


//--------------------------------------Rechercher une tache--------------------------------------

     /**
      *  chercher une tache dans la liste des taches par son nom
      * @param tacheList la liste des taches
      * @param nomTache le nom de la tache a chercher
      * @return
      *
      */
     public SimpleTache rechercherTache(List<SimpleTache> tacheList ,String nomTache ){
       SimpleTache tache = new SimpleTache();

         int i = 0;
         // on cherche la tache par son nom
       while(i < tacheList.size()){

        if (tacheList.get(i).nom.equals(nomTache)){
            tache = tacheList.get(i);
            i = tacheList.size() ;
        }
        i++ ;
       }


         return tache;
     }

        //-----------------------------------| modifierTache |------------------------------------//

    /**
     * permet de modifier une tache dans la lists des taches et dans le planning
     * // mettre a jour la tache
     * @param tacheList la liste des taches
     * @param ID  l identifiant de la tache a modifier (son nom )
     * @param nomTache le nouveau nom de la tache
     * @param duree la nouvelle duree
     * @param ddl
     * @param etat
     * @param priorite
     * @param categorie
     * @param type
     */

     public Boolean modifieTache(List<SimpleTache> tacheList, String ID , String nomTache , Duration duree , LocalDate ddl,Etat etat , Priorite priorite , Categorie categorie ,String type) {

       // on cherche la tache a modifier
         SimpleTache tacheToModify = null;
         SimpleTache ToModify = null;
        for (SimpleTache tache : tacheList) {
            if (tache.nom.equalsIgnoreCase(ID)) {
                tacheToModify = tache;
                break;
            }
        }
        if (tacheToModify != null) {

            // Si elle existe je la cherche dans le dernier planning
            Planning planning = new Planning();
            List<HistoriquePlanning> historiquePlanList= recupererObjetFichier(pseudo+"_historiquePlanning.ser");
            HistoriquePlanning history = new HistoriquePlanning();

            // pour recuperer le dernier planning
            HistoriquePlanning historiquePlanning;
            if((historiquePlanList.size()) >0){
                // pour recuperer le dernier planning s'il existe
                historiquePlanning = history.recupererHistorique(historiquePlanList, historiquePlanList.size()-1);

                for (Planning plan :historiquePlanning.listPlanning) {
                    if (plan.tache.nom.equalsIgnoreCase(ID)) {
                       ToModify= plan.tache ;
                        break;
                    }
                }
                tacheToModify.nom = nomTache;
                tacheToModify.duree = duree;
                tacheToModify.deadline = ddl;
                tacheToModify.priorite = priorite;
                tacheToModify.etat = etat;
                tacheToModify.categorie = categorie;
                tacheToModify.type = type ;




                Utilisateur.sauvegarderObjetFichier(pseudo+"_taches.ser", tacheList);
                if (ToModify != null){
                    ToModify.nom = nomTache;
                    ToModify.duree = duree;
                    ToModify.deadline = ddl;
                    ToModify.priorite = priorite;
                    ToModify.etat = etat;
                    ToModify.categorie = categorie;
                    ToModify.type = type ;
                    planning.tache = ToModify ;
                   // historiquePlanning.listPlanning.add(planning);
                    LogInController.sauvegarderObjetFichier(pseudo+"_historiquePlanning.ser",historiquePlanList);

                }
            }
            else {
                tacheToModify.nom = nomTache;
                tacheToModify.duree = duree;
                tacheToModify.deadline = ddl;
                tacheToModify.priorite = priorite;
                tacheToModify.etat = etat;
                tacheToModify.categorie = categorie;
                tacheToModify.type = type ;


                Utilisateur.sauvegarderObjetFichier(pseudo+"_taches.ser", tacheList);

            }




            System.out.println("La tâche a été modifiée avec succès.");
            return true;

        } else {

            System.out.println("La tâche spécifiée n'a pas été trouvée.");
            return false;
        }
    }
	
}// fin de class
