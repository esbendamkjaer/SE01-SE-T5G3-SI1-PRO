package dk.sdu.worldoftrash.game.gui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class FXApplication extends Application {

    private SceneManager sceneManager;

    @Override
    public void start(Stage stage) throws IOException {

        sceneManager = new SceneManager(stage);

        stage.setTitle("Titel");

        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang");

        createScene(SceneName.START_SCENE, resourceBundle);
        createScene(SceneName.GAME_SCENE, resourceBundle);

        sceneManager.changeScene(SceneName.START_SCENE);

        stage.show();
    }

    public void createScene(SceneName sceneName, ResourceBundle resourceBundle) throws IOException {
        SceneInfo sceneInfo = new SceneInfo(sceneName.getFxmlPath(), sceneManager, resourceBundle);

        sceneManager.addScene(sceneName, sceneInfo);
    }

}
