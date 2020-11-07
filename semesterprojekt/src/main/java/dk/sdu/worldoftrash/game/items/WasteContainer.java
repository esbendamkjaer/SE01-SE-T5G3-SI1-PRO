package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;
import dk.sdu.worldoftrash.game.data.WasteType;

public class WasteContainer extends Item {

    private WasteType wasteType;

    public WasteContainer(Game game, String name, WasteType wasteType) {
        super(game, name);
        this.wasteType = wasteType;
    }

    /**
     * Give a waste object to the waste container.
     * A true return-value does not mean, the waste was sorted correctly.
     * @param waste Waste object to give to the container.
     * @return Whether or not the container wants to keep it.
     */
    public boolean giveWaste(Waste waste){
        if (!waste.isClean()) {
            System.out.println("Clean your " + waste.getName() + " before you sort it.");

            return false;
        }

        if (checkWaste(waste)) {
            System.out.println("You put the waste in the right container!");
            getGame().getScoreSystem().givePoints(waste);
            System.out.println("Your score is now " + getGame().getScoreSystem().getScore() + ".");

        } else {
            System.out.printf("'%s' does not belong in this container because %s.\n", waste.getName(), waste.getWrongSorting());
        }

        getGame().getScoreSystem().incrementWasteCount(waste.getWasteType());
        return true;
    }

    /**
     * Checks whether a given waste object is of the same waste type as the container's content.
     * @param waste Waste to check against the container's content.
     * @return Whether or not they are of the same type.
     */
    public boolean checkWaste(Waste waste) {
        return waste.getWasteType() == this.wasteType;
    }
}
