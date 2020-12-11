package dk.sdu.worldoftrash.webserver.data;

import java.util.HashMap;

public class LevelData {

    private int score;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
