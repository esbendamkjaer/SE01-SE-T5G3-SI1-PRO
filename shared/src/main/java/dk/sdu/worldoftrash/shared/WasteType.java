package dk.sdu.worldoftrash.shared;

public enum WasteType {

    BURNABLE("burnable"),
    ORGANIC("organic"),
    HAZARDOUS("hazardous"),
    HARD_PLASTIC("hard_plastic"),
    CARDBOARD("cardboard"),
    GLASS("glass"),
    METAL("metal"),
    ;

    private String wasteType;

    WasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    @Override
    public String toString() {
        return this.wasteType;
    }
}
