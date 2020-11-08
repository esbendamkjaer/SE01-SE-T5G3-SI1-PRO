package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;

public class ZombieNPC extends NPC {

    public ZombieNPC(Game game, String name, String dialogue) {
        super(game, name, dialogue);
    }

    @Override
    public void talk() {
        System.out.println("RAAAAUUUUGHHHH. *Coughs * Sorry sir, but do you have a second to help me in this awkward situation?");
        System.out.println("I accidentally lost one of my arms, and I could not avoid seeing you on the way to the HOSPITAL, so would you kindly search for my arm inside the hospital and if possible, also bring me a sewing kit so I can sew my arm on again?");
        System.out.println("Thanks in advance.");
    }

    @Override
    public boolean giveItem(Item item) {
        return false;
    }
}
