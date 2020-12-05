package dk.sdu.worldoftrash.game.presentation;

import javafx.scene.media.AudioClip;

public class Sound {

    public static void playSound(String path) {
        AudioClip a = new AudioClip(Sound.class.getResource(path).toString());
        a.play();
    }

}
