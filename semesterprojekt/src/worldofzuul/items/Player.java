package worldofzuul.items;

import worldofzuul.Game;
import worldofzuul.Inventory;

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

