package dk.sdu.worldoftrash.game;

import javafx.scene.control.TextArea;

public class TextLogArea {

    private TextArea textLogArea;

    public TextLogArea (TextArea textLogArea) {
        this.textLogArea = textLogArea;
    }

    public void printText(String text) {
        textLogArea.setText(textLogArea.getText() + text);
    }
}
