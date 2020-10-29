package dk.sdu.worldoftrash.game;

import dk.sdu.worldoftrash.game.items.Waste;
import dk.sdu.worldoftrash.shared.LevelData;
import dk.sdu.worldoftrash.shared.ScoreData;

import java.util.UUID;

public class ScoreSystem {

    private int points;

    private int level = 0;
    private int numberOfLevels = 3;

    private Client client;

    private ScoreData scoreData;

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
}
