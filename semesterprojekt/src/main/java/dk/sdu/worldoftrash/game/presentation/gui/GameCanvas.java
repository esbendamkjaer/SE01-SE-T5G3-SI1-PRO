package dk.sdu.worldoftrash.game.presentation.gui;

import javafx.scene.canvas.Canvas;

public class GameCanvas extends Canvas {
    private double scale;

    public GameCanvas() {
        scale = 1;
    }

    @Override
    public double prefHeight(double width) {
        return 10000;
    }

    @Override
    public double prefWidth(double height) {
        return 10000;
    }

    @Override
    public double minHeight(double width) {
        return 100;
    }

    @Override
    public double minWidth(double height) {
        return 100;
    }

    @Override
    public double maxWidth(double height) {
        return Double.MAX_VALUE;
    }

    @Override
    public double maxHeight(double width) {
        return Double.MAX_VALUE;
    }

    @Override
    public void resize(double width, double height) {
        double min = Math.min(width, height);
        super.setWidth(min);
        super.setHeight(min);
        getGraphicsContext2D().scale(1/scale, 1/scale);
        scale = min / 928;
        getGraphicsContext2D().scale(scale, scale);
    }

    @Override
    public boolean isResizable() {
        return true;
    }
}
