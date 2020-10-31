package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;

public class CityNPC extends NPC {

    public CityNPC(Game game, String name, String dialogue) {
        super(game, name, dialogue);
    }

    @Override
    public void talk() {
        System.out.println("This is the city hub.");
    }

    @Override
    public boolean giveItem(Item item) {

        return false;
    }

}
