package app.zaidiboussebata.Control;
import app.zaidiboussebata.Noyau.Categorie;
import app.zaidiboussebata.Noyau.Etat;
import app.zaidiboussebata.Noyau.Priorite;
import app.zaidiboussebata.Noyau.SimpleTache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import static app.zaidiboussebata.Control.LogInController.*;


public class AddTacheController {

    String type = "SIMPLE"; //par defaut cest une tache simple
    Categorie categorie = Categorie.OTHER ; //par defaut cest une other
    Priorite priorite = Priorite.HIGH ; //par defaut cest une High
    Etat etat = Etat.UNSCHEDULED ; // par defaut unsheduled


   // ---------------------------------Check boxes pour la page editTache--------------------------------
    @FXML
    public TextField tacheName;
    @FXML
    public CheckBox checkName;
    @FXML
    public CheckBox checkType;
    @FXML
    public CheckBox checkDuree;
    @FXML
    public CheckBox checkDDL;
    @FXML
    public CheckBox checkCat;
    @FXML
    public CheckBox checkPri;
    @FXML
    public CheckBox checkState;
//-------------------------------------------------------------------------------------------------
    @FXML
    public TextField tacheID;

    @FXML
    public TextField  supID ;

    @FXML
    public TextField tacheDuree;
    @FXML
    public DatePicker ddl;
    @FXML
    public MenuButton typeM;
    @FXML
    public MenuButton categories ;
    @FXML
    public MenuButton priorities ;
    @FXML
    public MenuButton states ;
    @FXML
    public DatePicker deadLine ;
    @FXML
    public Label TaskErreur ;// le message d'erreur dans le cas d'une tache non trouvee
//--------------------------Les bouttns------------------------------------------------------------------------------------
    @FXML
    public Button removeBut;
    @FXML
    public Button addBut ;// le bouton pour ajouter une tache
    @FXML
    public Button editBut ;// le bouton pour modifier une tache

    @FXML
    protected void taskNotFoundClick() {
        TaskErreur.setText("This Task doesn't existe ! Try again");
    }
    // ----------------------------Les priorites---------------------------

    /**
     * quand je click sur le bouton High
     * @param event
     */
    public void HighClicked(ActionEvent event){
        priorite =  Priorite.HIGH;
        priorities.setText("HIGH");

    }

    public void MediumClicked(ActionEvent event){
        priorite = Priorite.MEDIUM;
        priorities.setText("MEDIUM");

    }
    public void LowClicked(ActionEvent event){
        priorite = Priorite.LOW;
        priorities.setText("LOW");
    }
    // ----------------------------Les categories---------------------------

    public void OtherClicked(ActionEvent event){
        categorie =  Categorie.OTHER;
        categories.setText("Other");
    }
    public void StudiesClicked(ActionEvent event){
        categorie =  Categorie.STUDIES;
        categories.setText("Studies");
    }
    public void HobbyClicked(ActionEvent event){
        categorie =  Categorie.HOBBY;

        categories.setText("HOBBY");
    }
    public void WorkClicked(ActionEvent event){
        categorie =  Categorie.WORK;
        categories.setText("WORK");
    }
    public void SportClicked(ActionEvent event){
        categorie =  Categorie.SPORT;
        categories.setText("SPORT");
    }
    public void HealthClicked(ActionEvent event){
        categorie =  Categorie.HEALTH;
        categories.setText("HEALTH");
    }
//----------------------------------------------Les Etats--------------------------------------------
    @FXML
    public void DelayedClicked(ActionEvent event) {
        etat = Etat.DELAYED ;
        states.setText("DELAYED");
    }
    @FXML
    public void CancelledClicked(ActionEvent event) {
        etat = Etat.CANCELLED ;
        states.setText("CANCELLED");
    }
    @FXML
    public void InProgressClicked(ActionEvent event) {
        etat = Etat.IN_PROGRESS ;
        states.setText("IN_PROGRESS");
    }
    @FXML
    public void UnscheduledClicked(ActionEvent event) {
        etat = Etat.UNSCHEDULED ;
        states.setText("UNSCHEDULED");
    }
    @FXML
    public void RealizedClicked(ActionEvent event) {
        etat = Etat.NON_REALISEE ;
        states.setText("NOT-REALIZED");
    }
    @FXML
    public void CompletedClicked(ActionEvent event) {
        etat = Etat.COMPLETED ;
        states.setText("COMPLETED");
    }
//_------------------------------------------------------------------------------------------



