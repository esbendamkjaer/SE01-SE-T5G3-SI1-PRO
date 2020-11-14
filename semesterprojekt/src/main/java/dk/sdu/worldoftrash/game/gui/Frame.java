package dk.sdu.worldoftrash.game.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Frame extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Titel");

        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"), resourceBundle);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

}
