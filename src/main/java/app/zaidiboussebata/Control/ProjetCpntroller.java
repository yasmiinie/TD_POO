package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.SimpleTache;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

import static app.zaidiboussebata.Control.LogInController.pseudo;
import static app.zaidiboussebata.Control.LogInController.recupererObjetFichier;

public class ProjetCpntroller {
    @FXML
    public ListView<String> liste ;
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
}
