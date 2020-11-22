package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;

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
            System.out.println("Penalty of -50 points.");
            getGame().getScoreSystem().addPoints(-50);
            return false;
        }

        waste.setClean(true);

        System.out.printf("You use some excess dishwater to clean the %s.\n", waste.getName());
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

    @Override
    public void interact(Player player) {
        Item item = player.getInventory().getItemAt(0);

    }

    @Override
    public void giveItem(Item item, Player player) {

    }
}
