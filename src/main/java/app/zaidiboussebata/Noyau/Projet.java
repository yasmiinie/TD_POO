package app.zaidiboussebata.Noyau;
import java.util.List;

import static app.zaidiboussebata.Control.LogInController.pseudo;
import static app.zaidiboussebata.Noyau.Utilisateur.sauvegarderObjetFichier;

public class Projet {
    public String nom;
    public String description;
    public List<SimpleTache> listeTaches ;
    public Etat etat;


    public Projet(){

    }
    // constructeur == ajouter projet

    /**
     * Constructeur
     * @param nom
     * @param description
     * @param listeTaches
     * @param etat
     */
    public Projet(String nom, String description,List<SimpleTache> listeTaches,Etat etat) {
        this.description = description;
        this.nom = nom;
        this.listeTaches = listeTaches;
        this.etat = etat;
    }
//---------------------------------------------------------------------------------------------------------

    /**
     * Permet la creation d'un projeet
     * @param nom
     * @param description
     * @param etat
     * @param listeTaches
     */
    public void creerProjet(String nom,String description,Etat etat,List<SimpleTache> listeTaches){
        List<Projet> historiqueProjet = Utilisateur.recupererObjetFichier(pseudo+"_projets.ser");

        Projet project = new Projet(nom, description, listeTaches, etat);

        // Add the new project to the list
        historiqueProjet.add(project);

       sauvegarderObjetFichier(pseudo+"_projets.ser",historiqueProjet);

    }
// l'utilisation de creer un projet :
    /*
     List<SimpleTache> listeTaches = recupererObjetFichier(FICHIER_TACHE_SIMPLE);
               List<SimpleTache> list = new ArrayList<>();
              System.out.print("Nom de Projet : ");
                nom = scanner.nextLine();
                System.out.print("description:");
               String description = scanner.nextLine();
               Boolean aa = false;
               while(aa == false){
              System.out.print("|1|- ajouter tache \n|2| -exit(fin de tache de prjt)\n+votre choix:");
              int i = scanner.nextInt();
              switch(i) {
              case 1:
                     SimpleTache tach = new SimpleTache();
                    //Le nom de tache

                       //Duree du tache
                       System.out.print("Durée de la tâche en minutes : ");
                       long zx=scanner.nextLong();
                        Duration du = Duration.ofMinutes(zx) ;
                        tach.duree = du;
                       scanner.nextLine(); // Consume the newline character

                       System.out.print("nom tache:");
                       String str = scanner.nextLine();
                         tach.nom = str;
                       //Deadline
                       System.out.print("Enter deadline (yyyy-MM-dd): ");
                        userInput = scanner.nextLine();
                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        tach.deadline = LocalDate.parse(userInput, formatter);
                       //Priorite
                        tach.priorite = Priorite.HEIGHT;
                       //Categorie
                        tach.categorie= Categorie.STUDIES;
                       //Etat
                        tach.etat = Etat.UNSCHEDULED;
                          listeTaches.add(tach);
                          list.add(tach);
              break;
              case 2:
              aa = true;
              break;
              };

               }

               etat = Etat.IN_PROGRESS;
               Projet project = new Projet();
               project.creerProjet(FICHIER_HISTORIQUE_PROJET, nom, description, etat, list);
               sauvegarderObjetFichier(FICHIER_TACHE_SIMPLE, listeTaches);


     */



}
