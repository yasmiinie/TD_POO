package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.Creneau_libre;
import app.zaidiboussebata.Noyau.Etat;
import app.zaidiboussebata.Noyau.SimpleTache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static app.zaidiboussebata.Control.LogInController.*;
import static app.zaidiboussebata.Control.LogInController.pseudo;

public class FreeSlotController {

    @FXML
    public Button createBut;

    @FXML
    public TextField endSlot;
    @FXML
    public DatePicker dateSlot;




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


    //----------------------------------------------------------------------
    @FXML
    public ListView<String> liste ;


    @FXML
    public  void navigateProject(ActionEvent event){

        navigateTo(tasksButton,"/app/zaidiboussebata/ProjetPage.fxml","Tasks Page" , true) ;
        //    TacheController.initialize();
    }
    //-------------------------------------------------------------------------
    public  void initialize() {

        liste.getItems().clear();

        List<Creneau_libre> CreneauList = recupererObjetFichier(pseudo+"_creneau.ser");

        for (int i = 0; i < CreneauList.size(); i++) {
            Creneau_libre creneau = CreneauList.get(i);
            liste.getItems().add("Free Slot 0"+(i + 1)+ " : ");
            liste.getItems().add("            - From : "+creneau.getDebut().toString());
            liste.getItems().add("            - To :   "+creneau.getFin().toString());
            liste.getItems().add("            - Date : "+creneau.getDate().toString());
            liste.getItems().add("                                 ");
        }
    }

    public void addClick(ActionEvent event) {
        navigateTo( createBut , "/app/zaidiboussebata/addFreeSlot.fxml", "Create a Free Slot" , false);

    }

    public void removeClick(ActionEvent event) {
        navigateTo( createBut , "/app/zaidiboussebata/removeFreeSlot.fxml", "Edit a Free Slot" , false);

    }

    public void editClick(ActionEvent event) {
        navigateTo( createBut , "/app/zaidiboussebata/editFreeSlot.fxml", "Edit a Free Slot" , false);


    }


}
