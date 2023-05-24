package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.zaidiboussebata.Control.LogInController.navigateTo;
import static app.zaidiboussebata.Control.LogInController.pseudo;

public class ProfileController {
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
    public Label pseudoLabel  ;
    @FXML
    public Label congratsLabel ;
    @FXML
    public Label VgoodLabel ;
    @FXML
    public Label goodLabel ;
    @FXML
    public Label excelLabel ;


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

    public void initialize(){
        pseudoLabel.setText(pseudo);
        List<CompletedTacheDay> mapCompletedDay = new ArrayList<>();

        List<HistoriquePlanning> historiquePlanList = Utilisateur.recupererObjetFichier(pseudo+"_historiquePlanning.ser" );
        Map<LocalDate, Integer> completedTasksByDay = new HashMap<>();
        CompletedTacheDay.calculateCompletedTasks(historiquePlanList, completedTasksByDay);
        CompletedTacheDay.printCompletedTacheDay(completedTasksByDay);
        Map<Badge, Integer> badgeCounts = new HashMap<>();
        Felicitation.updateBadgeCounts(completedTasksByDay, badgeCounts, Felicitation.nbr_min_tache);
        congratsLabel.setText(String.valueOf(badgeCounts.getOrDefault(Badge.FELICITATION, 0)));
        goodLabel.setText(String.valueOf(badgeCounts.getOrDefault(Badge.GOOD, 0)));
        VgoodLabel.setText(String.valueOf(badgeCounts.getOrDefault(Badge.VERYGOOD, 0)));
        excelLabel.setText(String.valueOf(badgeCounts.getOrDefault(Badge.EXCELLENT,0)));

    }
public void fct(){
    /*List<CompletedTacheDay> mapCompletedDay = new ArrayList<>();
    List<HistoriquePlanning> historiquePlanList = Utilisateur.recupererObjetFichier(FICHIER_HISTORIQUE_PLANNING);
    Map<LocalDate, Integer> completedTasksByDay = new HashMap<>();
    CompletedTacheDay.calculateCompletedTasks(historiquePlanList, completedTasksByDay);
    CompletedTacheDay.printCompletedTacheDay(completedTasksByDay);
    Map<Badge, Integer> badgeCounts = new HashMap<>();

    // Populate the completedTasksByDay map with data

    Felicitation.updateBadgeCounts(completedTasksByDay, badgeCounts, Felicitation.nbr_min_tache);

    // Access the counts for each badge
    int felicitationCount = badgeCounts.getOrDefault(Badge.FELICITATION, 0);
    int goodCount = badgeCounts.getOrDefault(Badge.GOOD, 0);
    int veryGoodCount = badgeCounts.getOrDefault(Badge.VERYGOOD, 0);
    int excellentCount = badgeCounts.getOrDefault(Badge.EXCELLENT, 0);
    System.out.println("\n+FELICITATION "+felicitationCount+" GOOD : "+goodCount);*/

}

}
