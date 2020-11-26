package dk.sdu.worldoftrash.game.domain;

import dk.sdu.worldoftrash.game.dal.Client;
import dk.sdu.worldoftrash.game.dal.data.LevelData;
import dk.sdu.worldoftrash.game.dal.data.ScoreData;
import dk.sdu.worldoftrash.game.domain.items.Waste;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.UUID;

public class ScoreSystem {

    private IntegerProperty scoreProperty;

    private int wasteCount;

    private Client client;

    private ScoreData scoreData;

    private LevelHandler levelHandler;

    private Game game;

    public ScoreSystem(Game game) {
        this.client = new Client(20, "https://worldoftrash.herokuapp.com");
        this.game = game;

        this.scoreData = new ScoreData(UUID.randomUUID());

        this.scoreProperty = new SimpleIntegerProperty(0);

        this.levelHandler = new LevelHandler(game, 45);
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
        return scoreProperty.getValue();
    }

    public void setScore(int score) {
        this.scoreProperty.setValue(score);
    }

    public IntegerProperty getScoreProperty() {
        return this.scoreProperty;
    }

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }

}
