package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;


public abstract class NPC extends Item {

    private String dialogue;

    public NPC(Game game, String name, String dialogue) {
        super(game, name);
        this.dialogue = dialogue;
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
}
