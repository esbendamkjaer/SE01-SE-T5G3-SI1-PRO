package dk.sdu.worldoftrash.game.domain.items;

import dk.sdu.worldoftrash.game.domain.Game;

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
            getGame().getTextPrinter().printText("This item is not washable.");
            getGame().getTextPrinter().printText("Penalty of -50 points.");
            getGame().getScoreSystem().addPoints(-50);
            return false;
        }

        waste.setClean(true);

        getGame().getTextPrinter().printText(String.format("You use some excess dishwater to clean the %s.", waste.getName()));
        return true;
    }

    @Override
    public boolean use() {
        getGame().getTextPrinter().printText("You need to use the sink on an item.");
        return false;
    }

    @Override
    public boolean useOn(Item item) {

        if (!(item instanceof Waste)) {
            getGame().getTextPrinter().printText("You cannot wash this item.");
            return false;
        }

        Waste waste = (Waste) item;

        return washItem(waste);
    }

    @Override
    public void interact(Player player) {
        use();
    }

    @Override
    public void giveItem(Item item, Player player) {
        useOn(item);
    }
}
