package dk.sdu.worldoftrash.webserver;

import java.util.HashMap;

public class LevelData {

    private int points;

    private HashMap<WasteType, Integer> correctlySortedByWasteType;

    public LevelData() {
        correctlySortedByWasteType = new HashMap<>();

        for(WasteType wasteType : WasteType.values()) {
            correctlySortedByWasteType.put(wasteType, 0);
        }

    }

    /**
     * Increment value stored at the given key by one.
     * @param key Key to the value in the HashMap.
     */
    public void incrementCorrect(WasteType key) {
        correctlySortedByWasteType.put(
                key,
                correctlySortedByWasteType.get(key) + 1
        );
    }

}
