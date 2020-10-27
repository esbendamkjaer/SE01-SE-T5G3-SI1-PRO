package worldofzuul.items;

public class WasteContainer extends Item {

    private WasteType wasteType;

    public WasteContainer(String name, WasteType wasteType) {
        super(name);
        this.wasteType = wasteType;
    }

    public boolean checkWaste(Waste waste){
        return waste.getWasteType() == this.wasteType;
    }
}
