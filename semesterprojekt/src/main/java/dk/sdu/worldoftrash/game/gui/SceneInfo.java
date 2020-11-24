package dk.sdu.worldoftrash.game.gui;

import dk.sdu.worldoftrash.game.gui.controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class SceneInfo {

    private Parent root;
    private SceneManager sceneManager;
    private String resource;
    private ResourceBundle resourceBundle;
    private Stage stage;

    public SceneInfo(String resource, SceneManager sceneManager, ResourceBundle resourceBundle, Stage stage) {
        this.resource = resource;
        this.sceneManager = sceneManager;
        this.resourceBundle = resourceBundle;
        this.stage = stage;
    }

    public Parent getRoot() {
        if (root == null) {
            root = loadScene(resource);
        }

        return root;
    }

    private Parent loadScene(String resource) {
        Parent sceneRoot = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource), resourceBundle);

        try {
            sceneRoot = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BaseController controller = fxmlLoader.getController();
        controller.setSceneManager(sceneManager);
        controller.setStage(stage);

        return sceneRoot;
    }

}
