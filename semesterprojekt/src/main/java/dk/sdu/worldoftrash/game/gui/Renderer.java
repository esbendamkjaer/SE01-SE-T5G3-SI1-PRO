package dk.sdu.worldoftrash.game.gui;

import dk.sdu.worldoftrash.game.items.Item;
import dk.sdu.worldoftrash.game.items.Player;
import dk.sdu.worldoftrash.game.rooms.Room;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {

    private Canvas canvas;
    private GraphicsContext context;

    public Renderer(Canvas canvas) {

        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();

    }

    public void render(Room room, Player player) {
        context.save();

        if (room.getBackground() != null) {
            context.drawImage(room.getBackground(), 0,0);
        }

        for (Item item : room.getItems()) {
            if (item.getImage() != null) {
                Point2D pos = item.getPosition();
                context.drawImage(
                        item.getImage(),
                        pos.getX(),
                        pos.getY(),
                        item.getWidth(),
                        item.getHeight()
                );
            }
        }

        Point2D pos = player.getPosition();
        context.drawImage(
                player.getImage(),
                pos.getX(),
                pos.getY(),
                player.getWidth(),
                player.getHeight()
        );

        context.restore();
    }

    public void prepare(){
        context.setFill( new Color(0.68, 0.68, 0.68, 1.0) );
        context.fillRect(0,0, canvas.getWidth(),canvas.getHeight());
    }
}
