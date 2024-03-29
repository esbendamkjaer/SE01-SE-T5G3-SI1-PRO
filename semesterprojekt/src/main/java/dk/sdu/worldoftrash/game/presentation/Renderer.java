package dk.sdu.worldoftrash.game.presentation;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.Img;
import dk.sdu.worldoftrash.game.domain.SpriteAnimation;
import dk.sdu.worldoftrash.game.domain.items.*;
import dk.sdu.worldoftrash.game.domain.Room;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

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
     * @param game Game object to render.
     */
    public void render(Game game) {
        context.save();

        renderRoom(game.getCurrentRoom());

        renderPlayer(game.getPlayer(), game);

        context.restore();
    }

    private void drawAnimatedSprite(SpriteAnimation animation, Item item) {
        Rectangle subImageRect = animation.getSubImageRect();
        context.drawImage(animation.getImage(),
                subImageRect.getX(), subImageRect.getY(), subImageRect.getWidth(), subImageRect.getHeight(),
                item.getX(), item.getY(), item.getWidth(), item.getHeight());
    }

    private void renderPlayer(Player player, Game game) {
        drawAnimatedSprite(player.getSpriteAnimation(), player);

        List<Interactable> colliding = game.getCollisionsWithPlayer(Interactable.class);

        if (colliding.isEmpty()) return;

        Image icon = Img.load("/images/icons/x_icon.png");

        for (Interactable interactable : colliding) {

            if (interactable instanceof Door) {
                Door door = (Door) interactable;

                if (door.getOtherSide().getPlace().isLocked()) {
                    icon = Img.load("/images/icons/lock-solid.png");
                }
            }
        }

        double width = 32, height = 32;
        double iconWidth = 20, iconHeight = 20;

        Image bg = Img.load("/images/icons/icon_bg.png");

        double x = 0;
        if (game.getWidth() - player.getX() - player.getWidth() < width) {
            x = player.getX() - width;
        } else {
            x = player.getX() + player.getWidth();
        }
        double y = player.getY();

        context.drawImage(bg, x, y, width, height);
        context.drawImage(icon, x + 0.5 * (width - iconWidth), y + 0.5 * (height - iconHeight), iconWidth, iconHeight);
    }

    private void renderRoom(Room room) {
        if (room.getBackground() != null) {
            context.drawImage(room.getBackground(), 0,0, width, height);
        }

        for (Item item : room.getItems()) {
            renderItem(item);
        }
    }

    /**
     * Render a given item item on the canvas.
     * @param item Item to render.
     */
    public void renderItem(Item item) {

        if (item.getImage() != null) {
            Point2D pos = item.getPosition();
            context.drawImage(
                    item.getImage(),
                    pos.getX() + 0.5 * (item.getWidth() - item.getImage().getWidth() * item.getScale()),
                    pos.getY() + 0.5 * (item.getHeight() - item.getImage().getHeight() * item.getScale()),
                    item.getImage().getWidth() * item.getScale(),
                    item.getImage().getHeight() * item.getScale()
            );
        }

    }

    /**
     * Clear canvas by
     */
    public void prepare(){
        context.setFill( new Color(0.68, 0.68, 0.68, 1.0) );
        context.fillRect(0,0, width, height);
    }
}
