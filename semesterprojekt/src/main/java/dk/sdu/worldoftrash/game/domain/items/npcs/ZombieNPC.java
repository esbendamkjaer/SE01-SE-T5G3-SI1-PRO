package dk.sdu.worldoftrash.game.domain.items.npcs;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.Inventory;
import dk.sdu.worldoftrash.game.domain.items.Item;

public class ZombieNPC extends NPC {
    private Item arm;
    private Item sewing_kit;

    public ZombieNPC(Game game, String name) {
        super(game, name, new Inventory(2));
    }

    @Override
    public void talk() {
        if (getInventory().hasItems(arm, sewing_kit)) {
            getGame().getTextLogArea().printText("Now I can right my wrongs of my past, Ignorance! Lazy! Reluctant! I wasn't the best trash sorter in my good days.");
            getGame().getTextLogArea().printText("My worst enemy actually were office supplies. Especially paper clips and post it notes. Did you know those two aren't worth recycling?");
            getGame().getTextLogArea().printText("They’ll leave a larger carbon food print rather than just tossing them in with residual waste! You didn't? now you do chap.");
        } else {
            getGame().getTextLogArea().printText("RAAAAUUUUGHHHH. *Coughs * Sorry sir, but do you have a second to help me in this awkward situation?");
            getGame().getTextLogArea().printText("I accidentally lost one of my arms, and I could not avoid seeing you on the way to the HOSPITAL, so would you kindly search for my arm inside the hospital and if possible, also bring me a sewing kit so I can sew my arm on again?");
            getGame().getTextLogArea().printText("Thanks in advance.");
        }
    }

    @Override
    public boolean giveItem(Item item) {
        if (item == arm) {
            getInventory().storeItem(item);
            getGame().getTextLogArea().printText("Oh my if you aren't marching so gallantly with my arm under your arm.");
            if (!getInventory().hasItems(sewing_kit)) {
                getGame().getTextLogArea().printText("If you'd get me a sewing kit, I would be eternally grateful.");
            }
            checkItems();
            return true;
        } else if (item == sewing_kit) {
            getInventory().storeItem(item);
            getGame().getTextLogArea().printText("Thank you for the sewing kit!");
            if (!getInventory().hasItems(arm)) {
                getGame().getTextLogArea().printText("If you’d get me my arm, I can sew it on.");
            }
            checkItems();
            return true;
        } else {
            getGame().getTextLogArea().printText("Are you joking with me?!");
            return false;
        }
    }

    public void checkItems() {
        if (getInventory().hasItems(arm, sewing_kit)) {
            getGame().getTextLogArea().printText("Now I can right my wrongs of my past, Ignorance! Lazy! Reluctant! I wasn't the best trash sorter in my good days.");
            getGame().getTextLogArea().printText("My worst enemy actually were office supplies. Especially paper clips and post it notes. Did you know those two aren't worth recycling?");
            getGame().getTextLogArea().printText("They’ll leave a larger carbon food print rather than just tossing them in with residual waste! You didn't? now you do chap.");
        }
    }

    public void setArm(Item arm) {
        this.arm = arm;
    }

    public void setSewing_kit(Item sewing_kit) {
        this.sewing_kit = sewing_kit;
    }
}
