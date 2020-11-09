package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.Inventory;
import dk.sdu.worldoftrash.game.items.Item;
import java.util.ArrayList;

public class ParkingLotNPC extends NPC {
    private Item bigbox;
    private Inventory inventory;

    public ParkingLotNPC(Game game, String name, String dialogue) {
        super(game, name, dialogue);
        inventory = new Inventory(2);
    }


    @Override
    public void talk() { if (NPC.)
        System.out.println("You! You in the red shirt! You must help me! I don’t have a place to stay and have never lived in an ordinary house before.");
        System.out.println("You must find me a cardboard box, a big cardboard box, the biggest cardboard box, I want to be the king of the homeless! ");
        System.out.println("Go to ‘supermarked’, they might have some cardboard boxes laying around, I'm sure you can find one in there somewhere");
    }
    @Override
    public boolean giveItem(Item item) {
        if (item == bigbox) {
            System.out.println("Amazing! But isn’t that box almost too big? But now EVERYONE knows that I am the king of the homeless, so it doesn’t matter at all! ");
            System.out.println("Here take a pro tip from a king: If you find any pizza boxes, pick it up and throw it In the bin. But REMEMBER! Pizza trays are not cardboard waste, as it is smeared into pizza.");
            System.out.println("This also applies to milk cartons a, and if you ever buy a pizza, then you should keep in mind, that the receipts from your pizza purchase must be included in the residual container, this is due to the fact that receipts is made of thermal paper and full of chemicals!");
            return true;
        } else {
            System.out.println("I can't use this");
            return false;
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void setBigbox(Item bigbox) {
        this.bigbox = bigbox;
    }
}
