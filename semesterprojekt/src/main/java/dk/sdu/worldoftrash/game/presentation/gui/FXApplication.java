package dk.sdu.worldoftrash.game.presentation.gui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class FXApplication extends Application {

    private SceneManager sceneManager;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.sceneManager = new SceneManager(primaryStage);

        primaryStage.setTitle("Titel");
        primaryStage.centerOnScreen();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang");

        createScene(SceneName.START_SCENE, resourceBundle);
        createScene(SceneName.GAME_SCENE, resourceBundle);

        sceneManager.changeScene(SceneName.START_SCENE);

        primaryStage.show();
    }

    public void createScene(SceneName sceneName, ResourceBundle resourceBundle) throws IOException {
        SceneInfo sceneInfo = new SceneInfo(sceneName.getFxmlPath(), sceneManager, resourceBundle, primaryStage);

        sceneManager.addScene(sceneName, sceneInfo);
    }

}
