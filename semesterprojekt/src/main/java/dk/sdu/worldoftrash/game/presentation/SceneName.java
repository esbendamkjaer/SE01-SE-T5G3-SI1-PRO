package dk.sdu.worldoftrash.game.presentation;

public enum SceneName {

    START_SCENE("/fxml/start.fxml"),
    GAME_SCENE("/fxml/game.fxml");

    private String fxmlPath;

    SceneName(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return this.fxmlPath;
    }
}
