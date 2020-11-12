package dk.sdu.worldoftrash.game.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Frame extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Titel");

        Group root = new Group();

        Scene scene = new Scene(root, 300, 400);

        stage.setScene(scene);
        stage.show();

    }

}
