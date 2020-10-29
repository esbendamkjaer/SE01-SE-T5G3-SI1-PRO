package dk.sdu.worldoftrash.webserver.data;

import java.util.HashMap;

public class LevelData {

    private int points;

    private HashMap<String, Integer> correctlySortedByWasteType;

    public LevelData() {
        correctlySortedByWasteType = new HashMap<>();

        for(WasteType wasteType : WasteType.values()) {
            correctlySortedByWasteType.put(wasteType.toString(), 0);
        }

    }

    /**
     * Increment value stored at the given key by one.
     * @param key Key to the value in the HashMap.
     */
    public void incrementCorrect(WasteType key) {
        correctlySortedByWasteType.put(
                key.toString(),
                correctlySortedByWasteType.get(key.toString()) + 1
        );
    }

    public void addPoints(int points) {
        setPoints(getPoints() + points);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public HashMap<String, Integer> getCorrectlySortedByWasteType() {
        return correctlySortedByWasteType;
    }

    public void setCorrectlySortedByWasteType(HashMap<String, Integer> correctlySortedByWasteType) {
        this.correctlySortedByWasteType = correctlySortedByWasteType;
    }
}
