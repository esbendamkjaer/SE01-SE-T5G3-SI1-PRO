package dk.sdu.worldoftrash.game.domain.items.npcs;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.items.Item;

public class CityNPC extends NPC {

    public CityNPC(Game game, String name) {
        super(game, name, null);
    }

    @Override
    public void talk() {
        if (getGame().getCurrentRoom().getName().equals("start")){
            getGame().getTextPrinter().printText("\"Hello there! \uD83D\uDE09\" \n\n\"Welcome to the World of Trash. My name is Trash Master Martin," +
                    " but you can just call me Martin\" \n\n\"You must help us save the planet! Now follow me into the next room if you want to survive. " +
                    "You can go to the next room by pressing X on the green arrow, and talk using X when you are near me\"");
        } else if (getGame().getCurrentRoom().getName().equals("sortingRoom")){
            getGame().getTextPrinter().printText("\"This is our sorting room. In this room we have a lot of sorting bins, and it is your job to sort the waste you find" +
                    " in Odense\" \n\n\"There are 8 different primary types of garbage: residual, hard plastic, hazardous, paper, cardboard, glass, metal and" +
                    " organic\" \n\n\"To sort your trash, walk up to the correct waste container and press LEFT CLICK on the WASTE in your INVENTORY MENU. " +
                    "It is also important that you rinse your WASTE in the SINK if needed\" \n\n\"Now meet me outside in Odense\"");
        } else if (getGame().getCurrentRoom().getName().equals("city")){
            if (getGame().getScoreSystem().getWasteCount() >= 0 && getGame().getScoreSystem().getWasteCount() < 15) {
                getGame().getTextPrinter().printText("\"This is what's left of Odense after the trashpocalypse, and because you are new, " +
                        "I would recommend that you go to the SUPERMARKET to the east first\" \n\n\"Once you are done there and have sorted all the garbage," +
                        " return to me\" \n\n\"You might encounter survivors of the trashpocalypse. They’ll have important information to share if you help them." +
                        " You can interact with them by pressing X\"");
            } else if (getGame().getScoreSystem().getWasteCount() >= 15 && getGame().getScoreSystem().getWasteCount() < 30) {
                getGame().getTextPrinter().printText("\"Fantastic Job! You have now cleaned the SUPERMARKET and are now able to clean up the HOSPITAL to the south\"");
            } else if (getGame().getScoreSystem().getWasteCount() >= 30 && getGame().getScoreSystem().getWasteCount() < 45) {
                getGame().getTextPrinter().printText("\"This is incredible! I never thought the HOSPITAL was so white! I think you are ready for the most" +
                        " difficult part here in ODENSE, go to the SCHOOL to the north\"");
            }
        }
    }

    @Override
    public boolean giveItem(Item item) {
        return false;
    }
}
