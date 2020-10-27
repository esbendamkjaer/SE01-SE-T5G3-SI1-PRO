package worldofzuul.items;

import worldofzuul.Inventory;

public class Player extends Item {

    private Inventory inventory;

    public Player(String name) {
        super(name);
        inventory = new Inventory(10);
    }

    public Inventory getInventory() {
        return inventory;
    }
}

