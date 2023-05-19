package app.zaidiboussebata.Control;

import app.zaidiboussebata.Noyau.Categorie;
import app.zaidiboussebata.Noyau.SimpleTache;
import app.zaidiboussebata.Noyau.Tache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import static app.zaidiboussebata.Control.LogInController.navigateTo;
import static app.zaidiboussebata.Control.LogInController.*;

public class TacheController {
    @FXML
    private static ListView<SimpleTache> tacheliste;

    public static void initialize() {
        List<SimpleTache> objetList = recupererObjetFichier(FICHIER_TACHE);
        ObservableList<SimpleTache> observableList = FXCollections.observableArrayList(objetList);
        tacheliste.setItems(observableList);
    }
    int type = 1 ; //par defaut cest une tache simple
    int categorie = 0 ; //par defaut cest une other

    int priorite = 1 ; //par defaut cest une High

    @FXML
    private Button ajouterButton;

    @FXML
    private  Button createButton;
    @FXML
    public TextField tacheName;
    @FXML
    public TextField tacheDuration;
    @FXML
    public DatePicker tacheDDL;

    @FXML
    public MenuBar typeMenu;

    // ----------------------------Les priorites---------------------------

    public void HighClicked(){
        priorite = 1;
    }

    public void MediumClicked(){
        priorite = 2;
    }
    public void LowClicked(){
        priorite = 3;
    }
    // ----------------------------Les categories---------------------------

    public void OtherClicked(){
        categorie = 0;
    }
    public void StudiesClicked(){
        categorie = 1;
    }
    public void HobbyClicked(){
        categorie = 2;
    }
    public void WorkClicked(){
        categorie = 3;
    }
    public void SportClicked(){
        categorie = 4;
    }
    public void HealthClicked(){
        categorie = 5;
    }

    public void ajouterTache(){
        navigateTo(ajouterButton,"/app/zaidiboussebata/TacheForm.fxml","add a task",false);
    }
   public void simpleButtonClicked(){
       type = 1;

   }
   public void decomposableButtonClicked(){
       type = 2;

    }
   public void addtoliste(){
     //  navigateTo(ajouterButton,"/app/zaidiboussebata/TachePage.fxml","add a task",true);
     //  tacheliste.getItems().add(tacheName.getText());
     //---------------------------------------------------------------------------------

    //   SimpleTache newTache = new SimpleTache();  // Create a new Tache object

    //   ObservableList<SimpleTache> observableList = tacheliste.getItems();  // Retrieve the ObservableList
    //   observableList.add(newTache);
       System.out.println( tacheDDL.getValue());
       System.out.println( tacheDuration.getText());
       System.out.println( tacheName.getText());
       System.out.println( type);
       System.out.println(categorie);
       System.out.println(priorite);
    }
}
