package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.items.Item;
import dk.sdu.worldoftrash.game.items.Player;

public interface Interactable {

    public abstract void interact(Player player);

    public abstract void giveItem(Item item, Player player);

}
