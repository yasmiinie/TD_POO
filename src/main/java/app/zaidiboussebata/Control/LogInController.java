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
    private Button SignUpButton;

    @FXML
    private Button CreateButton;

    @FXML
    private Button CreatedButton;

    @FXML
    private TextField textField ;// le pseudo entrer dans le login
    @FXML
    private TextField signupField ;// le pseudo entere dans le sign up


    private  String pseudo  ;
    private static final String FICHIER_UTILISATEURS = "utilisateurs.ser";
    private static final String FICHIER_CRENEAU_LIBRE = "creneaux_libres.ser";

    @FXML
    private Label ErreurMsg ;// le message derruer dans le cas d'un pseudo non valide
    //dans le log in
    @FXML
    private Label ErreurPseudo; // un pseudo deja existant (sign up )

    /**
     * Afficher le message d'erreur
     */
    @FXML
    protected void onButtonClick() {
        ErreurMsg.setText("This User doesn't existe ! Try again");
    }
    @FXML
    protected void onSignupClick() {
       ErreurPseudo.setText("This user name is already used ! Try again");
    }

    List<Utilisateur> UtilisateurList = recupererObjetFichier(FICHIER_UTILISATEURS);
    //---------------------SignUp ----------------------------
    public void signUp(){
      //  supprimerUtilisateur("", UtilisateurList) ;
       pseudo = signupField.getText();
        createUtilisateur( pseudo, UtilisateurList);
    }

    /**
     * permet la creation du nouvel utilisateur
     * @param pseudo son pseudo
     * @param UtilisateurList la liste de tous les utilisateur pour faire la verificztion
     */
    private  void createUtilisateur(String pseudo, List<Utilisateur> UtilisateurList) {
        if (pseudo != ""){
            //Verification de pseudo si il exist
            if (isPseudoExist(pseudo, UtilisateurList)) {
                onSignupClick();
                System.out.println("Le pseudo existe déjà. Veuillez en choisir un autre.");
            } else {

                Utilisateur newUtilisateur = new Utilisateur(pseudo);
                UtilisateurList.add(newUtilisateur);
                sauvegarderObjetFichier(FICHIER_UTILISATEURS, UtilisateurList);// on sauvgarde le nouveau uti dans le fichier
                System.out.println("Utilisateur created successfully!");
                navigateTo(SignUpButton ,"/app/zaidiboussebata/Calendrier-view.fxml" , "Home Page");
            }
        }



    }
//--------------------------------| Login |--------------------------------------------------------//
    /**
     * permet lauthentification de l'utilisateur par son pseudo
     *  si le pseudo exist donc la connexion est faite sinon un message d'erreur sera afficher
     * @throws IOException
     */
    public void logIn() throws IOException {
        pseudo = textField.getText();
        System.out.println(pseudo);
        if (isPseudoExist(pseudo, UtilisateurList)){

            navigateTo(LogInButton ,"/app/zaidiboussebata/Calendrier-view.fxml" , "Home Page");
        }
        else {
                onButtonClick();
        }


    }

    public void createAccount(){
        navigateTo(CreateButton ,"/app/zaidiboussebata/SignUpPage.fxml", "Sign Up Page");
    }
    public void navigateToLogin(){
    navigateTo(CreatedButton ,"/app/zaidiboussebata/LogInPage.fxml", "Log In Page");
    }

   public void navigateTo(Button Button ,String path , String title){
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        // Create a new stage
        Stage stage = new Stage();
        stage.setTitle(title);

        // Set the new scene
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Show the new stage
        stage.show();

        // Close the current stage (optional)
        Stage currentStage = (Stage) Button.getScene().getWindow();
        currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
//-------------------------------------| IsPseudoExist|--------------------------------------------------------//

    /**
     *  peremt de verifier si le pseudo existe dans la list des utilisateur recuperer du fichier Utilisateur
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

    public static <T> List<T> recupererObjetFichier(String nomFichier) {
        List<T> objetList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomFichier))) {
            objetList = (List<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Gérer l'exception en conséquence
        }
        return objetList;
    }


}
