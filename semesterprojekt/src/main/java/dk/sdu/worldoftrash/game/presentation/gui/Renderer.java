package dk.sdu.worldoftrash.game.presentation.gui;

import dk.sdu.worldoftrash.game.domain.items.Item;
import dk.sdu.worldoftrash.game.domain.items.Player;
import dk.sdu.worldoftrash.game.domain.rooms.Room;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {

    private Canvas canvas;
    private GraphicsContext context;

    private double width, height;

    public Renderer(Canvas canvas) {
        this.canvas = canvas;
        width = canvas.getWidth();
        height = canvas.getHeight();
        this.context = canvas.getGraphicsContext2D();

    }

    /**
     * Render given room on canvas.
     * @param room Room to render.
     * @param player Player to render on top of room.
     */
    public void render(Room room, Player player) {
        context.save();

        if (room.getBackground() != null) {
            context.drawImage(room.getBackground(), 0,0, width, height);
        }

        for (Item item : room.getItems()) {
            if (item.getImage() != null) {
                renderItem(item);
            }
        }

        player.getSpriteAnimation().drawImage(context, player.getX(), player.getY(), player.getWidth(), player.getHeight());
        player.render(context);
        //renderItem(player);

        context.restore();
    }

    /**
     * Render a given item item on the canvas.
     * @param item Item to render.
     */
    public void renderItem(Item item) {
        Point2D pos = item.getPosition();
        context.drawImage(
                item.getImage(),
                pos.getX() + 0.5 * (item.getWidth() - item.getImage().getWidth() * item.getScale()),
                pos.getY() + 0.5 * (item.getHeight() - item.getImage().getHeight() * item.getScale()),
                item.getImage().getWidth() * item.getScale(),
                item.getImage().getHeight() * item.getScale()
        );

        item.render(context);
    }

    /**
     * Clear canvas by
     */
    public void prepare(){
        context.setFill( new Color(0.68, 0.68, 0.68, 1.0) );
        context.fillRect(0,0, width, height);
    }
}
