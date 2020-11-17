package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.items.npcs.Interactable;

public interface Pickupable extends Interactable {

    /**
     * Execute logic on item associated with a pickup.
     * @return Whether or not the item wants to be picked up.
     */
    public abstract boolean pickup();

}
