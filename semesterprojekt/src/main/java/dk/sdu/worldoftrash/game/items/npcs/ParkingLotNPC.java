package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;

import java.util.ArrayList;

public class ParkingLotNPC extends NPC {
    private Item bigbox;

    public ParkingLotNPC(Game game, String name, String dialogue) {
        super(game, name, dialogue);
    }


    @Override
    public void talk() {
        System.out.println("You! You in the green sweater! You must help me! I don’t have a place to stay and have never lived in an ordinary house before.");
        System.out.println("You must find me a cardboard box, a big cardboard box, the biggest cardboard box, I want to be the king of the homeless! ");
        System.out.println("Go to ‘supermarked’, they might have some cardboard boxes laying around, I'm sure you can find one in there somewhere");
    }

    @Override
    public boolean giveItem(Item item) {
        if (item == bigbox) {
            System.out.println("Thanks bud");
            return true;
        } else {
            System.out.println("I cant use this");
            return false;
        }
    }

    public void setBigbox(Item bigbox) {
        this.bigbox = bigbox;
    }
}
