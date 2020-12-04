package dk.sdu.worldoftrash.game.presentation;

import dk.sdu.worldoftrash.game.domain.KeyPolling;
import javafx.scene.Scene;
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
     * @param sceneInfo SceneInfo to add.
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

            Scene currScene = stage.getScene();
            Scene scene = currScene == null
                    ? new Scene(sceneInfo.getRoot())
                    : new Scene(sceneInfo.getRoot(), currScene.getWidth(), currScene.getHeight());

            KeyPolling.getInstance().pollScene(scene);

            stage.setScene(scene);
            stage.show();
        } else {
            System.err.printf("No such scene '%s'.\n", sceneName);
        }
    }

    public Stage getStage() {
        return stage;
    }
}
