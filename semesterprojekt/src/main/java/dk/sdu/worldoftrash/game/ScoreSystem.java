package dk.sdu.worldoftrash.game;

import dk.sdu.worldoftrash.game.data.LevelData;
import dk.sdu.worldoftrash.game.data.ScoreData;
import dk.sdu.worldoftrash.game.data.WasteType;
import dk.sdu.worldoftrash.game.items.Waste;

import java.util.UUID;

public class ScoreSystem {

    private int score;

    private int wasteCount;

    private Client client;

    private ScoreData scoreData;

    private LevelHandler levelHandler;

    public ScoreSystem() {
        this.client = new Client(20, "https://worldoftrash.herokuapp.com");

        this.scoreData = new ScoreData(UUID.randomUUID());
        this.levelHandler = new LevelHandler();

        this.score = 0;
    }

    /**
     * Add points to score.
     * @param points Number of points to add.
     */
    public void addPoints(int points) {
        setScore(getScore() + points);
    }

    /**
     * Give points based on sorted Waste.
     * @param waste Sorted piece of waste to give points from.
     */
    public void givePoints(Waste waste) {
        addPoints(waste.getPoints());

        LevelData levelData = getLevelDataByName(levelHandler.getCurrentLevelName());

        levelData.incrementCorrect(waste.getWasteType());
    }

    /**
     * Upload ScoreData to webserver
     */
    public void uploadData() {
        client.sendScoreData(scoreData);
    }

    /**
     * Increment the total number of sorted waste.
     * @param wasteType WasteType of just sorted Waste.
     */
    public void incrementWasteCount(WasteType wasteType) {
        LevelData levelData = getLevelDataByName(levelHandler.getCurrentLevelName());
        levelData.incrementWasteCount(wasteType);

        wasteCount++;
        levelHandler.updateCondition(wasteCount);
    }

    /**
     * Get LevelData object of level by given name.
     * If a level by the given name is not found, an entry is created and returned.
     * @param name Name of level
     * @return LevelData object
     */
    public LevelData getLevelDataByName(String name) {
        LevelData levelData = scoreData.getLevelDataByName(levelHandler.getCurrentLevelName());

        if (levelData == null) {
            levelData = new LevelData();
            scoreData.addLevelData(levelHandler.getCurrentLevelName(), levelData);
        }

        return levelData;
    }

    /**
     * Returns the total number of sorted waste.
     * @return Number of sorted waste.
     */
    public int getWasteCount() {
        return wasteCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }

}
