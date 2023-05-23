package app.zaidiboussebata.Noyau;
import java.io.*; 
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {
    private static final String FICHIER_UTILISATEURS = "utilisateurs.ser";
    private static  String FICHIER_CRENEAU_LIBRE ;
    private static  String FICHIER_PLANNING ;
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
              swiplanListh (choix) {
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
    	/*
     	String pseudo ;
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
            
            swiplanListh (choix) {
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
                    int index = scanner.nextInt();
                    scanner.nextLine(); 
                    creneau.supprimerCreneauLibre(FICHIER_CRENEAU_LIBRE,index, CreneauLibreList);
                    break;
                case 3:
                    Creneau_libre.orderCreneauList(CreneauLibreList);

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
/*
    	String pseudo ;
    	System.out.print("Enter your pseudo: ");
        pseudo = scanner.nextLine();
      	user.setPseudo(pseudo);
        System.out.println("pseudo == "+user.getPseudo());
        String nomFichier = user.getPseudo()+"_taches_simples.ser";
        System.out.println("nom fichier == "+nomFichier);
        FICHIER_TACHE_SIMPLE = nomFichier;

        SimpleTache simpleTache = new SimpleTache();
        
        List<SimpleTache> tacheList = Utilisateur.recupererObjetFichier(FICHIER_TACHE_SIMPLE);
         
        SimpleTache.orderTacheList(tacheList);
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
       */
 /*******************************+| Debut Planning |+*********************************************/
     	// Lecture de pseudo
    	String pseudo ;
    	System.out.print("Enter your pseudo: ");
        pseudo = scanner.nextLine();
      	user.setPseudo(pseudo);
      	
        String FICHIER_PLANNING = user.getPseudo()+"_planning.ser";
        String FICHIER_CRENEAU_LIBRE = user.getPseudo()+"_creneaux_libres.ser";
        String FICHIER_TACHE_SIMPLE = user.getPseudo()+"_taches_simples.ser";

        
        List<SimpleTache> tacheList = Utilisateur.recupererObjetFichier(FICHIER_TACHE_SIMPLE);
        List<Creneau_libre> CreneauLibreList = Utilisateur.recupererObjetFichier(FICHIER_CRENEAU_LIBRE);
        List<Planning> listplanList = new ArrayList<>();
        System.out.println("\n===========================+| Tache |+=================== ");
        SimpleTache.afficherTaches(tacheList);
        System.out.println("\n===========================+| Creneau |+=================== ");

        Creneau_libre.afficherCreneauxLibres(CreneauLibreList);

        System.out.print("Enter debut (yyyy-MM-dd): ");
        String userInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate debut = LocalDate.parse(userInput, formatter);
        System.out.print("Enter fin (yyyy-MM-dd): ");
        userInput = scanner.nextLine();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fin = LocalDate.parse(userInput, formatter);
        /*System.out.print("Enter date (yyyy-MM-dd): ");
        userInput = scanner.nextLine();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(userInput, formatter);
        System.out.println("\n\n is exit : "+Periode.isDateInside(date, fin, date));*/
       
        Planning.plannifier(debut,fin,tacheList, CreneauLibreList, listplanList);
        if(listplanList.isEmpty()) {System.out.println(" planing is vide");};
        for(Planning plan : listplanList) {
            System.out.println("\n+ plan = nom : "+plan.tache.nom+"| Etat = "+plan.tache.etat+" | debut : "+plan.creneau.getDebut()+" | fin : "+plan.creneau.getFin()+" | date  : "+plan.creneau.getDate());
        }
        
       SimpleTache.afficherTaches(tacheList);
       Creneau_libre.afficherCreneauxLibres(CreneauLibreList);

        
        /*  
        SimpleTache.orderTacheList(tacheList);
        System.out.println("\n------------------------------| Befor Tache|------------------------------\n");
        SimpleTache.afficherTaches(tacheList);
        System.out.println("\n------------------------------| After Tache|------------------------------\n");
        SimpleTache.permuterTachesMemeDeadline(tacheList);
        SimpleTache.afficherTaches(tacheList);*/

        /*
            Planning.plannifier(tacheList, CreneauLibreList, listplanList);
            
            for(Planning plan:listplanList) {
                System.out.println("\n+ plan = nom : "+plan.tache.nom+"| Etat = "+plan.tache.etat+" | debut : "+plan.creneau.getDebut()+" | fin : "+plan.creneau.getFin()+" | date  : "+plan.creneau.getDate());
              }*/
            
            
        /*
        for (SimpleTache tache : tacheList) {
            for (int j = 0; j < CreneauLibreList.size(); j++) {
            	
                Creneau_libre creneau = CreneauLibreList.get(j);
                
                if (Planning.TacheCompatibleCreneau(tache,creneau)){

                	tache.etat = Etat.IN_PROGRESS;
                	Planning planList = new Planning();

                	if (tache.getDuree().plusMinutes(creneau.dureeMin.toMinutes()).compareTo(Duration.between(creneau.getDebut(), creneau.getFin())) <= 0) {
                		System.out.println("\n+==== | INSIDE IF | =====+\n");

                		Creneau_libre cr = new Creneau_libre(creneau.getDebut(), creneau.getDebut().plus(tache.duree), creneau.getDate());

                		planList.tache= tache;
                        planList.creneau= cr;
                        listplanList.add(planList);
                		LocalTime time = creneau.getDebut().plus(tache.duree);
                        creneau.setDebut(time);

                    }else {
                		System.out.println("\n+==== | INSIDE ELSE | =====+\n");

                    	planList.tache= tache;
                        planList.creneau= creneau;
                        listplanList.add(planList);
                    	CreneauLibreList.remove(j);
                    }
                       break;
                     // j = CreneauLibreList.size();
                }else {
                	tache.etat = Etat.UNSCHEDULED;
                }
            }
            
        }*/
        
        
        


        

        /*while (true) {
            System.out.println("Vous voulez planifier vos taches?");
            System.out.println("1. Oui");
            System.out.println("2. Non");
            int choix = scanner.nextInt();

            switch (choix) {
            case 1:
            	break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
            }*/
        
        
              
          
       

       
        
        

       

        
      

     
        
        
    
        
        
        


       
      	
 /*******************************+| FIN Planning |+*********************************************/

    }

  
 
       
}
  

