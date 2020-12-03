package dk.sdu.worldoftrash.game.domain.items;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.Inventory;
import dk.sdu.worldoftrash.game.domain.KeyPolling;
import dk.sdu.worldoftrash.game.domain.sprite.SpriteAnimation;
import javafx.scene.input.KeyCode;

import java.util.List;

public class Player extends Item {

    private Inventory inventory;
    private KeyPolling keys;
    private boolean interact_typed;

    // Speed in pixels per second.
    private float speed = 400f;
    private float velX;
    private float velY;

    private SpriteAnimation spriteAnimation;

    public Player(Game game, String name) {
        super(game, name);
        inventory = new Inventory(20);
        keys = KeyPolling.getInstance();

        spriteAnimation = new SpriteAnimation("/images/player/player_spritesheet.png", 32, 46, 9);
        spriteAnimation.setCols(5);

        setWidth(spriteAnimation.getColWidth() * getScale());
        setHeight(spriteAnimation.getRowHeight() * getScale());

        setScale(1.25f);
    }

    public SpriteAnimation getSpriteAnimation() {
        return spriteAnimation;
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        spriteAnimation.tick(delta);

        handleInteractables();
        
        if (keys.isDown(KeyCode.UP) || keys.isDown(KeyCode.W)) {
            velY += -speed;
            spriteAnimation.setRow(2);
        }

        if (keys.isDown(KeyCode.DOWN) || keys.isDown(KeyCode.S)) {
            velY += speed;
            spriteAnimation.setRow(4);
        }

        if (keys.isDown(KeyCode.LEFT) || keys.isDown(KeyCode.A)) {
            velX -= speed;
            spriteAnimation.setRow(3);
        }

        if (keys.isDown(KeyCode.RIGHT) || keys.isDown(KeyCode.D)) {
            velX += speed;
            spriteAnimation.setRow(1);
        }

        if (velX == 0 && velY == 0) {
            spriteAnimation.setRow(0);
        }

        handleCollisions(delta);

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

        this.velX = 0;
        this.velY = 0;
    }

    private void handleInteractables() {
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
    }

    private void handleCollisions(double delta) {
        setX(getX() + velX * delta);

        for (Wall wall : getGame().getCollisionsWithPlayer(Wall.class)) {
            if (velX > 0) {
                setX(wall.getX() - getWidth());
            } else if (velX < 0) {
                setX(wall.getX() + wall.getWidth());
            }
        }

        setY(getY() + velY * delta);

        for (Wall wall : getGame().getCollisionsWithPlayer(Wall.class)) {
            if (velY > 0) {
                setY(wall.getY() - getHeight());
            } else if (velY < 0) {
                setY(wall.getY() + wall.getHeight());
            }
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
        } else {
            getGame().getTextPrinter().printText("You do not have sufficient space in your inventory.");
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}

