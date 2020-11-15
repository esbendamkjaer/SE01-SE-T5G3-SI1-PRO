package dk.sdu.worldoftrash.game.gui;

import javafx.animation.AnimationTimer;

public class GameAnimationTimer extends AnimationTimer {

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
}
