package dk.sdu.worldoftrash.game;

import dk.sdu.worldoftrash.game.data.LevelData;
import dk.sdu.worldoftrash.game.data.ScoreData;
import dk.sdu.worldoftrash.game.items.Waste;

import java.util.UUID;

public class ScoreSystem {

    private int points;

    private int level = 0;
    private int numberOfLevels = 3;

    private Client client;

    private ScoreData scoreData;

    private int wasteCount;

    public ScoreSystem() {
        client = new Client(20, "http://localhost:8080");

        scoreData = new ScoreData(UUID.randomUUID());

        for (int i = 0; i < numberOfLevels; i++) {
            scoreData.addLevelData(new LevelData());
        }
    }

    public void addPoints(int points) {
        setPoints(getPoints() + points);
    }

    public void givePoints(Waste waste) {
        addPoints(waste.getPoints());

        LevelData levelData = scoreData.getLevelDataAt(level);
        levelData.incrementCorrect(waste.getWasteType());

        levelData.addPoints(waste.getPoints());
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
        wasteCount++;
    }

    public int getWasteCount() {
        return this.wasteCount;
    }

}
