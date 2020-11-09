package dk.sdu.worldoftrash.game.items;

public interface Usable {

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
