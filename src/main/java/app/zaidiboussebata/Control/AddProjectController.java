package app.zaidiboussebata.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static app.zaidiboussebata.Control.LogInController.navigateTo;

public class AddProjectController {

    @FXML
    public Button addBut ;
    @FXML
    public Button acceptBut ;
    @FXML
    public Button refuseBut ;
    @FXML
    public TextArea description;

    @FXML
    public TextField projectName;

//_---------_---------_---------_---------_---------_---------_---------_---------
     public void addProjet(ActionEvent event){
         navigateTo( addBut , "/app/zaidiboussebata/MoreTasks.fxml", "Schedule" , false);


     }

    /** accepter de planifier encore une autre tache
     *
     * @param event
     */
    public void acceptClick(ActionEvent event) {
        navigateTo( acceptBut , "/app/zaidiboussebata/addTasksProjet.fxml", "Schedule" , false);

    }

    public void refuseClick(ActionEvent event) {
        navigateTo( refuseBut , "/app/zaidiboussebata/ProjetPage.fxml", "Schedule" , false);

    }
}
