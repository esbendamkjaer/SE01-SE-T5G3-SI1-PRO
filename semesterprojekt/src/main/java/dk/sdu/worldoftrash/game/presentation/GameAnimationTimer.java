package dk.sdu.worldoftrash.game.presentation;

import javafx.animation.AnimationTimer;

public abstract class GameAnimationTimer extends AnimationTimer {

    private long lastFrameTimeNs;

    @Override
    public void handle(long now) {

        double secondsSinceLastFrame = ((now - lastFrameTimeNs) / 1e9);
        lastFrameTimeNs = now;
        update(secondsSinceLastFrame);

    }

    public abstract void update(double secondsSinceLastFrame);
}
