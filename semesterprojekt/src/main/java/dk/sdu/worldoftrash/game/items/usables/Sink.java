package dk.sdu.worldoftrash.game.items.usables;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;
import dk.sdu.worldoftrash.game.items.Waste;

public class Sink extends Item implements Usable {

    public Sink(Game game, String name) {
        super(game, name);
    }

    /**
     * Changes the clean state of a given waste to true.
     * @param waste Waste object to clean.
     * @return Returns false if the waste was already clean, otherwise true.
     */
    public boolean washItem(Waste waste) {
        if (waste.isClean()) {
            System.out.println("This item is not washable.");
            return false;
        }

        waste.setClean(true);

        System.out.println("Item is now clean.");
        return true;
    }

    @Override
    public boolean use() {
        System.out.println("You need to use the sink on an item.");
        return false;
    }

    @Override
    public boolean useOn(Item item) {

        if (!(item instanceof Waste)) {
            System.out.println("You cannot wash this item.");
            return false;
        }

        Waste waste = (Waste) item;

        return washItem(waste);
    }

}
