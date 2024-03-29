package dk.sdu.worldoftrash.game.presentation;

import javafx.application.Application;
import javafx.stage.Stage;

public class FXApplication extends Application {

    private SceneManager sceneManager;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.sceneManager = new SceneManager(primaryStage);

        primaryStage.setTitle("World of Trash");
        primaryStage.centerOnScreen();

        createScene(SceneName.START_SCENE);
        createScene(SceneName.GAME_SCENE);

        sceneManager.changeScene(SceneName.START_SCENE);

        primaryStage.show();
    }

    public void createScene(SceneName sceneName) {
        SceneInfo sceneInfo = new SceneInfo(sceneName.getFxmlPath(), sceneManager, primaryStage);

        sceneManager.addScene(sceneName, sceneInfo);
    }

}
