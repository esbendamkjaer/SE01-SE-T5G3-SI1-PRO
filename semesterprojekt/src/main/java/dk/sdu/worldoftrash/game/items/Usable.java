package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.items.npcs.Interactable;

public interface Usable extends Interactable {

    /**
     * Use usable on given item
     * @return Whether it was used successfully or not
     */
    public abstract boolean use();

    /**
     * Use usable on given item
     * @param item Item to use usable on
     * @return Whether it was used successfully or not
     */
    public abstract boolean useOn(Item item);

}
