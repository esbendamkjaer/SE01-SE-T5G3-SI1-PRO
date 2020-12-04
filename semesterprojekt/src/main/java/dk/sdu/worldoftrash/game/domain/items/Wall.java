package dk.sdu.worldoftrash.game.domain.items;

import dk.sdu.worldoftrash.game.domain.Game;
import javafx.scene.canvas.GraphicsContext;

public class Wall extends Item {

    public Wall(Game game, double x, double y, double width, double height) {
        super(game, null);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }
/*
    @Override
    public void render(GraphicsContext gc) {
        super.render(gc); gc.fillRect(getX(),getY(),getWidth(),getHeight());
    }

 */
}
