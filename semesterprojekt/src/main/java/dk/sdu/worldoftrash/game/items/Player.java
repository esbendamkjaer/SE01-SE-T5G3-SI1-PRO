package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.Inventory;

public class Player extends Item {

    private Inventory inventory;

    public Player(Game game, String name) {
        super(game, name);
        inventory = new Inventory(10);
    }

    public Inventory getInventory() {
        return inventory;
    }
}

