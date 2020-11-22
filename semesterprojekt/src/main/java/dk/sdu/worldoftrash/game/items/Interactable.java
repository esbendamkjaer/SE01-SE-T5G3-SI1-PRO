package dk.sdu.worldoftrash.game.items;

public interface Interactable {

    public abstract void interact(Player player);

    public abstract void giveItem(Item item, Player player);

}
