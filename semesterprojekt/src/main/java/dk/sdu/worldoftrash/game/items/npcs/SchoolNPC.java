package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.Inventory;
import dk.sdu.worldoftrash.game.items.Item;


public class SchoolNPC extends NPC {
    public SchoolNPC(Game game, String name, String dialogue) {
        super(game, name, dialogue, new Inventory(1));

    }

    private Item fertilizer;

    @Override
    public void talk() {
        if (getInventory().hasItems(fertilizer)) {
            System.out.println("Oi laddie! Ya got me fertilizer from that twat janitor, hoarding the stuff likes there being no ‘morrow. Well, we’ll see, we will.");
            System.out.println("Ya know, I found one of those perfume bottles. They’re not like those frigging deodorant! They explode when heated! not perfume bottles though they’re boooooring.");
            System.out.println("Ya know if ya rinse those things and put ‘em in GLASS-CONTAINERS they can be recycled, no fun though.");
            System.out.println("Well, I’m off to start the post-post-apocalypse.");
        } else {
            System.out.println("Lally-ho! What ya doin' there laddie? Ya runnin’ favors for that twit Martin eh? If ye’re runnin’ favors mindin’ runnin’ me one too eh, laddie?");
            System.out.println("Ya know, I’m working on fixin’ this place up and not just this mess of a school but Odense too");
            System.out.println("If ya steal me fertilizer from the janitor outside I’ma fix this place up real nice, pretty and everythin’.");
        }
    }

    @Override
    public boolean giveItem(Item item) {
        if (item == fertilizer) {
            getInventory().storeItem(item);
            System.out.println("Oi laddie! Ya got me fertilizer from that twat janitor, hoarding the stuff likes there being no ‘morrow. Well, we’ll see, we will.");
            System.out.println("Ya know, I found one of those perfume bottles. They’re not like those frigging deodorant! They explode when heated! not perfume bottles though they’re boooooring.");
            System.out.println("Ya know if ya rinse those things and put ‘em in GLASS-CONTAINERS they can be recycled, no fun though.");
            System.out.println("Well, I’m off to start the post-post-apocalypse.");
            return true;
        } else {
            System.out.println("This ain't the kind of fertilizer i need!");
            return false;
        }
    }

    public void setFertilizer(Item fertilizer){
            this.fertilizer = fertilizer;
        }
}

