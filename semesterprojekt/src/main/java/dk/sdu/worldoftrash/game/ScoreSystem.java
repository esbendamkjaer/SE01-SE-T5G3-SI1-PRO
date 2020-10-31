package dk.sdu.worldoftrash.game;

import dk.sdu.worldoftrash.game.data.LevelData;
import dk.sdu.worldoftrash.game.data.ScoreData;
import dk.sdu.worldoftrash.game.items.Waste;

import java.util.UUID;

public class ScoreSystem {

    private int points;
    private int wasteCount;

    private Client client;

    private ScoreData scoreData;

    private LevelHandler levelHandler;

    public ScoreSystem() {
        this.client = new Client(20, "http://localhost:8080" /*"https://worldoftrash.herokuapp.com"*/);

        this.scoreData = new ScoreData(UUID.randomUUID());
        this.levelHandler = new LevelHandler();

        this.points = 0;
    }

    public void addPoints(int points) {
        setPoints(getPoints() + points);
    }

    public void givePoints(Waste waste) {
        addPoints(waste.getPoints());

        LevelData levelData = getLevelDataByName(levelHandler.getCurrentLevel());

        levelData.incrementCorrect(waste.getWasteType());
    }

    public void uploadData() {
        client.sendScoreData(scoreData);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void incrementWasteCount() {
        LevelData levelData = getLevelDataByName(levelHandler.getCurrentLevel());
        levelData.setWasteCount(levelData.getWasteCount() + 1);

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
        LevelData levelData = scoreData.getLevelDataByName(levelHandler.getCurrentLevel());

        if (levelData == null) {
            levelData = new LevelData();
            scoreData.addLevelData(levelHandler.getCurrentLevel(), levelData);
        }

        return levelData;
    }

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }

}
