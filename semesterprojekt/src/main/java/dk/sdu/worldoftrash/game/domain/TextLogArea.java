package dk.sdu.worldoftrash.game.domain;

import javafx.scene.control.TextArea;

public class TextLogArea implements IGameTextPrinter {

    private TextArea textLogArea;

    public TextLogArea (TextArea textLogArea) {
        this.textLogArea = textLogArea;
    }

    /**
     * Print given text to the textLogArea
     * @param text
     */
    public void printText(String text) {
        textLogArea.appendText(text + "\n\n");
    }

}
