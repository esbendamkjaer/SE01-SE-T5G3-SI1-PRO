package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.Inventory;
import dk.sdu.worldoftrash.game.items.Item;


public abstract class NPC extends Item {

    private String dialogue;
    private Inventory inventory;

    public NPC(Game game, String name, String dialogue, Inventory inventory) {
        super(game, name);
        this.dialogue = dialogue;
        this.inventory = inventory;
    }

    public String getDialogue() {
        return dialogue;
    }

    /**
     * Talk to the NPC.
     */
    public abstract void talk();

    /**
     * Execute action of an NPC given an item.
     * @param item Item to give to the NPC.
     * @return Whether the NPC accepted the item or not.
     */
    public abstract boolean giveItem(Item item);

    public Inventory getInventory() {
        return inventory;
    }
}
