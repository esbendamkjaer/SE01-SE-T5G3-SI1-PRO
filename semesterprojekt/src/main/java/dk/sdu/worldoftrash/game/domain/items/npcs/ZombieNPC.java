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
            getGame().getTextPrinter().printText(
                    "\"Now I can right my wrongs of my past, Ignorance! Lazy! Reluctant! I wasn't the best trash sorter in my good days.\"" +
                    "\n\n\"My worst enemy actually were office supplies. Especially paper clips and post it notes. Did you know those two aren't worth recycling?\"" +
                    "\n\n\"They’ll leave a larger carbon food print rather than just tossing them in with residual waste! You didn't? now you do chap\"");
        } else {
            getGame().getTextPrinter().printText(
                    "\"RAAAAUUUUGHHHH. *Coughs * Sorry sir, but do you have a second to help me in this awkward situation?\"" +
                    "\n\n\"I accidentally lost one of my ARMS, and I could not avoid seeing you on the way to the HOSPITAL, " +
                    "so would you kindly search for my arm inside the hospital and if possible, also bring me a SEWING KIT so I can sew my arm on again?\"" +
                    "\n\n\"Thanks in advance\"");
        }
    }

    @Override
    public boolean giveItem(Item item) {
        if (item == arm) {
            getInventory().storeItem(item);
            getGame().getTextPrinter().printText(
                    "\"Oh my if you aren't marching so gallantly with my arm under your arm\"" +
                    (!getInventory().hasItems(sewing_kit) ? "\n\n\"If you'd get me a SEWING KIT, I would be eternally grateful\"" : "") +
                    checkItems());

            return true;
        } else if (item == sewing_kit) {
            getInventory().storeItem(item);
            getGame().getTextPrinter().printText(
                    "\"Thank you for the sewing kit!\"" +
                    (!getInventory().hasItems(arm) ? "\n\n\"If you’d get me my ARM, I can sew it on\"" : "") +
                    checkItems());

            return true;
        } else {
            getGame().getTextPrinter().printText("\"Are you joking with me?!\"");
            return false;
        }
    }

    private String checkItems() {
        if (getInventory().hasItems(arm, sewing_kit)) {
            return
                "\n\n\"Now I can right my wrongs of my past, Ignorance! Lazy! Reluctant! I wasn't the best trash sorter in my good days\"" +
                "\n\n\"My worst enemy actually were office supplies. Especially paper clips and post it notes. Did you know those two aren't worth recycling?\"" +
                "\n\n\"They’ll leave a larger carbon food print rather than just tossing them in with residual waste! You didn't? now you do chap\"";
        }
        return "";
    }

    public void setArm(Item arm) {
        this.arm = arm;
    }

    public void setSewing_kit(Item sewing_kit) {
        this.sewing_kit = sewing_kit;
    }
}
