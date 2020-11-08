package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;

import java.util.ArrayList;

public class ParkingLotNPC extends NPC {

    public ParkingLotNPC(Game game, String name, String dialogue) {
        super(game, name, dialogue);
    }

    @Override
    public void talk() {
        System.out.println("");
    }

    @Override
    public boolean giveItem(Item item) {
        return false;
    }

}
