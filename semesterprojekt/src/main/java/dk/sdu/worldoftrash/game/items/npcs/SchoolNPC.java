package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;

import java.util.ArrayList;

public class SchoolNPC extends NPC {
    public SchoolNPC(Game game, String name, String dialogue)
    {
        super(game, name, dialogue);

    }

    @Override
    public void talk() {
        System.out.println("Lally-ho! What ya doin’ there laddie? Ya runnin’ favors for that twit Martin eh? If ye’re runnin’ favors mindin’ runnin’ me one too eh, laddie?");
        System.out.println("Ya know, I’m working on fixin’ this place up and not just this mess of a school but Odense too");
        System.out.println("If ya steal me fertilizer from the janitor outside I’ma fix this place up real nice, pretty and everythin’.");
    }

    @Override
    public boolean giveItem(Item item) {
        return false;
    }
}
