package app.zaidiboussebata.Control;
import app.zaidiboussebata.Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;
import static app.zaidiboussebata.Control.LogInController.navigateTo;
import static app.zaidiboussebata.Control.LogInController.*;


public class TacheController {

    @FXML
    public Button createBut;
    //--------------------------------------------------------------------------------
    public Button  tasksButton;
    @FXML
    public Button  timeSlotButton;
    @FXML
    public Button calendartButton ;
    @FXML
    public Button  profileButton;
    @FXML
    public Button  scheduleButton;


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
    //_---------------------------------------------------------



    @FXML
    public  ListView<String> liste ;

    @FXML
    public  void navigateProject(ActionEvent event){

        navigateTo(tasksButton,"/app/zaidiboussebata/ProjetPage.fxml","Tasks Page" , true) ;
        //    TacheController.initialize();
    }

    /**
     * permet  l'initialisation de la liste a partir du fichier
     *
     */
    public  void initialize() {
        liste.getItems().clear();

        List<SimpleTache> tacheList = recupererObjetFichier(pseudo+"_taches.ser");

        for (int i = 0; i < tacheList.size(); i++) {
            SimpleTache simple = tacheList.get(i);
            liste.getItems().add("Task 0"+(i + 1)+ " : " +simple.nom);
            liste.getItems().add("            -Type : "+simple.type);
            liste.getItems().add("            -Priority : "+simple.priorite.toString());
            liste.getItems().add("            -Duration : "+simple.duree);
            liste.getItems().add("            -State : "+simple.etat);
            liste.getItems().add("            -Category : "+simple.categorie);
            liste.getItems().add("             -DeadLine : "+simple.deadline);
            liste.getItems().add("                                 ");

        }

    }
    public void addClick(ActionEvent event){
    navigateTo( createBut , "/app/zaidiboussebata/addTask.fxml", "Create a Task" , false);

    }
    public void removeClick(ActionEvent event){
        navigateTo( createBut , "/app/zaidiboussebata/removeTask.fxml", "Remove a Task" , false);
    }

    public void editClick(ActionEvent event){
        navigateTo( createBut , "/app/zaidiboussebata/editTask.fxml", "Edit a Task" , false);
    }
    public void TodayTasks(ActionEvent event){
        liste.getItems().clear();

        List<Planning> dateList = Journee.tacheOfDay(LocalDate.now(), pseudo+"_historiquePlanning");
        //affichage de la list
        if(dateList.size() ==0 ) {   liste.getItems().add("You dont have any taks")     ;return;}
        for(Planning plan : dateList) {

            liste.getItems().add("Task "+ " : " +plan.tache.nom);
            liste.getItems().add("            -Type : "+plan.tache.type);
            liste.getItems().add("            -Priority : "+plan.tache.priorite.toString());
            liste.getItems().add("            -Duration : "+plan.tache.duree);
            liste.getItems().add("            -State : "+plan.tache.etat);
            liste.getItems().add("            -Category : "+plan.tache.categorie);
            liste.getItems().add("            -DeadLine : "+plan.tache.deadline);
            liste.getItems().add("            -From : "+plan.creneau.getDebut());
            liste.getItems().add("            -To : "+plan.creneau.getFin());
            liste.getItems().add("            -Date : "+plan.creneau.getDate());
            liste.getItems().add("                                 ");
        }
        Etat etat = Journee.getEtatGlobal(dateList);
        System.out.println("\n\nVotre etat de cette journee  est "+etat);
    }

}
