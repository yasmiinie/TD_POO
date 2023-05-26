package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.SimpleTache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

import static app.zaidiboussebata.Control.LogInController.*;

public class ProjetCpntroller {

    @FXML
    public ListView<String> liste ;

    @FXML
    public Button ProjectButton ;
    @FXML
    public  void navigateProject(ActionEvent event){

        navigateTo(ProjectButton,"/app/zaidiboussebata/ProjetPage.fxml","Tasks Page" , true) ;
    }
    public  void initialize() {
     /*   liste.getItems().clear();

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

        }*/

    }

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
        navigateTo(scheduleButton,"/app/zaidiboussebata/SchedulePage.fxml","Schedule Page" , true) ;

    }
    public void addProject(ActionEvent event) {
    }

    public void editClick(ActionEvent event) {
    }

    public void removeClick(ActionEvent event) {
    }

    public void TodayTasks(ActionEvent event) {
    }
}
