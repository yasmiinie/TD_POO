package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.HistoriquePlanning;
import app.zaidiboussebata.Noyau.Planning;
import app.zaidiboussebata.Noyau.SimpleTache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

import static app.zaidiboussebata.Control.LogInController.*;

public class ScheduleController {
    //--------------------------------------------------------------------------------

    @FXML
    public Button tasksButton ;
    @FXML
    public Button calendartButton ;
    @FXML
    public Button timeSlotButton ;
    @FXML
    public Button  profileButton;
    @FXML
    public Button  scheduleButton;

    //--------------------------------------------------------------------------------
    @FXML
    public ListView<String> liste  ;
    //--------------------------------------------------------------------------------
    @FXML
    public Button manBut ;
    @FXML
    public Button autoBut ;
    @FXML
    public Button supBut ;
    @FXML
    public Button editBut ;

    @FXML
    public Button periodicBut ;
    //--------------------------------------------------------------------------------

    @FXML
    public  void navigateTasks(ActionEvent event){

        navigateTo(tasksButton,"/app/zaidiboussebata/TachePage.fxml","Tasks Page" , true) ;
        //    TacheController.initialize();
    }
    @FXML
    public  void navigateTimeSlot(ActionEvent event){
        navigateTo(timeSlotButton, "/app/zaidiboussebata/FreeSlotPage.fxml","Time Slot Page" , true) ;
    }
    @FXML
    public  void navigateCalendar(ActionEvent event){
        navigateTo(calendartButton,"/app/zaidiboussebata/Calendrier-view.fxml","Home Page" , true) ;
    }
    @FXML
    public  void navigateProfile(ActionEvent event){
        navigateTo(profileButton,"/app/zaidiboussebata/ProfilePage.fxml","Profile Page" , true) ;

    }
    @FXML
    public  void navigateSchedule(ActionEvent event){
        navigateTo(profileButton,"/app/zaidiboussebata/SchedulePage.fxml","Schedule Page" , true) ;

    }
//---------------------------------------------------------------------------------------------------------------------
    public void manClick(ActionEvent event){
        navigateTo( manBut , "/app/zaidiboussebata/manSchedule.fxml", "Schedule" , false);

    }
    public void autoClick(ActionEvent event){

        navigateTo( autoBut , "/app/zaidiboussebata/autoSchedule.fxml", "Schedule" , false);

    }
    public void editClick(ActionEvent event){
        navigateTo( editBut , "/app/zaidiboussebata/editTask.fxml", "Edit your Schedule" , false);

    }
    public void removeClick(ActionEvent event){
        navigateTo( supBut , "/app/zaidiboussebata/removeTask.fxml", "Remove from your Schedule" , false);
    }
    public void periodicClick(ActionEvent event){
        navigateTo( periodicBut , "/app/zaidiboussebata/periodic.fxml", "Remove from your Schedule" , false);
    }
    @FXML
    public Button ProjectButton ;
    @FXML
    public  void navigateProject(ActionEvent event){

        navigateTo(ProjectButton,"/app/zaidiboussebata/ProjetPage.fxml","Tasks Page" , true) ;
    }

    /**
     * Affiche la liste des encients planning
     *
     *
     */
    public void initialize() {

        liste.getItems().clear();
        // je recupere la liste des plannings
            List<HistoriquePlanning> historyList = recupererObjetFichier(pseudo+"_historiquePlanning.ser");
           if (historyList.size() >0){
               HistoriquePlanning history = historyList.get(historyList.size() - 1);
               liste.getItems().add("Planning : " );
               for (int j = 0; j < history.listPlanning.size() ; j++) {

                   Planning planning = history.listPlanning.get(j);
                   liste.getItems().add("            _Task 0" + (j + 1) + " : " + planning.tache.nom);
                   if(planning.bloquee == true){
                       liste.getItems().add("                   (The Slot is blocked for this task) " );
                   }
                   liste.getItems().add("                     Date : "+ planning.creneau.getDate());
                   liste.getItems().add("                     From : " +planning.creneau.getDebut());
                   liste.getItems().add("                     To :   " +planning.creneau.getFin());
                   liste.getItems().add("                     Type : " + planning.tache.type);
                   liste.getItems().add("                     Priority : " +  planning.tache.priorite);
                   liste.getItems().add("                     Duration : " +  planning.tache.duree);
                   liste.getItems().add("                     State : " +  planning.tache.etat);
                   liste.getItems().add("                     Category : " +  planning.tache.categorie);
                   liste.getItems().add("                     DeadLine : " +  planning.tache.deadline);
                   liste.getItems().add("                                 ");
               }
           }


    }

    /**
     * l'historique de tous les plannings precedent
     *
     * @param event
     */
   public void historyPlanning(ActionEvent event){

       liste.getItems().clear();
       // je recupere la liste des plannings
       List<HistoriquePlanning> historyList = recupererObjetFichier(pseudo+"_historiquePlanning.ser");


       for (int i = 0; i < historyList.size(); i++) {
           HistoriquePlanning history = historyList.get(i);
           liste.getItems().add("Planning 0" + (i + 1) + " : " );

           for (int j = 0; j < history.listPlanning.size() ; j++) {

               Planning planning = history.listPlanning.get(j);
               liste.getItems().add("            _Task 0" + (j + 1) + " : " + planning.tache.nom);
               liste.getItems().add("                     Date : "+ planning.creneau.getDate());
               liste.getItems().add("                     From : " +planning.creneau.getDebut());
               liste.getItems().add("                     To :   " +planning.creneau.getFin());
               liste.getItems().add("                     Type : " + planning.tache.type);
               liste.getItems().add("                     Priority : " +  planning.tache.priorite);
               liste.getItems().add("                     Duration : " +  planning.tache.duree);
               liste.getItems().add("                     State : " +  planning.tache.etat);
               liste.getItems().add("                     Category : " +  planning.tache.categorie);
               liste.getItems().add("                     DeadLine : " +  planning.tache.deadline);
               liste.getItems().add("                                 ");
           }
           liste.getItems().add("                                 ");
       }

   }
}
