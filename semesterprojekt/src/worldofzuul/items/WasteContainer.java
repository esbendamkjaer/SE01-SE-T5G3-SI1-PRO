package worldofzuul.items;

public class WasteContainer extends Item {

    private WasteType wasteType;

    public WasteContainer(String name, WasteType wasteType) {
        super(name);
        this.wasteType = wasteType;
    }

    /**
     * Checks whether a given waste object is of the same waste type as the container's content.
     * @param waste Waste to check against the container's content.
     * @return Whether or not they are of the same type.
     */
    public boolean checkWaste(Waste waste){
        return waste.getWasteType() == this.wasteType;
    }
}
