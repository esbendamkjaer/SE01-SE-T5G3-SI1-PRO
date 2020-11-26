package dk.sdu.worldoftrash.game.domain;

public enum WasteType {

    RESIDUAL("residual"),
    ORGANIC("organic"),
    HAZARDOUS("hazardous"),
    HARD_PLASTIC("hard_plastic"),
    CARDBOARD("cardboard"),
    PAPER("paper"),
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
