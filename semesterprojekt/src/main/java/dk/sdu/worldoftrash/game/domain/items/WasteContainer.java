package dk.sdu.worldoftrash.game.domain.items;

import dk.sdu.worldoftrash.game.domain.SoundIO;
import dk.sdu.worldoftrash.game.domain.Game;
import dk.sdu.worldoftrash.game.domain.WasteType;

public class WasteContainer extends Item implements Interactable {

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
            getGame().getTextLogArea().printText("Clean your " + waste.getName() + " before you sort it.");
            getGame().getTextLogArea().printText("Penalty of -50 points.");
            getGame().getScoreSystem().addPoints(-50);
            return false;
        }
        if (checkWaste(waste)) {
            getGame().getScoreSystem().onCorrect(waste);
        } else {
            getGame().getScoreSystem().onWrong(waste);
            getGame().getTextLogArea().printText(String.format("'%s' does not belong in this container because %s.", waste.getName(), waste.getWrongSorting()));
            waste.setWronglySorted(true);
            return false;
        }
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

    @Override
    public void interact(Player player) {
        getGame().getTextLogArea().printText("Click an item in your inventory to drop it in the container.");
    }

    @Override
    public void giveItem(Item item, Player player) {
        if (item instanceof Waste) {
            if (giveWaste((Waste) item)) {
                player.getInventory().removeItem(item);
            }
        } else {
            getGame().getTextLogArea().printText("You can only drop waste in a waste container");
        }
    }
}
