package dk.sdu.worldoftrash.game.domain.items.npcs;

import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.items.Item;
import dk.sdu.worldoftrash.game.domain.rooms.Room;

public class CityNPC extends NPC {

    private Room Room;

    public CityNPC(Game game, String name) {
        super(game, name, null);
    }

    @Override
    public void talk() {
        if (getGame().getCurrentRoom() == getGame().getStart()){
            getGame().getTextLogArea().printText("\"Hello there! \uD83D\uDE09\"");
            getGame().getTextLogArea().printText("\"Welcome to the World of Trash. My name is Trash Master Martin, but you can just call me Martin\"");
            getGame().getTextLogArea().printText("\"You must help us save the planet! Now follow me into the next room if you want to survive. " +
                    "You can go to the next room pressing X on the green arrow, and talk using X when you are near me\"");
        }
        if (getGame().getCurrentRoom() == getGame().getSortingRoom()){
            getGame().getTextLogArea().printText("\"This is our sorting room. In this room we have a lot of sorting bins, and it is your job to sort the waste you find in Odense\"");
            getGame().getTextLogArea().printText("\"There are 8 different primary types of garbage, residual, hard plastic, hazardous, paper, cardboard, glass, metal and organic\"");
            getGame().getTextLogArea().printText("\"To sort your trash, walk up to the correct waste container and press LEFT CLICK on the WASTE in your INVENTORY MENU. " +
                    "It is also important that you rinse your WASTE in the SINK\"");
            getGame().getTextLogArea().printText("\"Now meet me outside in Odense\"");
        }
        if (getGame().getCurrentRoom() == getGame().getOdense()){
            if (getGame().getScoreSystem().getWasteCount() >= 0 && getGame().getScoreSystem().getWasteCount() < 15) {
                getGame().getTextLogArea().printText("\"This is what's left of Odense after the trashpocalypse, and because you are new, " +
                        "I would recommend that you go to the SUPERMARKET in the east first\"");
                getGame().getTextLogArea().printText("\"Once you are done there and have sorted the garbage, return to me\"");
                getGame().getTextLogArea().printText("\"You might encounter survivors of the trashpocalypse and they’ll have important information to share if you help them." +
                        " You can interact with them using X\"");
            } else if (getGame().getScoreSystem().getWasteCount() >= 15 && getGame().getScoreSystem().getWasteCount() < 30) {
                getGame().getTextLogArea().printText("\"Fantastic Job! You have now cleaned the SUPERMARKET and are now able to clean up the HOSPITAL in south\"");
            } else if (getGame().getScoreSystem().getWasteCount() >= 30 && getGame().getScoreSystem().getWasteCount() < 45) {
                getGame().getTextLogArea().printText("\"This is incredible! I never thought the HOSPITAL was so white! I think you are ready for the most difficult part here in ODENSE," +
                        " go to SCHOOL in the north\"");
            }
        }
    }

    @Override
    public boolean giveItem(Item item) {
        return false;
    }
}
