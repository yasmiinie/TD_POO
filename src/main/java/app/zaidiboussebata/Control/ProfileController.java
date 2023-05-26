package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.zaidiboussebata.Control.LogInController.navigateTo;
import static app.zaidiboussebata.Control.LogInController.pseudo;
import static app.zaidiboussebata.Noyau.Creneau_libre.dureeMin;
import static app.zaidiboussebata.Noyau.Felicitation.nbr_min_tache;

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
    @FXML
    public Label nbTasksLabel;
    @FXML
    public Label nbTasksLabel1;
    @FXML
    public Label  progressLabel;

    @FXML
    public Label dailyLabel ;

    @FXML
    public TextField nbTasksField;
    @FXML
    public TextField nbTasksField1;
    @FXML
    public Label overLabel;
    @FXML
    public Label profLabel ;
    @FXML
    public Label sportLabel;
    @FXML
    public Label workLabel;
    @FXML
    public Label hobbyLabel;
    @FXML
    public Label  healthLabel;
    @FXML
    public Label studyLabel ;




    //--------------------------------------------------------------------------------
    @FXML
    public Button ProjectButton ;
    @FXML
    public  void navigateProject(ActionEvent event){

        navigateTo(ProjectButton,"/app/zaidiboussebata/ProjetPage.fxml","Tasks Page" , true) ;
    }
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

        Journee journee = new Journee();
        Felicitation felicitation = new Felicitation();
        CompletedTacheDay completedTacheDay = new CompletedTacheDay();
        pseudoLabel.setText(pseudo);
        List<CompletedTacheDay> mapCompletedDay = new ArrayList<>();

        List<HistoriquePlanning> historiquePlanList = Utilisateur.recupererObjetFichier(pseudo+"_historiquePlanning.ser" );
        Map<LocalDate, Integer> completedTasksByDay = new HashMap<>();
        completedTacheDay.calculateCompletedTasks(historiquePlanList, completedTasksByDay);
        completedTacheDay.printCompletedTacheDay(completedTasksByDay);
        Map<Badge, Integer> badgeCounts = new HashMap<>();
        felicitation.updateBadgeCounts(completedTasksByDay, badgeCounts, nbr_min_tache);
        congratsLabel.setText(String.valueOf(badgeCounts.getOrDefault(Badge.FELICITATION, 0)));
        goodLabel.setText(String.valueOf(badgeCounts.getOrDefault(Badge.GOOD, 0)));
        VgoodLabel.setText(String.valueOf(badgeCounts.getOrDefault(Badge.VERYGOOD, 0)));
        excelLabel.setText(String.valueOf(badgeCounts.getOrDefault(Badge.EXCELLENT,0)));
      //changer le nb de taches
        nbTasksLabel.setText(String.valueOf(nbr_min_tache));




        // rendement journalier



        dailyLabel.setText(String.valueOf(journee.rendementJournalier(LocalDate.now() , pseudo+"_historiquePlanning.ser")));

        //la duree de temps passée sur des taches d'une categorie


        Duration studiesDuration = null;
        Duration hobbyDuration = null;
        Duration workDuration = null;
        Duration sportDuration = null;
        Duration healthDuration = null;

        for (Map.Entry<Categorie, Duration> entry :journee.CategoryDuration(pseudo+"_historiquePlanning.ser" ).entrySet()) {
            Categorie category = entry.getKey();
            Duration totalDuration = entry.getValue();

            if (category == Categorie.STUDIES) {
                studiesDuration = totalDuration;
                // Perform operations specific to STUDIES category
            } else if (category == Categorie.HOBBY) {
                hobbyDuration = totalDuration;
                // Perform operations specific to HOBBY category
            } else if (category == Categorie.WORK) {
                workDuration = totalDuration;
                // Perform operations specific to WORK category
            } else if (category == Categorie.SPORT) {
                sportDuration = totalDuration;
                // Perform operations specific to SPORT category
            } else if (category == Categorie.HEALTH) {
                healthDuration = totalDuration;
                // Perform operations specific to HEALTH category
            }
            // Add more else-if conditions for other categories
        }

        if (studiesDuration != null) {
            studyLabel.setText(String.valueOf(studiesDuration.toHours() + ":" + studiesDuration.toMinutesPart()));

        } else {
            studyLabel.setText("0");
        }

        if (hobbyDuration != null) {
            hobbyLabel.setText(String.valueOf(hobbyDuration.toHours() + ":" + hobbyDuration.toMinutesPart()));

        } else {
            hobbyLabel.setText("0");

        }

        if (workDuration != null) {
            workLabel.setText(String.valueOf(workDuration.toHours() + ":" + workDuration.toMinutesPart() ));
        } else {
            workLabel.setText(String.valueOf("0"));
        }

        if (sportDuration != null) {
            sportLabel.setText(String.valueOf(sportDuration.toHours() + ":" + sportDuration.toMinutesPart()));
        } else {
            sportLabel.setText(String.valueOf("0"));
        }

        if (healthDuration != null) {
            healthLabel.setText(String.valueOf(healthDuration.toHours() + ":" + healthDuration.toMinutesPart() ));

        } else {
            healthLabel.setText("0");
        }



       List<Planning> dateList = journee.tacheOfDay(LocalDate.now(), pseudo+"_historiquePlanning.ser");

        // nombre de fois
        Etat etat = journee.getEtatGlobal(dateList);
        progressLabel.setText(String.valueOf( etat));
        overLabel.setText(String.valueOf(  completedTacheDay.countDaysWithCompletedTasks(completedTasksByDay , nbr_min_tache + 1)));

        // le jour le plus rentable

        if ( journee.bestDay( pseudo+"_historiquePlanning.ser",completedTasksByDay ) == null){
            profLabel.setText(String.valueOf( "No Day" ));
        }
        else {
            profLabel.setText(String.valueOf(journee.bestDay( pseudo+"_historiquePlanning.ser",completedTasksByDay) ));

        }
        //affichage de la list
        if(dateList.size() ==0 ) {System.out.print("Enter your pseudo: ");}





    }

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


    public void editnbTask(ActionEvent event){
        System.out.println(Integer.parseInt(nbTasksField.getText()));
        nbr_min_tache = Integer.parseInt(nbTasksField.getText());
        nbTasksLabel.setText(String.valueOf(nbr_min_tache));
    }

    public void editnbSlot(ActionEvent event){
        try {
            Duration duration = parseDuration(nbTasksField1.getText());
            dureeMin = duration ;
            nbTasksLabel1.setText(String.valueOf(duration));

            // Use the duration as needed
        } catch (IllegalArgumentException e) {
            // Handle invalid duration input
        }
    }

}
