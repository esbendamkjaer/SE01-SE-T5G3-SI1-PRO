package dk.sdu.worldoftrash.game.gui;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {

    private Stage stage;

    private Map<SceneName, SceneInfo> scenes;

    public SceneManager(Stage stage) {
        this.scenes = new HashMap<>();
        this.stage = stage;
    }

    /**
     * Add a scene to Map of scenes.
     * @param sceneName Name of the scene being added.
     * @param scene Scene to add.
     */
    public void addScene(SceneName sceneName, SceneInfo sceneInfo) {
        scenes.put(sceneName, sceneInfo);
    }

    /**
     * Change the current scene to scene by given scene name.
     * @param sceneName Name of scene to switch to.
     */
    public void changeScene(SceneName sceneName) {
        if (scenes.containsKey(sceneName)) {
            SceneInfo sceneInfo = scenes.get(sceneName);

            stage.setScene(sceneInfo.getScene());
            stage.show();
        } else {
            System.err.printf("No such scene '%s'.\n", sceneName);
        }
    }


}
