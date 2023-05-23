package app.zaidiboussebata.Noyau;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Utilisateur implements Serializable{
	private String pseudo;
	static int nbr_tache = 0;
    private static final String FICHIER_UTILISATEURS = "utilisateurs.ser";

//--------------------------------| constructeur |--------------------------------------------------------//
	public Utilisateur(String pseudo) {
		this.setPseudo(pseudo);
	}
	
//--------------------------------| getters setters |--------------------------------------------------------//
     
	public String getPseudo() {return pseudo;}
	public void setPseudo(String pseudo) {this.pseudo = pseudo;}
	
	
//--------------------------------| Login |--------------------------------------------------------//
    /**
     *  permet de la connexion de l'utilisateur par son pseudo
     *  si le pseudo exist donc la connexion est faite sinon elle va affichier un message d'erreur
     * @param pseudo
     * @param UtilisateurList
     */
    public static void login(String pseudo, List<Utilisateur> UtilisateurList) {
        
        if (isPseudoExist(pseudo, UtilisateurList)) {
            System.out.println("------------|+ Connexion réussie! +|-------------");
        } else {
            System.out.println(" Le pseudo n'existe pas. Veuillez réessayer ou créer un nouvel Utilisateur.");
        }
    }
    
//-------------------------------------| IsPseudoExist|--------------------------------------------------------//

    /**
     *  peremt de verifier si le pseudo existe dans la list des utilisateur qui etait recuperer a partir de fichier
     * @param pseudo
     * @param UtilisateurList
     * @return
     */
    public static boolean isPseudoExist(String pseudo, List<Utilisateur> UtilisateurList) {
        for (Utilisateur utilisateur : UtilisateurList) {
            if (utilisateur.getPseudo().equals(pseudo)) {return true;}
        }
        return false;
    }
    
//----------------------------| supprimerUtilisateur  |--------------------------------------------------------------//    
    
    /**
     * permert de supprimer l'utilisateur dans le fichier des utilisateurs
     * @param scanner
     * @param UtilisateurList
     */
    public static void supprimerUtilisateur(String pseudo, List<Utilisateur> UtilisateurList) {
      
        Iterator<Utilisateur> iterator = UtilisateurList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Utilisateur Utilisateur = iterator.next();
            if (Utilisateur.getPseudo().equals(pseudo)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
        	sauvegarderObjetFichier(FICHIER_UTILISATEURS, UtilisateurList);
            System.out.println("Utilisateur deleted successfully!");
        } else {
            System.out.println("Utilisateur not found. Deletion failed.");
        }
    }


//----------------------------| createUtilisateur  |--------------------------------------------------------------//    

    /**
     *  permet de creer un utilisateur 
     *  verifier si le pseudo existe si oui elle va afficheir un message d'erreur sinon elle va creer un nouvel utilisatuer 
     * @param pseudo
     * @param UtilisateurList
     */
public static void createUtilisateur(String pseudo, List<Utilisateur> UtilisateurList) {
    
   //Verification de pseudo si il exist
   if (isPseudoExist(pseudo, UtilisateurList)) {
       System.out.println("Le pseudo existe déjà. Veuillez en choisir un autre.");
   } else {
       Utilisateur newUtilisateur = new Utilisateur(pseudo);
       UtilisateurList.add(newUtilisateur);
       sauvegarderObjetFichier(FICHIER_UTILISATEURS, UtilisateurList);
       System.out.println("Utilisateur created successfully!");
   }

   }

//----------------------------| afficherUtilisateurs  |--------------------------------------------------------------//    

/**
 * Permet d'afficher la liste des utilisateurs.
 * @param utilisateurList La liste des utilisateurs à afficher
 */
public static void afficherUtilisateurs(List<Utilisateur> utilisateurList) {
    System.out.println("Liste des utilisateurs :");
    if (utilisateurList.size() != 0) {
        for (int i = 0; i < utilisateurList.size(); i++) {
            Utilisateur utilisateur = utilisateurList.get(i);
            System.out.println("Utilisateur " + (i + 1) + ": " + utilisateur.getPseudo());
        }
    } else {
        System.out.println("La liste des utilisateurs est vide.");
    }
}

//----------------------------------------| recupererObjetFichier |-----------------------------------------------------//
  
/**
   * Permet de récupérer les objets à partir d'un fichier
   * @param <T>         Le type d'objet à récupérer
   * @param nomFichier  Le nom du fichier
   * @return            Une liste d'objets du type spécifié
   */
  public static <T> List<T> recupererObjetFichier(String nomFichier) {
      List<T> objetList = new ArrayList<>();
      try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomFichier))) {
          objetList = (List<T>) inputStream.readObject();
      } catch (IOException | ClassNotFoundException e) {
          // Gérer l'exception en conséquence
      }
      return objetList;
  }
  
//----------------------------------------| sauvegarderObjetFichier |-----------------------------------------------------//
 
  /**
   * Permet de sauvegarder les objets dans un fichier
   * @param <T>         Le type d'objet à sauvegarder
   * @param nomFichier  Le nom du fichier
   * @param objetList   La liste d'objets à sauvegarder
   */
  public static <T> void sauvegarderObjetFichier(String nomFichier, List<T> objetList) {
      try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
          outputStream.writeObject(objetList);
      } catch (IOException e) {
          e.printStackTrace();
          // Gérer l'exception en conséquence
      }
  }

	
}
