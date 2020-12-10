package dk.sdu.worldoftrash.game.presentation.views;

import dk.sdu.worldoftrash.game.presentation.SceneManager;

public class BaseView {
    private SceneManager sceneManager;

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
