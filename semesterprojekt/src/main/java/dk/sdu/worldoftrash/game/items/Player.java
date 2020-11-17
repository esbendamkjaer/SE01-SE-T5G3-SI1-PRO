package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.Inventory;
import dk.sdu.worldoftrash.game.gui.KeyPolling;
import dk.sdu.worldoftrash.game.items.npcs.Interactable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.List;

public class Player extends Item {

    private Inventory inventory;
    private KeyPolling keys;
    private boolean typed;

    public Player(Game game, String name) {
        super(game, name);
        inventory = new Inventory(10);
        keys = KeyPolling.getInstance();
        setImage(new Image(getClass().getResourceAsStream("/images/player.png")));
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        List<Item> colliding = getGame().getCollisionsWithPlayer();

        for (Item item : colliding) {

            if (item instanceof Interactable) {
                if (keys.isDown(KeyCode.X) && !typed) {

                    Interactable interactable = (Interactable) item;
                    interactable.interact(this);

                    typed = true;
                } else {
                    typed = false;
                }
            }

        }

        if (keys.isDown(KeyCode.UP)) {
            setPosition(getPosition().add(0, -10));
        }

        if (keys.isDown(KeyCode.DOWN)) {
            setPosition(getPosition().add(0, 10));
        }

        if (keys.isDown(KeyCode.LEFT)) {
            setPosition(getPosition().add(-10, 0));
        }

        if (keys.isDown(KeyCode.RIGHT)) {
            setPosition(getPosition().add(10, 0));
        }

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

    public void pickup(Item item) {
        Pickupable pickupable = (Pickupable) item;

        if (!pickupable.pickup()) {
            return;
        }

        if (getInventory().storeItem((Item) pickupable)) {
            getGame().getCurrentRoom().removeItem(item);
            System.out.println("You picked up " + item.getName());
        } else {
            System.out.println("You do not have sufficient space in your inventory.");
        }
    }
}

