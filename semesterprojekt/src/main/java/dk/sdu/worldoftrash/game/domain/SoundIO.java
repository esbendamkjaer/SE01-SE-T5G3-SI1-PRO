package dk.sdu.worldoftrash.game.domain;

import javafx.scene.media.AudioClip;

public class SoundIO {

    public static void playSound(String path) {
        AudioClip a = new AudioClip(SoundIO.class.getResource(path).toString());
        a.play();
    }

}
