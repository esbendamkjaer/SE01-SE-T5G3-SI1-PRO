package dk.sdu.worldoftrash.game.gui.controllers;

import dk.sdu.worldoftrash.game.gui.SceneManager;
import javafx.stage.Stage;

public class BaseController {

    private SceneManager sceneManager;
    private Stage stage;

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }
}
