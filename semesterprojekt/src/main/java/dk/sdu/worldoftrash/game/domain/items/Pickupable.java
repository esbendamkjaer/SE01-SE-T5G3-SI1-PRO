package dk.sdu.worldoftrash.game.domain.items;

public interface Pickupable extends Interactable {

    /**
     * Execute logic on item associated with a pickup.
     * @return Whether or not the item wants to be picked up.
     */
    public abstract boolean pickup();

}
