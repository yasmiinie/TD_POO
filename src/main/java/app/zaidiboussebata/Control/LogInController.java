package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogInController {
    @FXML
    private Button LogInButton;

    @FXML
    private TextField textField ;

    private  String pseudo ;
    private static final String FICHIER_UTILISATEURS = "utilisateurs.ser";
    private static final String FICHIER_CRENEAU_LIBRE = "creneaux_libres.ser";

@FXML
 private Label ErreurMsg ;
    /**
     * Afficher le message d'erreur
     */
    @FXML
    protected void onButtonClick() {
        ErreurMsg.setText("this User doesn't existe ! Try again");
    }
    public static <T> List<T> recupererObjetFichier(String nomFichier) {
        List<T> objetList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomFichier))) {
            objetList = (List<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Gérer l'exception en conséquence
        }
        return objetList;
    }

    public void navigateToAnotherPage() throws IOException {
        List<Utilisateur> UtilisateurList = recupererObjetFichier(FICHIER_UTILISATEURS);
        pseudo = textField.getText();
        System.out.println(pseudo);
        if (isPseudoExist(pseudo, UtilisateurList)){
            System.out.println("yep");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/zaidiboussebata/Calendrier-view.fxml"));
                Parent root = loader.load();

                // Create a new stage
                Stage stage = new Stage();
                stage.setTitle("Home Page");

                // Set the new scene
                Scene scene = new Scene(root);
                stage.setScene(scene);

                // Show the new stage
                stage.show();

                // Close the current stage (optional)
                Stage currentStage = (Stage) LogInButton.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            onButtonClick();
        }

    }
//--------------------------------| Login |--------------------------------------------------------//
    /**
     *  permet lauthentification de l'utilisateur par son pseudo
     *  si le pseudo exist donc la connexion est faite sinon un message d'erreur sera afficher
     *
     * @param UtilisateurList
     */
    private  void login(String pseudo, List<Utilisateur> UtilisateurList) {

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
    private static boolean isPseudoExist(String pseudo, List<Utilisateur> UtilisateurList) {
        for (Utilisateur utilisateur : UtilisateurList) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                return true;
            }
        }
        return false;
    }
//----------------------------| supprimerUtilisateur  |--------------------------------------------------------------//

    /**
     * permert de supprimer l'utilisateur dans le fichier des utilisateurs
     *
     * @param UtilisateurList
     */
    private static void supprimerUtilisateur(String pseudo, List<Utilisateur> UtilisateurList) {

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

    private static void createUtilisateur(String pseudo, List<Utilisateur> UtilisateurList) {

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

    /**
     * Permet d'afficher la liste des utilisateurs.
     * @param utilisateurList La liste des utilisateurs à afficher
     */
    private static void afficherUtilisateurs(List<Utilisateur> utilisateurList) {
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
    /**
     * Permet de sauvegarder les objets dans un fichier
     * @param <T>         Le type d'objet à sauvegarder
     * @param nomFichier  Le nom du fichier
     * @param objetList   La liste d'objets à sauvegarder
     */
    private static <T> void sauvegarderObjetFichier(String nomFichier, List<T> objetList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            outputStream.writeObject(objetList);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception en conséquence
        }
    }


}
