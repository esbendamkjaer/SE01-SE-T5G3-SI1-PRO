package dk.sdu.worldoftrash.game.items.npcs;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.items.Item;
import dk.sdu.worldoftrash.game.rooms.Room;

public class CityNPC extends NPC {

    private Room Room;

    public CityNPC(Game game, String name, String dialogue) {
        super(game, name, dialogue, null);
    }

    @Override
    public void talk() {
        if (getGame().getCurrentRoom() == getGame().getStart()){
            System.out.println("Hello there! \uD83D\uDE09\n" +
                    "Welcome to the world of Trash. You must help us save the planet! \nNow follow me if you want to survive, start by using GO to the sorting-room and TALK to me there.\n");
        }
        if (getGame().getCurrentRoom() == getGame().getSortingRoom()){
            System.out.println("This is our sorting room. In this room we have a lot of sorting bins, and it is your job to sort the waste you find in Odense");
            System.out.println("There are 8 different primary types of garbage, residual, hard plastic, hazardous, paper, cardboard, glass, metal and organic");
            System.out.println("To sort your trash, use the DROP command as following (drop ‘item_name’ in ‘container_name’) and REMEMBER that some rubbish must be rinsed in SINK (Sink usage: use + sink + on + ITEM_NAME).");
            System.out.println("Now meet me in ODENSE");
        }
        if (getGame().getCurrentRoom() == getGame().getOdense()){
            if (getGame().getScoreSystem().getWasteCount() >= 0 && getGame().getScoreSystem().getWasteCount() < 15) {
                System.out.println("This is what's left of Odense after the trashpocalypse, and because you are new, I would recommend that you go to the SUPERMARKET first");
                System.out.println("Once you are done there and have sorted the garbage, return to me.");
                System.out.println("Btw, you have the ability to use the ‘search’ command if you aren’t able to find the trash in the different rooms.");
            } else if (getGame().getScoreSystem().getWasteCount() >= 15 && getGame().getScoreSystem().getWasteCount() < 30) {
                System.out.println("Fantastic Job! You have now cleaned the SUPERMARKET and are now able to clean up the HOSPITAL.");
            } else if (getGame().getScoreSystem().getWasteCount() >= 30 && getGame().getScoreSystem().getWasteCount() < 45) {
                System.out.println("This is incredible! I never thought the HOSPITAL was so white! I think you are ready for the most difficult part here in ODENSE, go to SCHOOL");
            }
        }
    }

    @Override
    public boolean giveItem(Item item) {
        return false;
    }
}
