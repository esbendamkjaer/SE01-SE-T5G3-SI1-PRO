package dk.sdu.worldoftrash.game.presentation.gui;

import javafx.scene.image.Image;

public class ImageIO {

    /**
     * Load an image resource by given path.
     * @param path Path to the image.
     * @return Image object.
     */
    public static Image load(String path) {
        return new Image(ImageIO.class.getResourceAsStream(path));
    }

}
