package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;

public class CityNPC extends NPC {

    public CityNPC(Game game, String name, String dialogue) {
        super(game, name, dialogue);
    }

    @Override
    public void talk() {
        System.out.println("Welcome to the world of Trash. You must help us save the planet! Now follow me if you want to survive, start by using GO to the sorting-room and TALK to me there.");
    }

    @Override
    public boolean giveItem(Item item) {

        return false;
    }

}
