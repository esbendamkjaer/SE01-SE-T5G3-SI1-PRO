package dk.sdu.worldoftrash.webserver.data;

import java.util.HashMap;

public class LevelData {

    private int points;

    private HashMap<String, CategoryData> correctlySortedByWasteType;

    public LevelData() {
        correctlySortedByWasteType = new HashMap<>();
    }

    public HashMap<String, CategoryData> getCorrectlySortedByWasteType() {
        return correctlySortedByWasteType;
    }

    public void setCorrectlySortedByWasteType(HashMap<String, CategoryData> correctlySortedByWasteType) {
        this.correctlySortedByWasteType = correctlySortedByWasteType;
    }

    /**
     * Increment number of correctly sorted for given waste type.
     * @param wasteType Waste type.
     */
    public void incrementCorrect(WasteType wasteType) {
        CategoryData categoryData = correctlySortedByWasteType.get(wasteType.toString());
        categoryData.setCorrect(categoryData.getCorrect() + 1);
    }

    /**
     * Increment total waste count for given waste type.
     * @param wasteType Waste type.
     */
    public void incrementWasteCount(WasteType wasteType) {
        CategoryData categoryData = correctlySortedByWasteType.get(wasteType.toString());
        categoryData.setTotal(categoryData.getTotal() + 1);
    }
}