    /**
     * Permet de convertir le text en typede DURATION
     *
     * @param durationText
     * @return type Duration
     */
    private Duration parseDuration(String durationText) {
        // Implement your custom parsing logic here
        // Example: Parse duration in the format "HH:MM"
        String[] parts = durationText.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
       // int seconds = Integer.parseInt(parts[2]);

        return Duration.ofHours(hours).plusMinutes(minutes);
    }


 // ------------------------------Les Types---------------------------------------------------------------------
    /**
     * permet de choisir un element de mon menu
     * @param event
     */
    public void simpleButtonClicked(ActionEvent event){
        type = "SIMPLE";
        typeM.setText("SIMPLE");
    }

    /**
     * permet de choisir un element de mon menu
     * @param event
     */
    public void decomposableButtonClicked(ActionEvent event){
        type = "DECOMPOSABLE";
        typeM.setText("DECOMPOSABLE");
    }

//---------------------------------Ajouter une tache---------------------------------------------------------------------------
    /**
     *  ajouter une nouvelle tache au creneau
     *
     * @param event
     */
    public void addTache(ActionEvent event) {
        List<SimpleTache> tacheList = recupererObjetFichier(pseudo+"_taches.ser");

        SimpleTache simple = new SimpleTache();
        simple.nom = tacheName.getText() ;
        simple.type = type;
        simple.etat = Etat.UNSCHEDULED ;
        simple.deadline = deadLine.getValue();
        simple.categorie = categorie ;
        simple.priorite = priorite ;

        try {
            Duration duration = parseDuration(tacheDuree.getText());
            simple.duree = duration;
            // Use the duration as needed
        } catch (IllegalArgumentException e) {
            // Handle invalid duration input
        }


        tacheList.add(simple) ;
        sauvegarderObjetFichier(pseudo+"_taches.ser", tacheList); // on sauvegarde dans le fichier
        // on ferme la fenetre
        Stage currentStage = (Stage) addBut.getScene().getWindow();
        currentStage.close();
    }




//-------------------------------------Modifier une tache-----------------------------------------------------------------------------------------------------

    /**
     * permet de faire des modifications de tous les champs d'une tache
     *
     * @param event
     */
    @FXML
    public void editTache(ActionEvent event) {
        // on lit la liste des taches
        List<SimpleTache> tacheList = recupererObjetFichier(pseudo + "_taches.ser");

        SimpleTache simple = new SimpleTache();

        SimpleTache rechrcherTache = new SimpleTache();
        rechrcherTache = simple.rechercherTache(tacheList, tacheID.getText());

        String name = rechrcherTache.nom;
        Duration duree = rechrcherTache.duree;
        LocalDate deadlin = rechrcherTache.deadline;
        Priorite prio = rechrcherTache.priorite;
        Categorie cate = rechrcherTache.categorie;
        Etat eta = rechrcherTache.etat;
        String typ = rechrcherTache.type;


        System.out.println(rechrcherTache.priorite);
        System.out.println(rechrcherTache.type);
        System.out.println(rechrcherTache.deadline);
        System.out.println(rechrcherTache.nom);

        if (checkName.isSelected()) {
            name = tacheName.getText();
            System.out.println("name selected");
            System.out.println(tacheName.getText());
        }
        if (checkType.isSelected()) {
            typ = type;
        }
        if (checkDuree.isSelected()) {
            Duration duration = parseDuration(tacheDuree.getText());
            duree = duration;

        }
        if (checkDDL.isSelected()) {
            deadlin = ddl.getValue();
        }
        if (checkCat.isSelected()) {
            cate = categorie;
        }
        if (checkPri.isSelected()) {
            prio = priorite;
        }
        if (checkState.isSelected()) {
            eta = etat;
        }

        if (simple.modifieTache(pseudo + "_taches.ser", tacheList, tacheID.getText(), name, duree, deadlin, eta, prio, cate, typ)) {

            // on ferme la fenetre
            Stage currentStage = (Stage) editBut.getScene().getWindow();
            currentStage.close();
        } else {
            taskNotFoundClick();
        }
    }
      //-----------------------------------------Remove-----------------------------------
    @FXML
    public void removeTache(ActionEvent event) {
        // on lit la liste des taches
        List<SimpleTache> tacheList = recupererObjetFichier(pseudo + "_taches.ser");

        SimpleTache simple = new SimpleTache() ;

       if (simple.supprimerTache(FICHIER_TACHE , tacheList , supID.getText() )){
           // on ferme la fenetre
           Stage currentStage = (Stage) removeBut.getScene().getWindow();
           currentStage.close();
       }
       else {
           taskNotFoundClick();
       }

    }


}



