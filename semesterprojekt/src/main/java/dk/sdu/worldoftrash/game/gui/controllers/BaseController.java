package dk.sdu.worldoftrash.game.gui.controllers;

import dk.sdu.worldoftrash.game.gui.SceneManager;

public class BaseController {

    private SceneManager sceneManager;

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
