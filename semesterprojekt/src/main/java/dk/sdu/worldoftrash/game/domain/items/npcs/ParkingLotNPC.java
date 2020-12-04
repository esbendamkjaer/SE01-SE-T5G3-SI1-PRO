package dk.sdu.worldoftrash.game.domain.items.npcs;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.Inventory;
import dk.sdu.worldoftrash.game.domain.items.Item;

public class ParkingLotNPC extends NPC {
    private Item bigbox;

    public ParkingLotNPC(Game game, String name) {
        super(game, name, new Inventory(1));
    }


    @Override
    public void talk() {
        if (getInventory().hasItemWithName(bigbox.getName())) {
            getGame().getTextPrinter().printText(
                    "\"Amazing! But isn’t that box almost too big? But now EVERYONE knows that I am the king of the homeless," +
                    " so it doesn't matter at all!\"" +
                    "\n\n\"Here take a pro tip from a king: If you find any pizza boxes, pick it up and throw it in the bin. " +
                    "But REMEMBER! Pizza boxes are not cardboard waste, as it is smeared into pizza\"" +
                    "\n\n\"This also applies to milk cartons, and if you ever buy a pizza, then you should keep in mind, " +
                    "that the receipts from your pizza purchase also goes in the residual container, this is due to the fact that receipts is made of " +
                    "thermal paper and full of chemicals!\"");
        } else {
            getGame().getTextPrinter().printText(
                    "\"You! You in the red shirt! You must help me! I don’t have a place to stay and have never lived in an ordinary house before\"" +
                    "\n\n\"You must find me a cardboard box, a big cardboard box, the BIGGEST CARDBOARD BOX, I want to be the king of the homeless!\"" +
                    "\n\n\"Go to ‘supermarket’, they might have some cardboard boxes laying around, I'm sure you can find one in there somewhere\"");
        }
    }

    @Override
    public boolean giveItem(Item item) {
        if (item == bigbox) {
            getInventory().storeItem(item);
            getGame().getTextPrinter().printText(
                    "\"Amazing! But isn’t that box almost too big? But now EVERYONE knows that I am the king of the homeless, " +
                    "so it doesn't matter at all!\"" +
                    "\n\n\"Here take a pro tip from a king: If you find any pizza boxes, pick it up and throw it in the bin. But REMEMBER! " +
                    "Pizza boxes are not cardboard waste, as it is smeared into pizza\"" +
                    "\n\n\"This also applies to milk cartons, and if you ever buy a pizza, then you should keep in mind, " +
                    "that the receipts from your pizza purchase also goes in the residual container, this is due to the fact that receipts is made of thermal paper" +
                    " and full of chemicals!\"");
            return true;
        } else {
            getGame().getTextPrinter().printText("\"I can't use this\"");
            return false;
        }
    }

    public void setBigbox(Item bigbox) {
        this.bigbox = bigbox;
    }
}
