package dk.sdu.worldoftrash.game.presentation;

import javafx.animation.AnimationTimer;

public abstract class GameAnimationTimer extends AnimationTimer {

    private long lastFrameTimeNanos;

    private boolean paused;
    private boolean active;
    private boolean pauseScheduled;
    private boolean playScheduled;
    private boolean restartScheduled;

    public void pause() {
        if (!isPaused()) {
            pauseScheduled = true;
        }
    }

    public void play() {
        if (isPaused()) {
            playScheduled = true;
        }
    }

    @Override
    public void start() {
        super.start();
        setActive(true);
        setRestartScheduled(true);

    }

    @Override
    public void handle(long now) {

        if (isPauseScheduled()) {
            setPaused(true);
            setPaused(true);
            setPauseScheduled(false);
        }

        if (isPlayScheduled()) {
            setPaused(false);
            setPlayScheduled(false);
        }

        if (isRestartScheduled()) {
            setPaused(false);
            setRestartScheduled(false);
        }

        if(!isPaused()) {
            float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
            lastFrameTimeNanos = now;
            tick(secondsSinceLastFrame);
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isRestartScheduled() {
        return restartScheduled;
    }

    public void setRestartScheduled(boolean restartScheduled) {
        this.restartScheduled = restartScheduled;
    }

    public boolean isPauseScheduled() {
        return pauseScheduled;
    }

    public void setPauseScheduled(boolean pauseScheduled) {
        this.pauseScheduled = pauseScheduled;
    }

    public boolean isPlayScheduled() {
        return playScheduled;
    }

    public void setPlayScheduled(boolean playScheduled) {
        this.playScheduled = playScheduled;
    }

    public abstract void tick(double secondsSinceLastFrame);
}
