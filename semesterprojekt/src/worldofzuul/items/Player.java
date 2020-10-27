package worldofzuul.items;

import worldofzuul.Inventory;

public class Player extends Item {

    private Inventory inventory;

    public Player() {
        inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }
}

