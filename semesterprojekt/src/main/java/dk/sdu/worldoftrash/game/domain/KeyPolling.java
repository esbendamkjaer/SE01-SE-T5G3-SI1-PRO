package dk.sdu.worldoftrash.game.domain;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class KeyPolling {

    private static Scene scene;
    private static final Set<KeyCode> keysCurrentlyDown = new HashSet<>();

    private KeyPolling() {
    }

    public static KeyPolling getInstance() {
        return new KeyPolling();
    }

    /**
     * Clear state of keys.
     */
    private void clearKeys() {
        keysCurrentlyDown.clear();
    }

    /**
     * Remove key handlers.
     */
    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            KeyPolling.scene.setOnKeyPressed(null);
            KeyPolling.scene.setOnKeyReleased(null);
        }
    }

    /**
     * Set scene to handle key presses.
     * @param scene Scene to handle key presses on.
     */
    public void setScene(Scene scene) {
        clearKeys();
        removeCurrentKeyHandlers();

        KeyPolling.scene = scene;

        KeyPolling.scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            keysCurrentlyDown.add(event.getCode());
        });

        KeyPolling.scene.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            keysCurrentlyDown.remove(event.getCode());
        });
    }

    /**
     * Check state of key specified by given KeyCode.
     * @param keyCode KeyCode of key.
     * @return True if key is currently pressed down.
     */
    public boolean isDown(KeyCode keyCode) {
        return keysCurrentlyDown.contains(keyCode);
    }
}
