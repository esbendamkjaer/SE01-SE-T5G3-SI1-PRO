package dk.sdu.worldoftrash.game.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class SceneManager {

    private Stage stage;

    private HashMap<String, Scene> scenes = new HashMap<String, Scene>();

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * Add a scene to Map of scenes.
     * @param sceneName Name of the scene being added.
     * @param scene Scene to add.
     */
    public void addScene(String sceneName, Scene scene) {
        scenes.put(sceneName, scene);
    }

    /**
     * Change the current scene to scene by given scene name.
     * @param sceneName Name of scene to switch to.
     */
    public void changeScene(String sceneName) {
        if (scenes.containsKey(sceneName)) {
            Scene scene = scenes.get(sceneName);

            stage.setScene(scene);
            stage.show();
        } else {
            System.err.printf("No such scene '%s'.\n", sceneName);
        }
    }


}
