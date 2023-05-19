package my_desktop_planner;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final String FICHIER_UTILISATEURS = "utilisateurs.ser";
    private static  String FICHIER_CRENEAU_LIBRE ;
    private static  String FICHIER_TACHE_SIMPLE ;


    public static void main(String[] args) {
    	 Scanner scanner = new Scanner(System.in);
      	List<Utilisateur> UtilisateurList = Utilisateur.recupererObjetFichier(FICHIER_UTILISATEURS);
     	Utilisateur user = new Utilisateur("");

/*******************************+| Debut Authentification |+*********************************************/
    	  
        /*  System.out.println("+ Bienvenue dans le système d'authentification!");
           
          while (true) {
              System.out.println("1. Créer un nouvel utilisateur");
              System.out.println("2. Connectez-vous avec un utilisateur existant");
              System.out.println("3. Supprimer utilisateur");
              System.out.println("4. Afficher les utilisateurs");
              System.out.println("5. Exit");
              System.out.print("Choisis une option: ");
              //Lecture du choix            
              int choix = scanner.nextInt();
              
              scanner.nextLine(); 
              String pseudo ;
              switch (choix) {
                  case 1:// cas de la creation d'un nouvel utilisateur
                 	 System.out.print("Saisissez le nouveau pseudo de l'Utilisateur :");
                 	 user.setPseudo(scanner.nextLine());
                 	Utilisateur.createUtilisateur(user.getPseudo(), UtilisateurList);
                      break;
                  case 2:// cas de l'identifcation
                 	 System.out.print("Enter your pseudo: ");
                 	 user.setPseudo(scanner.nextLine());
                      user.login(user.getPseudo(), UtilisateurList);
                      break;
                  case 3:// cas de supprisson d'un utilisateur
                 	  System.out.print("Enter the pseudo of the Utilisateur to delete: ");
                  	 user.setPseudo(scanner.nextLine());
                      user.supprimerUtilisateur(user.getPseudo(), UtilisateurList);
                      break;
                  case 4:// cas de supprisson d'un utilisateur
                 	 user.afficherUtilisateurs(UtilisateurList);
                     break;
                  case 5:// fin de programme
                	  Utilisateur.sauvegarderObjetFichier(FICHIER_UTILISATEURS, UtilisateurList);
                      System.out.println("Exiting the Authentication System. Goodbye!");
                      return;
                  default:
                      System.out.println("Choix invalide. Veuillez réessayer.");
              }
          }*/
 /*******************************+| fin Authentification |+*********************************************/


      
         
         
/*******************************+| Debut CreneauLibre |+*********************************************/
    	/*String pseudo ;
    	System.out.print("Enter your pseudo: ");
        pseudo = scanner.nextLine();
      	user.setPseudo(pseudo);

      	user.login(user.getPseudo(), UtilisateurList);
      	// on va faire un exception
        if(Utilisateur.isPseudoExist(user.getPseudo(), UtilisateurList)) {
          System.out.println("pseudo == "+user.getPseudo());
          String nomFichier = user.getPseudo()+"_creneaux_libres.ser";
          System.out.println("nom fichier == "+nomFichier);
          FICHIER_CRENEAU_LIBRE = nomFichier;
          Creneau_libre creneau = new Creneau_libre(null, null, null);
         
      List<Creneau_libre> CreneauLibreList = Utilisateur.recupererObjetFichier(FICHIER_CRENEAU_LIBRE);
         
     	while (true) {
            System.out.println("1. Créer Creneau");
            System.out.println("2. Supprimer Creneau");
            System.out.println("3. Affichier listes des creneaux");
            System.out.println("4. Exit");
            System.out.print("Choisis une option: ");
            //Lecture du choix            
            int choix = scanner.nextInt();
            
            scanner.nextLine(); 
            
            switch (choix) {
                case 1:// cas de la creation d'un nouvel utilisateur
                    System.out.print("Saisissez l'heure de début (HH:mm): ");
                    String debutString = scanner.nextLine();

                    System.out.print("Saisissez l'heure de fin (HH:mm): ");
                    String finString = scanner.nextLine();
                    //Date
                    System.out.print("Enter a date (yyyy-MM-dd): ");
                    String userInput = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(userInput, formatter);
                    
                    
                	creneau.createrCreneauLibre(FICHIER_CRENEAU_LIBRE,debutString,finString,date, CreneauLibreList);
                    break;
                case 2:
                	System.out.print("Entrez l'indice du créneau libre à supprimer : ");
                    int index = scanner.nextInt()+1;
                    scanner.nextLine(); 
                    creneau.supprimerCreneauLibre(FICHIER_CRENEAU_LIBRE,index, CreneauLibreList);
                    break;
                case 3:
                	creneau.afficherCreneauxLibres(CreneauLibreList);
                    break;
                case 4:
               	 Utilisateur.sauvegarderObjetFichier(FICHIER_CRENEAU_LIBRE, CreneauLibreList);
                    System.out.println("Exiting System. Goodbye!");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        }else {return;}*/
     	
/*******************************+| Fin CreneauLibre |+*********************************************/

     	
/*******************************+| Debut Tache |+*********************************************/

    	String pseudo ;
    	System.out.print("Enter your pseudo: ");
        pseudo = scanner.nextLine();
      	user.setPseudo(pseudo);
        System.out.println("pseudo == "+user.getPseudo());
        String nomFichier = user.getPseudo()+"_tahces_simples.ser";
        System.out.println("nom fichier == "+nomFichier);
        FICHIER_TACHE_SIMPLE = nomFichier;

        SimpleTache simpleTache = new SimpleTache();
        
        List<Tache> tacheList = Utilisateur.recupererObjetFichier(FICHIER_TACHE_SIMPLE);
                
     	while (true) {
            System.out.println("1. Créer TacheSimple");
            System.out.println("2. Supprimer TacheSimple");
            System.out.println("3. Modifier TacheSimple");
            System.out.println("4. Affichier listes des TacheSimple");
            System.out.println("5. Exit");
            System.out.print("Choisis une option: ");
            //Lecture du choix            
            int choix = scanner.nextInt();
            String nom ;
            scanner.nextLine(); 
            
           switch (choix) {
                case 1:// cas de la creation d'une tache
                
                	//Le nom de tache
                	System.out.print("Nom de la tâche : ");
                    nom = scanner.nextLine();
                    //Duree du tache
                    System.out.print("Durée de la tâche en minutes : ");
                    long dureeMinutes = scanner.nextLong();
                    scanner.nextLine(); // Consume the newline character
                    //Deadline
                    System.out.print("Enter a date (yyyy-MM-dd): ");
                    String userInput = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate deadline = LocalDate.parse(userInput, formatter);
                    //Priorite
                    Priorite priorite = Priorite.HEIGHT;
                    //Categorie
                    Categorie categorie= Categorie.HOBBY;
                    //Etat
                    Etat etat = Etat.IN_PROGRESS;

                    simpleTache.CreerTache(tacheList, nom, dureeMinutes,deadline, priorite, categorie, etat);
                   
                    break;
                case 2:
              
                	System.out.print("Nom de la tâche : ");
                    nom = scanner.nextLine();
                    simpleTache.supprimerTache(FICHIER_TACHE_SIMPLE,tacheList, nom);
                    break;
                case 3:
                	System.out.print("Nom de la tâche : ");
                    nom = scanner.nextLine();
                    simpleTache.modifierTache(FICHIER_TACHE_SIMPLE,tacheList, nom);
                	break;
                case 4:
                   SimpleTache.afficherTaches(tacheList);
                    break;
                case 5:
                    Utilisateur.sauvegarderObjetFichier(FICHIER_TACHE_SIMPLE, tacheList);
                    System.out.println("Exiting System. Goodbye!");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        
       
    }
 
       
}
  

