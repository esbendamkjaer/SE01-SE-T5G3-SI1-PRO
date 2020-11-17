package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.Inventory;
import dk.sdu.worldoftrash.game.gui.KeyPolling;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Item {

    private Inventory inventory;
    private KeyPolling keys;

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

        if (keys.isDown(KeyCode.E)) {
            throw new UnsupportedOperationException();
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
}

