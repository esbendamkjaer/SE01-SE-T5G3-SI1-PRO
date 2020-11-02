package dk.sdu.worldoftrash.game.data;

import java.util.HashMap;
import java.util.Map;

public class LevelData {

    private Map<String, CategoryData> correctlySortedByWasteType;

    public LevelData() {
        correctlySortedByWasteType = new HashMap<>();
    }

    public Map<String, CategoryData> getCorrectlySortedByWasteType() {
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
        createCategoryIfAbsent(wasteType);

        CategoryData categoryData = correctlySortedByWasteType.get(wasteType.toString());
        categoryData.setCorrect(categoryData.getCorrect() + 1);
    }

    /**
     * Increment total waste count for given waste type.
     * @param wasteType Waste type.
     */
    public void incrementWasteCount(WasteType wasteType) {
        createCategoryIfAbsent(wasteType);

        CategoryData categoryData = correctlySortedByWasteType.get(wasteType.toString());
        categoryData.setTotal(categoryData.getTotal() + 1);
    }

    /**
     * If given WasteType is absent in HashMap, creates CategoryData object and saves it in HashMap.
     * @param wasteType Waste type.
     */
    public void createCategoryIfAbsent(WasteType wasteType) {
        if (!correctlySortedByWasteType.containsKey(wasteType.toString())) {
            correctlySortedByWasteType.put(wasteType.toString(), new CategoryData(0, 0));
        }
    }
}