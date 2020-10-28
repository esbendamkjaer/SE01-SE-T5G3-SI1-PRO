package dk.sdu.worldoftrash.game.items;

import dk.sdu.worldoftrash.game.Game;

public class Waste extends Pickupable {
    private WasteType wasteType;
    private int point;
    private String description;
    private boolean clean;

    public Waste(Game game, String name, WasteType wasteType, String description, boolean clean) {
        super(game, name);
        this.wasteType = wasteType;
        this.description = description;
        this.clean = clean;
    }

    public WasteType getWasteType(){
        return wasteType;
    }

    public int getPoint(){
        throw new UnsupportedOperationException();
    }

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }
}
