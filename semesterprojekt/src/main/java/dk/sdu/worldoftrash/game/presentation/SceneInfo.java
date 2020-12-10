package dk.sdu.worldoftrash.game.presentation;

import dk.sdu.worldoftrash.game.presentation.views.BaseView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneInfo {

    private Parent root;
    private SceneManager sceneManager;
    private String resource;
    private Stage stage;

    public SceneInfo(String resource, SceneManager sceneManager, Stage stage) {
        this.resource = resource;
        this.sceneManager = sceneManager;
        this.stage = stage;
    }

    /**
     * Return root of scene graph.
     * @return
     */
    public Parent getRoot() {
        if (root == null) {
            root = loadSceneRoot(resource);
        }

        return root;
    }

    /**
     * Load root of scene.
     * @param resource
     * @return Root of scene graph.
     */
    private Parent loadSceneRoot(String resource) {
        Parent sceneRoot = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));

        try {
            sceneRoot = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BaseView controller = fxmlLoader.getController();
        controller.setSceneManager(sceneManager);

        return sceneRoot;
    }

}
