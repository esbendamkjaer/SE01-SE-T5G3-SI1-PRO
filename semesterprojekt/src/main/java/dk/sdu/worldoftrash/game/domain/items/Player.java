package dk.sdu.worldoftrash.game.domain.items;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.Inventory;
import dk.sdu.worldoftrash.game.presentation.gui.ImageIO;
import dk.sdu.worldoftrash.game.presentation.gui.KeyPolling;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.List;

public class Player extends Item {

    private Inventory inventory;
    private KeyPolling keys;
    private boolean interact_typed;

    // Speed in pixels per second.
    private float speed = 600f;

    private Image left, right, front, back;

    public Player(Game game, String name) {
        super(game, name);
        inventory = new Inventory(10);
        keys = KeyPolling.getInstance();

        left = ImageIO.load("/images/player/player_left.png");
        right = ImageIO.load("/images/player/player_right.png");
        front = ImageIO.load("/images/player/player_front.png");
        back = ImageIO.load("/images/player/player_back.png");

        setImage(front);
        fitToImage();

        setScale(1.25f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        List<Item> colliding = getGame().getCollisionsWithPlayer();

        for (Item item : colliding) {

            if (item instanceof Interactable) {
                if (keys.isDown(KeyCode.X)) {
                    if (!interact_typed) {
                        Interactable interactable = (Interactable) item;
                        interactable.interact(this);

                        interact_typed = true;
                    }
                } else {
                    interact_typed = false;
                }
            }

        }

        Point2D newPos = getPosition();

        boolean moved = false;
        if (keys.isDown(KeyCode.UP) || keys.isDown(KeyCode.W)) {
            newPos = newPos.add(0, -speed * delta);
            setImage(back);
        }

        if (keys.isDown(KeyCode.DOWN) || keys.isDown(KeyCode.S)) {
            newPos = newPos.add(0, speed * delta);
            setImage(front);
        }

        if (keys.isDown(KeyCode.LEFT) || keys.isDown(KeyCode.A)) {
            newPos = newPos.add(-speed * delta, 0);
            setImage(left);
        }

        if (keys.isDown(KeyCode.RIGHT) || keys.isDown(KeyCode.D)) {
            newPos = newPos.add(speed * delta, 0);
            setImage(right);
        }

        if (newPos.equals(getPosition())) {
            setImage(front);
        }

        fitToImage();

        setPosition(newPos);

        if (getY() < 0) {
            setY(0);
        } else if (getY() + getHeight() > getGame().getHeight()) {
            setY(getGame().getHeight() - getHeight());
        }

        if (getX() < 0) {
            setX(0);
        } else if (getX() + getWidth() > getGame().getWidth()) {
            setX(getGame().getWidth() - getWidth());
        }
    }

    /**
     * Pickup an item from the current room.
     * @param item Item from current room to pickup.
     */
    public void pickup(Item item) {
        Pickupable pickupable = (Pickupable) item;

        if (!pickupable.pickup()) {
            return;
        }

        if (getInventory().storeItem((Item) pickupable)) {
            getGame().getCurrentRoom().removeItem(item);
            getGame().getTextLogArea().printText("You picked up " + item.getName());
        } else {
            getGame().getTextLogArea().printText("You do not have sufficient space in your inventory.");
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

}

