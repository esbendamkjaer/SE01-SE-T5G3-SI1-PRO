package dk.sdu.worldoftrash.game.domain.items.npcs;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.Inventory;
import dk.sdu.worldoftrash.game.domain.items.Item;


public class SchoolNPC extends NPC {
    public SchoolNPC(Game game, String name) {
        super(game, name, new Inventory(1));
    }

    private Item fertilizer;

    @Override
    public void talk() {
        if (getInventory().hasItems(fertilizer)) {
            getGame().getTextPrinter().printText(
                    "\"Oi laddie! Ya got me fertilizer from that twat janitor, hoarding the stuff likes there being no ‘morrow. " +
                    "Well, we’ll see, we will\"" +
                    "\n\n\"Ya know, I found one of those perfume bottles. They’re not like those frigging deodorant! " +
                    "They explode when heated! not perfume bottles though they’re boooooring\"" +
                    "\n\n\"Ya know if ya rinse those things and put ‘em in GLASS-CONTAINERS they can be recycled. No fun though\"" +
                    "\n\n\"Well, I’m off to start the post-post-apocalypse\"");
        } else {
            getGame().getTextPrinter().printText(
                    "\"Lally-ho! What ya doin' there laddie? Ya runnin’ favors for that twit Martin eh? If ye’re runnin’ favors mindin’ runnin’ me one too eh, laddie?\"" +
                    "\n\n\"Ya know, I’m working on fixin’ this place up and not just this mess of a school but Odense too\"" +
                    "\n\n\"If ya steal me FERTILIZER from the janitor outside I’ma fix this place up real nice, pretty and everythin’\"");
        }
    }

    @Override
    public boolean giveItem(Item item) {
        if (item == fertilizer) {
            getInventory().storeItem(item);
            getGame().getTextPrinter().printText(
                    "\"Oi laddie! Ya got me fertilizer from that twat janitor, hoarding the stuff likes there being no ‘morrow. Well, we’ll see, we will\"" +
                    "\n\n\"Ya know, I found one of those perfume bottles. They’re not like those frigging deodorant! " +
                            "They explode when heated! not perfume bottles though they’re boooooring\"" +
                    "\n\n\"Ya know if ya rinse those things and put ‘em in GLASS-CONTAINERS they can be recycled. No fun though\"" +
                    "\n\n\"Well, I’m off to start the post-post-apocalypse\"");
            return true;
        } else {
            getGame().getTextPrinter().printText("\"This ain't the kind of fertilizer i need!\"");
            return false;
        }
    }

    public void setFertilizer(Item fertilizer){
            this.fertilizer = fertilizer;
        }
}

