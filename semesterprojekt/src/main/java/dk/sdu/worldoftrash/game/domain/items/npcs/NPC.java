package dk.sdu.worldoftrash.game.domain.items.npcs;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.Inventory;
import dk.sdu.worldoftrash.game.domain.items.Interactable;
import dk.sdu.worldoftrash.game.domain.items.Item;
import dk.sdu.worldoftrash.game.domain.items.Player;

public abstract class NPC extends Item implements Interactable {

    private Inventory inventory;

    public NPC(Game game, String name, Inventory inventory) {
        super(game, name);
        this.inventory = inventory;
    }

    /**
     * Talk to the NPC.
     */
    public abstract void talk();

    /**
     * Execute action of an NPC given an Item object.
     * @param item Item to give to the NPC.
     * @return Whether the NPC accepted the item or not.
     */
    public abstract boolean giveItem(Item item);

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void interact(Player player) {
        talk();
    }

    @Override
    public void giveItem(Item item, Player player) {
        if (giveItem(item)) {
            player.getInventory().removeItem(item);
        }
    }
}
