package app.zaidiboussebata.Control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    /**
     * Permet d'aller d'une page a une autre
     * @throws IOException
     */
    public void navigateToAnotherPage() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/zaidiboussebata/Calendrier-view.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Home Page");

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Show the new stage
            stage.show();

            // Close the current stage (optional)
          Stage currentStage = (Stage) helloButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}