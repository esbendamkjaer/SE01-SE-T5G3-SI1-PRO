package dk.sdu.worldoftrash.game.gui;

import dk.sdu.worldoftrash.game.gui.controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ResourceBundle;

public class SceneInfo {

    private Scene scene;
    private SceneManager sceneManager;
    private String resource;
    private ResourceBundle resourceBundle;

    public SceneInfo(String resource, SceneManager sceneManager, ResourceBundle resourceBundle) {
        this.resource = resource;
        this.sceneManager = sceneManager;
        this.resourceBundle = resourceBundle;
    }

    public Scene getScene() {
        if (scene == null) {
            scene = loadScene(resource);
        }

        return scene;
    }

    private Scene loadScene(String resource) {
        Parent sceneRoot = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource), resourceBundle);

        try {
            sceneRoot = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BaseController controller = fxmlLoader.getController();
        controller.setSceneManager(sceneManager);

        return new Scene(sceneRoot);
    }

}
