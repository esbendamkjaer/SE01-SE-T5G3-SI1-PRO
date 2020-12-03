package dk.sdu.worldoftrash.game.domain;

import javafx.scene.image.Image;

public class Img {

    /**
     * Load an image resource by given path.
     * @param path Path to the image.
     * @return Image object.
     */
    public static Image load(String path) {
        return new Image(Img.class.getResourceAsStream(path));
    }

}
