package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.Creneau_libre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.GenericDeclaration;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static app.zaidiboussebata.Control.LogInController.*;

public class AddSlotController {

    @FXML
    public Label SlotErreur;
    @FXML
    public TextField startSlot;
    @FXML
    public TextField endSlot ;
    @FXML
    public DatePicker dateSlot;
    @FXML
    public Button addBut;

    private Duration parseDuration(String durationText) {
        // Implement your custom parsing logic here
        // Example: Parse duration in the format "HH:MM"
        String[] parts = durationText.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return Duration.ofHours(hours).plusMinutes(minutes);
    }

//------------------------------------------------------------------------------------
    /**
     *
     * @param text le text pour convertir en Local time
     * @return
     *
     */
    public LocalTime toLocalTime(String text){

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(text, formatter);
            System.out.println(localTime);
          return localTime ;
        }
        catch (Exception e){
            return null;
        }
    }

//------------------------------------------------------------------------------------------------------
    @FXML
    protected void SlotClick() {
        SlotErreur.setText("The duration is less than minimum required , try again !");
    }

    //------------------------------------------------------------------------------------------------------
    public void addSlot(ActionEvent event ){
        Creneau_libre creneau = new  Creneau_libre();

        List<Creneau_libre> CreneauList = recupererObjetFichier(pseudo+"_creneau.ser");

        if (creneau.createrCreneauLibre(pseudo+"_creneau.ser",toLocalTime(startSlot.getText()),toLocalTime(endSlot.getText()),dateSlot.getValue(),CreneauList)){
           // on ferme la fenetre
           Stage currentStage = (Stage) addBut.getScene().getWindow();
           currentStage.close();
       }
       else{
           SlotClick();
       }
    }


}
