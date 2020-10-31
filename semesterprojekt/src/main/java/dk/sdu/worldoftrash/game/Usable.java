package dk.sdu.worldoftrash.game;

import dk.sdu.worldoftrash.game.items.Item;

public interface Usable {

    public abstract boolean use();

    public abstract boolean useOn(Item item);

}
