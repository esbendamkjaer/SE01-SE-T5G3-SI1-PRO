package dk.sdu.worldoftrash.game.gui;

import javafx.scene.image.Image;

public class ImageIO {

    public static Image load(String dir) {
        return new Image(ImageIO.class.getResourceAsStream(dir));
    }

}
