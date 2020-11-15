package dk.sdu.worldoftrash.game.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Renderer {

    private Canvas canvas;
    private GraphicsContext context;

    public Renderer(Canvas canvas) {

        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();

    }

    public void render() {

    }

    public void update() {

    }

    private void drawBackground(GraphicsContext g) {

    }

}
